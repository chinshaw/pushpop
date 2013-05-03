package com.pushpop.client.place;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.web.bindery.event.shared.EventBus;
import com.pushpop.client.IClientFactory;
import com.pushpop.client.activity.AskQuestionActivity;
import com.pushpop.client.activity.AuthenticationActivity;
<<<<<<< HEAD
import com.pushpop.client.activity.QuestionActivity;
=======
>>>>>>> 9629e6d9c7b32e65882d3bcb6996414b8ba5ea22
import com.pushpop.client.activity.QuestionsActivity;

public class ContentActivityMapper implements ActivityMapper {

    private IClientFactory clientFactory;

    public ContentActivityMapper(IClientFactory clientFactory, EventBus eventBus) {
        this.clientFactory = clientFactory;
    }

    @Override
    public Activity getActivity(Place place) {
<<<<<<< HEAD
        
=======
>>>>>>> 9629e6d9c7b32e65882d3bcb6996414b8ba5ea22
        if (place instanceof RequiresAuthentication) {
            if (clientFactory.getCurrentPerson() == null) {
                return new AuthenticationActivity(new AuthenticationPlace(place), clientFactory, clientFactory.getAuthenticationView(), place);
            }
        }
        
<<<<<<< HEAD
        if (place instanceof QuestionPlace) {
            return new QuestionActivity( (QuestionPlace) place, clientFactory, clientFactory.getQuestionView());
        }
        
=======
>>>>>>> 9629e6d9c7b32e65882d3bcb6996414b8ba5ea22
        if (place instanceof QuestionsPlace) {
            return new QuestionsActivity((QuestionsPlace) place, clientFactory, clientFactory.getQuestionsView());
        }
        
        if (place instanceof AskQuestionPlace) {
            return new AskQuestionActivity(clientFactory, clientFactory.getAskQuestionView());
        }
        
        if (place instanceof AuthenticationPlace) {
            return new AuthenticationActivity((AuthenticationPlace)place, clientFactory, clientFactory.getAuthenticationView());
        }

        return new QuestionsActivity(new QuestionsPlace(), clientFactory, clientFactory.getQuestionsView());
    }
}