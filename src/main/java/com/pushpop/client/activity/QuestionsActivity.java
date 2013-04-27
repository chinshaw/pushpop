package com.pushpop.client.activity;

import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.pushpop.client.IClientFactory;
import com.pushpop.client.place.QuestionsPlace;
import com.pushpop.client.proxy.QuestionProxy;
import com.pushpop.client.view.IQuestionsView;
import com.pushpop.client.view.IQuestionsView.QuestionSort;

public class QuestionsActivity extends AbstractActivity<IQuestionsView> {

    
    private int MAX_QUESTIONS = 40;
    
    
    private Receiver<List<QuestionProxy>> questionsReceiver = new Receiver<List<QuestionProxy>>() {

        @Override
        public void onSuccess(List<QuestionProxy> response) {
            // TODO Add caching
            QuestionsActivity.this.loadQuestionsToView(response);
        }
    };

    public QuestionsActivity(QuestionsPlace place, IClientFactory clientFactory, IQuestionsView display) {
        super(clientFactory, display);
    }

    @Override
    protected void bindToView() {
        daoRequestFactory().createQuestionRequest().findRange(0, 40).with(QuestionProxy.PROPERTIES).fire(questionsReceiver);
    }

    public void doSort(QuestionSort sortOrder) {
        Window.alert("TO DO ");
    }

    private void loadQuestionsToView(List<QuestionProxy> questions) {
        display.getQuestionsDisplay().setRowData(0, questions);
    }

}
