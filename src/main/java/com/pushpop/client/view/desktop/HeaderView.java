package com.pushpop.client.view.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.pushpop.client.view.IHeaderView;
import com.pushpop.client.view.resources.Resources;

public class HeaderView extends DesktopView implements IHeaderView {

    private static HeaderUiBinder uiBinder = GWT.create(HeaderUiBinder.class);

    interface HeaderUiBinder extends UiBinder<Widget, HeaderView> {
    }
    
    @UiField
    Anchor profileLink;
    
    @UiField
    SpanElement reputationScore;
    
    @UiField
    SpanElement goldBadgeCount;
    
    @UiField
    SpanElement silverBadgeCount;
    
    @UiField
    SpanElement bronzeBadgeCount;
    
    @UiField
    SpanElement viewProfile;
    
    private Presenter presenter;
    
    public HeaderView(EventBus eventBus, Resources resources) {
        super(eventBus, resources);        
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void reset() {
        // TODO Auto-generated method stub
    }
    
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
    
    public void setPersonCommonName(String commonName) {
        profileLink.setText(commonName);
    }
    
    public void setReputationScore(int score) {
        reputationScore.setInnerText(Integer.toString(score));
    }
    
    public void setGoldBadgeCount(int goldBadgeCount) {
        this.goldBadgeCount.setInnerText(Integer.toString(goldBadgeCount));
    }
    
    public void setSilverBadgeCount(int silverBadgeCount) {
        this.silverBadgeCount.setInnerText(Integer.toString(silverBadgeCount));
    }
    
    public void setBronzeBadgeCount(int bronzeBadgeCount) {
        this.bronzeBadgeCount.setInnerText(Integer.toString(bronzeBadgeCount));
    }
    
    @UiHandler("profileLink")
    void onViewProfile(ClickEvent click) {
        presenter.onViewProfile();
    }
    
    @Override
    public void setProfileName(String profileName) {
        profileLink.setText(profileName);
    }

    @Override
    public HasText getErrorDisplay() {
        // TODO Auto-generated method stub
        return null;
    }
}
