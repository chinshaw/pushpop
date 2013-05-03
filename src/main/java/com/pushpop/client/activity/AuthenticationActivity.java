package com.pushpop.client.activity;

<<<<<<< HEAD
=======
import com.google.gwt.core.shared.GWT;
>>>>>>> 9629e6d9c7b32e65882d3bcb6996414b8ba5ea22
import com.google.gwt.place.shared.Place;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.pushpop.client.IClientFactory;
import com.pushpop.client.events.AuthenticationEvent;
import com.pushpop.client.place.AuthenticationPlace;
<<<<<<< HEAD
import com.pushpop.client.place.QuestionsPlace;
=======
>>>>>>> 9629e6d9c7b32e65882d3bcb6996414b8ba5ea22
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
<<<<<<< HEAD
                    clientFactory.getPlaceController().goTo(toGo);
                } else {
                    clientFactory.getPlaceController().goTo(new QuestionsPlace());
=======
                    GWT.log("GOing to  anew place");
                    clientFactory.getPlaceController().goTo(toGo);
>>>>>>> 9629e6d9c7b32e65882d3bcb6996414b8ba5ea22
                }
            }

            public void onFailure(ServerFailure failure) {
                display.showFailure(failure);
            }
        });
    }
}