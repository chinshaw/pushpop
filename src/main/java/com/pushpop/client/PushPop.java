package com.pushpop.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.pushpop.client.events.AuthenticationEvent;
import com.pushpop.client.place.QuestionsPlace;
import com.pushpop.client.proxy.PersonProxy;
import com.pushpop.client.view.desktop.MasterLayoutPanel;
import com.pushpop.client.view.resources.ResourcesFactory;

public class PushPop implements EntryPoint {
    private IClientFactory clientFactory = IClientFactory.INSTANCE;;

    private MasterLayoutPanel masterLayoutPanel;
    
    final PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(clientFactory.getPlaceHistoryMapper());

    @Override
    public void onModuleLoad() {
        masterLayoutPanel = clientFactory.getMasterLayoutPanel();

        RootLayoutPanel.get().add(masterLayoutPanel);
        RootLayoutPanel.get().setStyleName(clientFactory.getResources().style().root());

        StyleInjector.inject(ResourcesFactory.getResources().style().getText());


        PlaceController placeController = clientFactory.getPlaceController();

        // Start PlaceHistoryHandler with our PlaceHistoryMapper
        historyHandler.register(placeController, clientFactory.getEventBus(), new QuestionsPlace());


        // We don't have access to the JSESSIONID so we will just ask the server
        // if there is a valid user.
        start();
    }
    
    private void start() {
        clientFactory.permissionsRequestFactory().createAuthenticationRequest().getCurrentPersionFromSession().fire(new Receiver<PersonProxy>() {

            @Override
            public void onSuccess(PersonProxy response) {
                clientFactory.setCurrentPerson(response);
                clientFactory.getEventBus().fireEvent(new AuthenticationEvent(response));
                historyHandler.handleCurrentHistory();
            }

            public void onFailure(ServerFailure failure) {
                historyHandler.handleCurrentHistory();
            }
        });
    }
}
