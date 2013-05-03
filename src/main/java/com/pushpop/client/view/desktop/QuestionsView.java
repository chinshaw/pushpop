package com.pushpop.client.view.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.HasData;
import com.google.web.bindery.event.shared.EventBus;
import com.pushpop.client.proxy.QuestionProxy;
import com.pushpop.client.view.IQuestionsView;
import com.pushpop.client.view.resources.Resources;

public class QuestionsView extends DesktopView implements IQuestionsView {

    interface QuestionsViewUiBinder extends UiBinder<Widget, QuestionsView> {
    }
    
    private static QuestionsViewUiBinder uiBinder = GWT.create(QuestionsViewUiBinder.class);

    @UiField(provided = true)
    CellList<QuestionProxy> questionsMiniList;

    static Widget selectedtab = null;

    public QuestionsView(EventBus eventBus, Resources resources) {
        super(eventBus, resources);
        questionsMiniList = new CellList<QuestionProxy>(new QuestionCell());
        questionsMiniList.setEmptyListWidget(new Label("No Questions"));

        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void reset() {
        // TODO Auto-generated method stub
    }

    @Override
    public HasData<QuestionProxy> getQuestionsDisplay() {
        return questionsMiniList;
    }

    @UiHandler("sortByInteresting")
    void onSortByInteresting(ClickEvent click) {
        Anchor tab = (Anchor) click.getSource();
        switchTabs(tab);
    }

    @UiHandler("sortByHot")
    void onSortByHot(ClickEvent click) {
        Anchor tab = (Anchor) click.getSource();
        switchTabs(tab);
    }

    @UiHandler("sortByHotForWeek")
    void onSortByWeek(ClickEvent click) {
        Anchor tab = (Anchor) click.getSource();
        switchTabs(tab);
    }

    @UiHandler("sortByHotForMonth")
    void onSortyByMonth(ClickEvent click) {
        Anchor tab = (Anchor) click.getSource();
        switchTabs(tab);
    }

    private void switchTabs(Widget tab) {
        if (selectedtab != null) {
            tab.removeStyleName("youarehere");
        }
        tab.addStyleName("youarehere");
        selectedtab = tab;
    }

    @Override
    public HasText getErrorDisplay() {
        // TODO Auto-generated method stub
        return null;
    }
}