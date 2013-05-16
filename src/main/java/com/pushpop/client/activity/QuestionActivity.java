package com.pushpop.client.activity;

import com.google.web.bindery.requestfactory.shared.Receiver;
import com.pushpop.client.IClientFactory;
import com.pushpop.client.place.QuestionPlace;
import com.pushpop.client.proxy.QuestionProxy;
import com.pushpop.client.service.DaoRequestFactory.QuestionRequest;
import com.pushpop.client.view.IQuestionView;

public class QuestionActivity extends AbstractActivity<IQuestionView> implements
		IQuestionView.Presenter {
	
	Receiver<QuestionProxy> QUESTION_RECEIVED = new Receiver<QuestionProxy>() {

		@Override
		public void onSuccess(QuestionProxy response) {
			displayQuestion(response);
		}
	};
	
	private Long questionId;

	public QuestionActivity(QuestionPlace place, IClientFactory clientFactory,
			IQuestionView display) {
		super(clientFactory, display);
		this.questionId = place.getQuestionId();
	}

	@Override
	protected void bindToView() {
		QuestionRequest request = clientFactory.daoRequestFactory().createQuestionRequest();
		
		request.findAndIncrement(questionId).fire(QUESTION_RECEIVED);
	}
	
	private void displayQuestion(QuestionProxy question) {
		display.setQuestionTitle(question.getTitle());
		display.setQuestionDescription(question.getDescription());
		display.setQuestionVotesCount(question.getVotesCount());
	}
}
