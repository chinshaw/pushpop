package com.pushpop.client.place;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.web.bindery.event.shared.EventBus;
import com.pushpop.client.IClientFactory;
import com.pushpop.client.activity.AskQuestionActivity;
import com.pushpop.client.activity.AuthenticationActivity;
import com.pushpop.client.activity.QuestionActivity;
import com.pushpop.client.activity.QuestionsActivity;

public class ContentActivityMapper implements ActivityMapper {

    private IClientFactory clientFactory;

    public ContentActivityMapper(IClientFactory clientFactory, EventBus eventBus) {
        this.clientFactory = clientFactory;
    }

    @Override
    public Activity getActivity(Place place) {
        if (place instanceof RequiresAuthentication) {
            if (clientFactory.getCurrentPerson() == null) {
                return new AuthenticationActivity(new AuthenticationPlace(place), clientFactory, clientFactory.getAuthenticationView(), place);
            }
        }
        
        if (place instanceof QuestionPlace) {
            return new QuestionActivity( (QuestionPlace) place, clientFactory, clientFactory.getQuestionView());
        }

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