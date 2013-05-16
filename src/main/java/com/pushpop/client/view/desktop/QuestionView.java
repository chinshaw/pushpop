package com.pushpop.client.view.desktop;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.pushpop.client.proxy.QuestionProxy;
import com.pushpop.client.view.IQuestionView;
import com.pushpop.client.view.resources.Resources;
import com.pushpop.client.view.widgets.WikiTextViewer;

public class QuestionView extends DesktopView implements IQuestionView {

	interface QuestionEditorDriver extends
			RequestFactoryEditorDriver<QuestionProxy, QuestionView> {
	}

	private QuestionEditorDriver editorDriver = GWT
			.create(QuestionEditorDriver.class);

	@UiTemplate(value = "Question.ui.xml")
	interface ViewBinder extends UiBinder<Widget, QuestionView> {
	}

	private static ViewBinder uiBinder = GWT.create(ViewBinder.class);

	@UiField
	AnchorElement questionTitle;

	@UiField
	WikiTextViewer questionDescription;
	
	@UiField
	SpanElement voteCount;

	public QuestionView(EventBus eventBus, Resources resources) {
		super(eventBus, resources);
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
	}

	public RequestFactoryEditorDriver<QuestionProxy, QuestionView> getEditorDriver() {
		return editorDriver;
	}

	@Override
	public HasText getErrorDisplay() {
		return null;
	}

	public void setQuestionTitle(String title) {
		questionTitle.setInnerText(title);
	}

	@Override
	public void setQuestionDescription(String description) {
		questionDescription.setText(description);
	}

	@Override
	public void setQuestionVotesCount(Integer votesCount) {
		voteCount.setInnerText(votesCount.toString());
	}
}
