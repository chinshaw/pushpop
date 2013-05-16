package com.pushpop.client.view;

import com.google.gwt.editor.client.Editor;
import com.pushpop.client.proxy.QuestionProxy;

public interface IQuestionView extends IView, Editor<QuestionProxy> {

    public interface Presenter {
        
    }
    
    public abstract void setQuestionTitle(String title);
    
    public abstract void setQuestionDescription(String description);

	public abstract void setQuestionVotesCount(Integer votesCount);
}
