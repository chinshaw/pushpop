package com.pushpop.client.activity;

import com.google.gwt.place.shared.Place;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.pushpop.client.IClientFactory;
import com.pushpop.client.activity.AbstractActivity;
import com.pushpop.client.events.AuthenticationEvent;
import com.pushpop.client.place.AuthenticationPlace;
import com.pushpop.client.place.QuestionsPlace;
import com.pushpop.client.proxy.PersonProxy;
import com.pushpop.client.view.IAuthenticationView;

public class AuthenticationActivity extends AbstractActivity<IAuthenticationView> implements IAuthenticationView.Presenter {

    private Place toGo;

    public AuthenticationActivity(AuthenticationPlace place, IClientFactory clientFactory, IAuthenticationView display) {
        this(place, clientFactory, display, place.getPlaceToGo());
    }

    public AuthenticationActivity(AuthenticationPlace place, IClientFactory clientFactory, IAuthenticationView display, Place toGo) {
        super(clientFactory, display);
        this.toGo = toGo;
    }

    @Override
    protected void bindToView() {
        display.setPresenter(this);
        display.reset();
    }

    @Override
    public void onAuthentication(String username, String password) {
        clientFactory.permissionsRequestFactory().createAuthenticationRequest().authenticate(username, password).fire(new Receiver<PersonProxy>() {

            @Override
            public void onSuccess(PersonProxy person) {
                clientFactory.setCurrentPerson(person);
                eventBus.fireEvent(new AuthenticationEvent(person));

                if (toGo != null) {
                    clientFactory.getPlaceController().goTo(toGo);
                } else {
                    clientFactory.getPlaceController().goTo(new QuestionsPlace());
                }
            }

            public void onFailure(ServerFailure failure) {
                display.showFailure(failure);
            }
        });
    }
}