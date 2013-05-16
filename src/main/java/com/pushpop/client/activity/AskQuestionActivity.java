package com.pushpop.client.activity;

import java.util.Set;
import java.util.logging.Logger;

import javax.validation.ConstraintViolation;

import com.google.gwt.core.shared.GWT;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.pushpop.client.IClientFactory;
import com.pushpop.client.proxy.QuestionProxy;
import com.pushpop.client.service.DaoRequestFactory.QuestionRequest;
import com.pushpop.client.view.IAskQuestionView;

public class AskQuestionActivity extends AbstractActivity<IAskQuestionView>
		implements IAskQuestionView.Presenter {

	private static final Logger logger = Logger
			.getLogger(AskQuestionActivity.class.getName());

	private QuestionRequest context;

	public AskQuestionActivity(IClientFactory clientFactory,
			IAskQuestionView display) {
		super(clientFactory, display);
		context = daoRequestFactory().createQuestionRequest();
	}

	@Override
	protected void bindToView() {
		display.setPresenter(this);

		QuestionProxy question = context.create(QuestionProxy.class);
		context.save(question);
		display.getEditorDriver().edit(question, context);
	}

	@Override
	public void onSubmitQuestion() {

		display.getEditorDriver().flush().fire(new Receiver<Void>() {

			@Override
			public void onSuccess(Void response) {
				GWT.log("SAVED QUESTION");
			}

			public void onFailure(ServerFailure error) {
				GWT.log("Error occurred while processing " + error.getMessage());
			}

			public void onConstraintViolation(
					Set<ConstraintViolation<?>> violations) {
				super.onConstraintViolation(violations);

				for (ConstraintViolation<?> violation : violations) {
					logger.info(violation.getMessage());
				}
			}

		});
	}
}
