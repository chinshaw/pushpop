package com.pushpop.client.view.desktop;

import java.util.List;

import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HasText;
import com.google.web.bindery.event.shared.EventBus;
import com.pushpop.client.proxy.AnswerProxy;
import com.pushpop.client.view.IAnswerListView;
import com.pushpop.client.view.resources.Resources;

public class AnswersCellList extends DesktopView implements IAnswerListView {
    
    
    @UiField 
    HeadingElement totalAnswers;
    
    
    private Presenter presenter;
    
    @UiField 
    AnchorElement sortBylastActivity;
    
    @UiField
    AnchorElement sortByHistorical;

    
    public AnswersCellList(EventBus eventBus, Resources resources) {
        super(eventBus, resources);
    
        
        
    }
    
    @Override
    public void reset() {
        // TODO Auto-generated method stub

    }

    @Override
    public void setAnswers(List<AnswerProxy> answers) {
        // TODO Auto-generated method stub
    }
    
    public void setTotalAnswerCount(int count) {
        totalAnswers.setInnerText("" + count + " Answers");
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
    
    @UiHandler("sortBylastActivity") 
    void onSortByLastActivity(ClickEvent click) {
        
    }

    @Override
    public HasText getErrorDisplay() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
