package com.pushpop.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.ResettableEventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.gwt.client.DefaultRequestTransport;
import com.pushpop.client.place.ContentActivityMapper;
import com.pushpop.client.place.HeaderActivityMapper;
import com.pushpop.client.place.IAppPlaceHistoryMapper;
import com.pushpop.client.proxy.PersonProxy;
import com.pushpop.client.service.DaoRequestFactory;
import com.pushpop.client.service.PermissionsRequestFactory;
import com.pushpop.client.view.IAskQuestionView;
import com.pushpop.client.view.IAuthenticationView;
import com.pushpop.client.view.IHeaderView;
import com.pushpop.client.view.IQuestionsView;
import com.pushpop.client.view.IUsersView;
import com.pushpop.client.view.desktop.AskQuestionView;
import com.pushpop.client.view.desktop.AuthenticationView;
import com.pushpop.client.view.desktop.HeaderView;
import com.pushpop.client.view.desktop.MasterLayoutPanel;
import com.pushpop.client.view.desktop.QuestionsView;
import com.pushpop.client.view.resources.Resources;
import com.pushpop.client.view.resources.ResourcesFactory;

public class ClientFactoryDesktop implements IClientFactory {
    
    /**
     * This is the current logged in user.
     */
    private PersonProxy person = null;

    protected static final ResettableEventBus eventBus = new ResettableEventBus(new SimpleEventBus());

    private static final PlaceController placeController = new PlaceController(eventBus);
    
    private static final DaoRequestFactory daoRequestFactory = GWT.create(DaoRequestFactory.class);
    
    private static final PermissionsRequestFactory permissionsRequestFactory = GWT.create(PermissionsRequestFactory.class);
    
    private final IAppPlaceHistoryMapper historyMapper = GWT.create(IAppPlaceHistoryMapper.class);
    
    
    private static final Resources resources = ResourcesFactory.getResources();
    private MasterLayoutPanel masterLayoutPanel = new MasterLayoutPanel(eventBus, resources);
    
    private IQuestionsView questionsView = new QuestionsView(eventBus, resources);
    
    private IAskQuestionView askQuestionView = new AskQuestionView(eventBus, resources);
    
    private IHeaderView headerView = new HeaderView(eventBus, resources);
    
    private IAuthenticationView authenticationView = new AuthenticationView(eventBus, resources);
    

    public ClientFactoryDesktop() {
        DefaultRequestTransport daoTransport = new DefaultRequestTransport();
        daoTransport.setRequestUrl("/pushpop/dao");
        daoRequestFactory.initialize(eventBus, daoTransport);
        
        DefaultRequestTransport permissionTransport = new DefaultRequestTransport();
        permissionTransport.setRequestUrl("/pushpop/permissions");
        permissionsRequestFactory.initialize(eventBus, permissionTransport);
        
        ContentActivityMapper centerActivityMapper = new ContentActivityMapper(this, eventBus);
        ActivityManager centerActivityManager = new ActivityManager(centerActivityMapper, eventBus);
        centerActivityManager.setDisplay(masterLayoutPanel.getContentPanel());
        
        
        HeaderActivityMapper headerActivityMapper = new HeaderActivityMapper(this, eventBus);
        ActivityManager headerActivityManager = new ActivityManager(headerActivityMapper, eventBus);
        headerActivityManager.setDisplay(masterLayoutPanel.getHeaderPanel());
    }

    @Override
    public PersonProxy getCurrentPerson() {
        return person;
    }

    @Override
    public void setCurrentPerson(PersonProxy person) {
        this.person = person;
    }
    
    @Override
    public EventBus getEventBus() {
        return eventBus;
    }

    @Override
    public PlaceController getPlaceController() {
        return placeController;
    }

    @Override
    public DaoRequestFactory daoRequestFactory() {
        return daoRequestFactory;
    }

    @Override
    public IQuestionsView getQuestionsView() {
        return questionsView;
    }

    @Override
    public IAskQuestionView getAskQuestionView() {
        return askQuestionView;
    }

    @Override
    public IUsersView getUsersView() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MasterLayoutPanel getMasterLayoutPanel() {
        return masterLayoutPanel;
    }

    @Override
    public PlaceHistoryMapper getPlaceHistoryMapper() {
        return historyMapper;
    }

    @Override
    public IHeaderView getHeaderView() {
        return headerView;
    }

    @Override
    public IAuthenticationView getAuthenticationView() {
        return authenticationView;
    }

    @Override
    public PermissionsRequestFactory permissionsRequestFactory() {
        return permissionsRequestFactory;
    }

}
