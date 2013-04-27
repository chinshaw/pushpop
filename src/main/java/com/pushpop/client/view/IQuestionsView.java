package com.pushpop.client.view;

import com.google.gwt.view.client.HasData;
import com.pushpop.client.proxy.QuestionProxy;

public interface IQuestionsView extends IView {

    public interface Presenter {

        public void doSort(QuestionSort sortOrder);
    
    }

    public enum QuestionSort {
        INTERESTING, HOT, WEEK, MONTH
    }

    public HasData<QuestionProxy> getQuestionsDisplay();

}
