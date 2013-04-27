package com.pushpop.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class QuestionsPlace extends Place {

    public static class Tokenizer implements PlaceTokenizer<QuestionsPlace> {
        @Override
        public String getToken(QuestionsPlace place) {
            return "";
        }

        @Override
        public QuestionsPlace getPlace(String token) {
            return new QuestionsPlace();
        }
    }

}
