package com.pushpop.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class AskQuestionPlace extends Place implements RequiresAuthentication {

    public static class Tokenizer implements PlaceTokenizer<AskQuestionPlace> {
        @Override
        public String getToken(AskQuestionPlace place) {
            return "";
        }

        @Override
        public AskQuestionPlace getPlace(String token) {
            return new AskQuestionPlace();
        }
    }
}
