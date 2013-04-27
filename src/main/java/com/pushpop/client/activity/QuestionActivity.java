package com.pushpop.client.activity;

import com.pushpop.client.IClientFactory;
import com.pushpop.client.place.QuestionPlace;
import com.pushpop.client.view.IQuestionView;

public class QuestionActivity extends AbstractActivity<IQuestionView> implements IQuestionView.Presenter {

    public QuestionActivity(QuestionPlace place, IClientFactory clientFactory, IQuestionView display) {
        super(clientFactory, display);
    }

    @Override
    protected void bindToView() {
        // TODO Auto-generated method stub
    }

}
