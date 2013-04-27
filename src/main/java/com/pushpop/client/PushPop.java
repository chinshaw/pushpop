package com.pushpop.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
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

    private IClientFactory clientFactory;

    private MasterLayoutPanel masterLayoutPanel;

    @Override
    public void onModuleLoad() {
        clientFactory = IClientFactory.INSTANCE;
        masterLayoutPanel = clientFactory.getMasterLayoutPanel();

        RootLayoutPanel.get().add(masterLayoutPanel);

        StyleInjector.inject(ResourcesFactory.getResources().style().getText());

        PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(clientFactory.getPlaceHistoryMapper());
        PlaceController placeController = clientFactory.getPlaceController();

        // Start PlaceHistoryHandler with our PlaceHistoryMapper
        historyHandler.register(placeController, clientFactory.getEventBus(), new QuestionsPlace());
        historyHandler.handleCurrentHistory();

        // We don't have access to the JSESSIONID so we will just ask the server
        // if there is a valid user.
        checkForValidSession();
    }

    private void checkForValidSession() {
        clientFactory.permissionsRequestFactory().createAuthenticationRequest().getCurrentPersionFromSession().fire(new Receiver<PersonProxy>() {

            @Override
            public void onSuccess(PersonProxy response) {
                clientFactory.setCurrentPerson(response);
                clientFactory.getEventBus().fireEvent(new AuthenticationEvent(response));
            }

            public void onFailure(ServerFailure failure) {
                GWT.log("No valid sesion yet");
            }
        });
    }
}