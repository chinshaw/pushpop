package com.pushpop.client.view;

import java.util.List;

import com.pushpop.client.proxy.AnswerProxy;

public interface IAnswerListView extends IView {

    public interface Presenter {
        
        void onUpVote(AnswerProxy answer);
        
        void onEditAnswer(AnswerProxy answer);
        
        void onFlagAnswer(AnswerProxy answer);
        
        void onAddComment(AnswerProxy answer);
    }
    
    public void setPresenter(Presenter presenter);
    
    public void setAnswers(List<AnswerProxy> answers);
    
}
