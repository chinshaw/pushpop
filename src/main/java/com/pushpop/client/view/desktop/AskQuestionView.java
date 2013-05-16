package com.pushpop.client.view.desktop;


import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.pushpop.client.proxy.QuestionProxy;
import com.pushpop.client.view.IAskQuestionView;
import com.pushpop.client.view.resources.Resources;
import com.pushpop.client.view.widgets.WikiEditor;

public class AskQuestionView extends DesktopView implements IAskQuestionView {

    interface QuestionEditorDriver extends RequestFactoryEditorDriver<QuestionProxy, AskQuestionView> {
    }
    
    private Logger logger = Logger.getLogger(AskQuestionView.class.getName());
    
    @UiTemplate(value = "AskQuestion.ui.xml")
    interface AskQuestionUiBinder extends UiBinder<Widget, AskQuestionView> {
    }

    private static QuestionEditorDriver editorDriver = GWT.create(QuestionEditorDriver.class);

    private static AskQuestionUiBinder uiBinder = GWT.create(AskQuestionUiBinder.class);

    private Presenter presenter;
    
    @UiField 
    TextBox title;

    @UiField
    @Editor.Path("description")
    WikiEditor wikiEditor;

    
    public AskQuestionView(EventBus eventBus, Resources resources) {
        super(eventBus, resources);
        
        initWidget(uiBinder.createAndBindUi(this));
        
        editorDriver.initialize(this);
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
    
    @Override
    public void reset() {

    }

    @Override
    public Widget asWidget() {
        return this;
    }
    
    @UiHandler("submitQuestion")
    void onSubmitQuestion(ClickEvent click) {
        presenter.onSubmitQuestion();
    }
    
    /**
     * Get the question editor used to submit the question.
     * @return
     */
    @Override
    public RequestFactoryEditorDriver<QuestionProxy, ?> getEditorDriver() {
        return editorDriver;
    }

    @Override
    public HasText getErrorDisplay() {
        // TODO Auto-generated method stub
        return null;
    }
}