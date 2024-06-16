package com.example.data.repositories

import com.example.core.either.Either
import com.example.core.either.NetworkError
import com.example.data.remote.api_services.EstablishmentApiService
import com.example.data.remote.dto.FeedbackListDto
import com.example.data.remote.dto.toDto
import com.example.domain.models.EstablishmentDetails
import com.example.domain.models.Feedback
import com.example.domain.models.Menu
import com.example.domain.models.PostFeedback
import com.example.domain.models.PostFeedbackInAnswers
import com.example.domain.repositories.EstablishmentRepository
import kotlinx.coroutines.flow.Flow

class EstablishmentRepositoryImpl(
    private val apiService: EstablishmentApiService
): EstablishmentRepository {
    override  fun getEstablishmentList(search: String?): Flow<Either<NetworkError, List<EstablishmentDetails>>> = makeNetworkRequestToFetchList {
        apiService.getEstablishmentList(search)
    }

    override fun getEstablishmentMenuById(id: Int): Flow<Either<String, List<Menu>>> = makeNetworkRequest {
        apiService.getEstablishmentMenuById(id).map { it.toDomain() }
    }

    override fun getEstablishmentDetailsById(id: Int): Flow<Either<String, EstablishmentDetails>> = makeNetworkRequest {
        apiService.getEstablishmentDetailsById(id).toDomain()
    }

    override fun getEstablishmentFeedbackList(id: Int): Flow<Either<String, List<Feedback>>> = makeNetworkRequest {
        apiService.getEstablishmentFeedbackList(id).map { it.toDomain() }
    }

    override fun postFeedback(param: PostFeedback): Flow<Either<String, Feedback>> = makeNetworkRequest{
        apiService.postFeedback(param.toDto()).toDomain()
    }

    override fun postFeedbackInAnswers(param: PostFeedbackInAnswers): Flow<Either<String, Feedback>> = makeNetworkRequest{
        apiService.postFeedbackInAnswers(param.toDto()).toDomain()
    }

    override fun getFeedbackAnswers(feedbackId: Int): Flow<Either<String, List<Feedback>>> = makeNetworkRequest {
        apiService.getFeedbackAnswers(feedbackId).map { it.toDomain() }
    }
}