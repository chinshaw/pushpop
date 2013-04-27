package com.pushpop.client.activity;

import com.pushpop.client.events.AuthenticationEvent;
import com.google.gwt.place.shared.Place;
import com.pushpop.client.IClientFactory;
import com.pushpop.client.place.AuthenticationPlace;
import com.pushpop.client.place.UserPlace;
import com.pushpop.client.view.IHeaderView;

public class HeaderActivity extends AbstractActivity<IHeaderView> implements IHeaderView.Presenter, AuthenticationEvent.Handler {

    
    public HeaderActivity(Place place, IClientFactory clientFactory, IHeaderView display ) {
        super(clientFactory, display);
    }
    
    @Override
    protected void bindToView() {
        display.setPresenter(this);
        eventBus.addHandler(AuthenticationEvent.TYPE, this);
        
        
    }

    @Override
    public void onViewProfile() {
        if (isAuthenticated()) {
            clientFactory.getPlaceController().goTo(new UserPlace(getCurrentPerson()));
        } clientFactory.getPlaceController().goTo(new AuthenticationPlace());
    }

    @Override
    public void onUserAuthentication(AuthenticationEvent event) {
        if (event.getPerson() != null) {
            display.setProfileName(event.getPerson().getCommonName());
        }
    }
    
    
    
}
