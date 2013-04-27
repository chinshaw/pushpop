package com.pushpop.client.view.desktop;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.pushpop.client.proxy.QuestionProxy;
import com.pushpop.client.view.IQuestionView;
import com.pushpop.client.view.resources.Resources;

public class QuestionView extends DesktopView implements IQuestionView {

    interface QuestionEditorDriver extends RequestFactoryEditorDriver<QuestionProxy, QuestionView> {
    }
    
    private QuestionEditorDriver editorDriver = GWT.create(QuestionEditorDriver.class);

    @UiTemplate(value = "Question.ui.xml")
    interface ViewBinder extends UiBinder<Widget, QuestionView> {
    }
    
    public QuestionView(EventBus eventBus, Resources resources) {
        super(eventBus, resources);
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
   
}
