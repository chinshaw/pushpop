package com.pushpop.client.view.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.pushpop.client.proxy.QuestionProxy;
import com.pushpop.client.view.IAskQuestionView;
import com.pushpop.client.view.resources.Resources;
import com.pushpop.client.view.widgets.Instaview;

public class AskQuestionView extends DesktopView implements IAskQuestionView {

    interface QuestionEditorDriver extends RequestFactoryEditorDriver<QuestionProxy, AskQuestionView> {
    }
    
    
    @UiTemplate(value = "AskQuestion.ui.xml")
    interface AskQuestionUiBinder extends UiBinder<Widget, AskQuestionView> {
    }

    private static QuestionEditorDriver editorDriver = GWT.create(QuestionEditorDriver.class);

    private static AskQuestionUiBinder uiBinder = GWT.create(AskQuestionUiBinder.class);

    private Presenter presenter;
    
    @UiField 
    TextBox title;
    
    
    @UiField
    TextArea description;
    
    public AskQuestionView(EventBus eventBus, Resources resources) {
        super(eventBus, resources);
      
        initWidget(uiBinder.createAndBindUi(this));
        
        description.addKeyUpHandler(new KeyUpHandler() {
            
            @Override
            public void onKeyUp(KeyUpEvent event) {
                String wikiText = description.getValue();
                String htmlText = Instaview.convert(wikiText);
                GWT.log("Wiki text is " + htmlText);
            }
        });
        
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