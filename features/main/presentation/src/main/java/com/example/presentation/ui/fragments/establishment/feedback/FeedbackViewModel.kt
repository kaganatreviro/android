package com.example.presentation.ui.fragments.establishment.feedback

import com.example.core_ui.base.BaseViewModel
import com.example.domain.models.Feedback
import com.example.domain.models.PostFeedback
import com.example.domain.models.PostFeedbackInAnswers
import com.example.domain.use_cases.GetEstablishmentFeedbackListUseCase
import com.example.domain.use_cases.GetFeedbackAnswersUseCase
import com.example.domain.use_cases.GetUserEmailUseCase
import com.example.domain.use_cases.PostFeedbackInAnswersUseCase
import com.example.domain.use_cases.PostFeedbackUseCase
import kotlinx.coroutines.flow.asStateFlow

class FeedbackViewModel(
    private val postFeedbackUseCase: PostFeedbackUseCase,
    private val postFeedbackInAnswersUseCase: PostFeedbackInAnswersUseCase,
    private val getEstablishmentFeedbackListUseCase: GetEstablishmentFeedbackListUseCase,
    private val getFeedbackAnswersUseCase: GetFeedbackAnswersUseCase,
    private val getUserEmailUseCase: GetUserEmailUseCase
) : BaseViewModel() {

    private val _establishmentFeedbackListState = mutableUiStateFlow<List<Feedback>>()
    val establishmentFeedbackListState = _establishmentFeedbackListState.asStateFlow()

    private val _postFeedbackState = mutableUiStateFlow<Feedback>()
    val postFeedbackState = _postFeedbackState.asStateFlow()

    private val _postFeedbackInAnswersState = mutableUiStateFlow<Feedback>()
    val postFeedbackInAnswersState = _postFeedbackInAnswersState.asStateFlow()

    private val _feedbackAnswersUseCase = mutableUiStateFlow<List<Feedback>>()
    val feedbackAnswersState = _feedbackAnswersUseCase.asStateFlow()

    fun getEstablishmentFeedbackList(id: Int) {
        getEstablishmentFeedbackListUseCase(id).gatherRequest(_establishmentFeedbackListState)
    }

    fun postFeedback(feedback: PostFeedback){
        postFeedbackUseCase(feedback).gatherRequest(_postFeedbackState)
    }

    fun postFeedbackInAnswers(feedback: PostFeedbackInAnswers){
        postFeedbackInAnswersUseCase(feedback).gatherRequest(_postFeedbackInAnswersState)
    }

    fun getFeedbackAnswers(feedbackId: Int){
        getFeedbackAnswersUseCase(feedbackId).gatherRequest(_feedbackAnswersUseCase)
    }

    fun getUserEmail(): String? = getUserEmailUseCase()
}