package com.pushpop.client.view;

import com.google.gwt.editor.client.Editor;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.pushpop.client.proxy.QuestionProxy;

public interface IAskQuestionView extends IView, Editor<QuestionProxy> {

    public interface Presenter {
        public abstract void onSubmitQuestion();
    }
    
    
    public abstract RequestFactoryEditorDriver<QuestionProxy, ?> getEditorDriver();
    
    public void setPresenter(Presenter presenter);
    

}
