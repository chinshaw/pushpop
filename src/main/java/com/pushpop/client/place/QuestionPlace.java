package com.pushpop.client.place;

import java.util.HashMap;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class QuestionPlace extends Place {

    
    public static String QuestionIdToken = "Q";
    
    private Long questionId;
    
    public QuestionPlace(String questionId) {
        this(Long.parseLong(questionId));
    }
    
    public QuestionPlace(Long questionId) {
        this.questionId = questionId;
    }
    
    public Long getQuestionId() {
        return questionId;
    }
    
    public static class Tokenizer implements PlaceTokenizer<QuestionPlace> {
        
        @Override
        public String getToken(QuestionPlace place) {
<<<<<<< HEAD
            return QuestionIdToken + "=" + place.getQuestionId();
=======
            return QuestionIdToken + place.getQuestionId();
>>>>>>> 9629e6d9c7b32e65882d3bcb6996414b8ba5ea22
        }

        @Override
        public QuestionPlace getPlace(String token) {
            HashMap<String, String> parameterMap = PlaceUtils.tokenToMap(token);
<<<<<<< HEAD
=======
            
>>>>>>> 9629e6d9c7b32e65882d3bcb6996414b8ba5ea22
            return new QuestionPlace(parameterMap.get(QuestionIdToken));
        }
    }
}
