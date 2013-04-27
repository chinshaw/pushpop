/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pushpop.client.activity;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.ResettableEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.pushpop.client.IClientFactory;
import com.pushpop.client.proxy.PersonProxy;
import com.pushpop.client.service.DaoRequestFactory;
import com.pushpop.client.service.DaoRequestFactory.QuestionRequest;
import com.pushpop.client.view.IView;

/**
 * 
 * @author chris
 */
public abstract class AbstractActivity<V extends IView> implements Activity {

    protected static IClientFactory clientFactory;
    protected EventBus eventBus;
    protected AcceptsOneWidget parentPanel;
    protected final V display;

    protected final List<HandlerRegistration> handlers = new ArrayList<HandlerRegistration>();
    

    public AbstractActivity(IClientFactory _clientFactory, V display) {
        clientFactory = _clientFactory;
        this.display = display;
    }

    protected void setEventBus(ResettableEventBus eventBus) {
        this.eventBus = eventBus;
    }

    protected EventBus getEventBus() {
        return this.eventBus;
    }

    @Override
    public void start(AcceptsOneWidget parentPanel, com.google.gwt.event.shared.EventBus eventBus) {
        GWT.log("Setting display " + display + " to parent panel " + parentPanel);
        
        this.parentPanel = parentPanel;
        this.eventBus = eventBus;

        parentPanel.setWidget(display.asWidget());
        bindToView();
    }

    public AcceptsOneWidget getParentPanel() {
        return parentPanel;
    }

    public V getDisplay() {
        return display;
    }

    public IClientFactory getClientFactory() {
        return clientFactory;
    }

    @Override
    public String mayStop() {
        return null;
    }

    @Override
    public void onCancel() {
    }

    @Override
    public void onStop() {
    	cleanup();
        display.reset();
        // Dumb thing, this is a problem because the Actvity takes an older shared.EventBus
        ((com.google.gwt.event.shared.ResettableEventBus)eventBus).removeHandlers();
    }
    
	protected void addHandler(HandlerRegistration registration) {
		handlers.add(registration);
	}

    public List<HandlerRegistration> getHandlers() {
        return handlers;
    }

    protected void cleanup() {
        for (HandlerRegistration handler : handlers) {
            handler.removeHandler();
        }
    }

    /**
     * This function will be called immediately after adding the view to the
     * DOM. This function should be used to hook up click handlers, populate
     * HasData handlers and such.
     */
    protected abstract void bindToView();
    
    protected DaoRequestFactory daoRequestFactory() {
        return clientFactory.daoRequestFactory();
    }

    protected EventBus eventBus() {
        return clientFactory.getEventBus();
    }
    
    protected PlaceController placeController() {
        return clientFactory.getPlaceController();
    }

    protected PersonProxy currentPerson() {
        return clientFactory.getCurrentPerson();
    }
    
    protected QuestionRequest createQuestionRequestion() {
        return clientFactory.daoRequestFactory().createQuestionRequest();
    }
    
    protected PersonProxy getCurrentPerson() {
        return clientFactory.getCurrentPerson();
    }
    
    protected boolean isAuthenticated() {
        if (clientFactory.getCurrentPerson() == null) {
            return false;
        }
        return true;
    }
}