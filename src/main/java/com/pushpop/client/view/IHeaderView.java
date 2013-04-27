package com.pushpop.client.view;

public interface IHeaderView extends IView {

    public interface Presenter {

        void onViewProfile();
        
    }
    
    public void setPresenter(Presenter presenter);
    
    public void setPersonCommonName(String commonName);
    
    public void setReputationScore(int score);
    
    public void setGoldBadgeCount(int goldBadgeCount);
    
    public void setSilverBadgeCount(int silverBadgeCount);
    
    public void setBronzeBadgeCount(int bronzeBadgeCount);

    void setProfileName(String profileName);
    
    
}
