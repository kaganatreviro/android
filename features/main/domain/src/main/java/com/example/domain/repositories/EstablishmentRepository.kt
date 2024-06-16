package com.example.domain.repositories

import com.example.core.either.Either
import com.example.core.either.NetworkError
import com.example.domain.models.EstablishmentDetails
import com.example.domain.models.Feedback
import com.example.domain.models.Menu
import com.example.domain.models.PostFeedback
import com.example.domain.models.PostFeedbackInAnswers
import kotlinx.coroutines.flow.Flow

interface EstablishmentRepository {

      fun getEstablishmentList(search: String?): Flow<Either<NetworkError, List<EstablishmentDetails>>>
      fun getEstablishmentMenuById(id: Int): Flow<Either<String, List<Menu>>>
      fun getEstablishmentDetailsById(id: Int): Flow<Either<String, EstablishmentDetails>>
      fun getEstablishmentFeedbackList(id: Int): Flow<Either<String, List<Feedback>>>
      fun postFeedback(param: PostFeedback): Flow<Either<String, Feedback>>
      fun postFeedbackInAnswers(param: PostFeedbackInAnswers): Flow<Either<String, Feedback>>
      fun getFeedbackAnswers(feedbackId: Int): Flow<Either<String, List<Feedback>>>
}