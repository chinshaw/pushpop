package com.pushpop.client.place;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.web.bindery.event.shared.EventBus;
import com.pushpop.client.IClientFactory;
import com.pushpop.client.activity.HeaderActivity;

public class HeaderActivityMapper implements ActivityMapper {

    private final IClientFactory clientFactory;
    
    public HeaderActivityMapper(IClientFactory clientFactory, EventBus eventBus) {
        this.clientFactory = clientFactory;
    }
    
    @Override
    public Activity getActivity(Place place) {
        return new HeaderActivity(place, clientFactory, clientFactory.getHeaderView());
    }
}
