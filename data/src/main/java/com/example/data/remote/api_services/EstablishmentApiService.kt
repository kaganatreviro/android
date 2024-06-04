package com.example.data.remote.api_services

import com.example.data.remote.dto.EstablishmentDetailsDto
import com.example.data.remote.dto.FeedbackListDto
import com.example.data.remote.dto.MenuDto
import com.example.data.remote.dto.PostFeedbackDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface EstablishmentApiService {
    @GET(GET_ESTABLISHMENTS_LIST)
    suspend fun getEstablishmentList(
        @Query("search") search: String?
    ): List<EstablishmentDetailsDto>

    @GET(GET_ESTABLISHMENT_BY_ID)
    suspend fun getEstablishmentDetailsById(@Path("id") id: Int): EstablishmentDetailsDto

    @GET(GET_ESTABLISHMENT_MENU_BY_ID)
    suspend fun getEstablishmentMenuById(@Path("id") id: Int): List<MenuDto>

    @GET(GET_ESTABLISHMENT_FEEDBACK_LIST)
    suspend fun getEstablishmentFeedbackList(@Path("establishment_id") id: Int): List<FeedbackListDto>

    @POST(POST_FEEDBACK)
    suspend fun postFeedback(@Body feedback: PostFeedbackDto): FeedbackListDto

    @GET(GET_FEEDBACK_ANSWERS)
    suspend fun getFeedbackAnswers(@Path("id") id: Int): List<FeedbackListDto>

    companion object {
        const val GET_ESTABLISHMENTS_LIST = "api/v1/partner/establishments/"
        const val GET_ESTABLISHMENT_BY_ID = "api/v1/partner/establishments/{id}/"
        const val GET_ESTABLISHMENT_MENU_BY_ID = "api/v1/partner/menu/{id}/"
        const val GET_ESTABLISHMENT_FEEDBACK_LIST = "api/v1/feedback/feedbacks/list/{establishment_id}/"
        const val POST_FEEDBACK = "api/v1/feedback/feedbacks/create/"
        const val GET_FEEDBACK_ANSWERS = "api/v1/feedback/feedbacks/{id}/answers/list/"
    }
}