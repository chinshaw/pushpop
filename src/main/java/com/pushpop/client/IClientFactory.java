package com.pushpop.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.web.bindery.event.shared.EventBus;
import com.pushpop.client.proxy.PersonProxy;
import com.pushpop.client.service.DaoRequestFactory;
import com.pushpop.client.service.PermissionsRequestFactory;
import com.pushpop.client.view.IAskQuestionView;
import com.pushpop.client.view.IAuthenticationView;
import com.pushpop.client.view.IHeaderView;
<<<<<<< HEAD
import com.pushpop.client.view.IQuestionView;
=======
>>>>>>> 9629e6d9c7b32e65882d3bcb6996414b8ba5ea22
import com.pushpop.client.view.IQuestionsView;
import com.pushpop.client.view.IUsersView;
import com.pushpop.client.view.desktop.MasterLayoutPanel;

public interface IClientFactory {

    public static final IClientFactory INSTANCE = GWT.create(IClientFactory.class);
    
    public abstract PersonProxy getCurrentPerson();

    public abstract void setCurrentPerson(PersonProxy person);
    
    public abstract EventBus getEventBus();

    public abstract PlaceController getPlaceController();

    public abstract DaoRequestFactory daoRequestFactory();
    
    public abstract PermissionsRequestFactory permissionsRequestFactory();
    
    //Views
    
    public abstract IQuestionsView getQuestionsView();

    public abstract IAskQuestionView getAskQuestionView();
    
    public abstract IUsersView getUsersView();

    public abstract  MasterLayoutPanel getMasterLayoutPanel();

    public abstract PlaceHistoryMapper getPlaceHistoryMapper();

    public abstract IHeaderView getHeaderView();

    public abstract IAuthenticationView getAuthenticationView();
<<<<<<< HEAD

    public abstract IQuestionView getQuestionView();
=======
>>>>>>> 9629e6d9c7b32e65882d3bcb6996414b8ba5ea22
    
    
    
}
