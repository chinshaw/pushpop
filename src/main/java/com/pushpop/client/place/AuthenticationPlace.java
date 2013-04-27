package com.pushpop.client.place;

import java.util.HashMap;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.pushpop.client.IClientFactory;

public class AuthenticationPlace extends Place {

    
    private Place ifSuccessfulPlace = null;
    
    public AuthenticationPlace() {
        
    }
    
    public AuthenticationPlace(Place ifSuccessfulPlace) {
        this.ifSuccessfulPlace = ifSuccessfulPlace;
    }
    
    public Place getPlaceToGo() {
        return ifSuccessfulPlace;
    }
    
    public static class Tokenizer implements PlaceTokenizer<AuthenticationPlace> {
        @Override
        public String getToken(AuthenticationPlace place) {
            if (place.getPlaceToGo() != null) {
                return "toTo=" + IClientFactory.INSTANCE.getPlaceHistoryMapper().getToken(place);
            }
            return "";
        }

        @Override
        public AuthenticationPlace getPlace(String token) {
            HashMap<String, String> parameterMap = PlaceUtils.tokenToMap(token);
            
            Place place = IClientFactory.INSTANCE.getPlaceHistoryMapper().getPlace(parameterMap.get("toGo"));
            return new AuthenticationPlace(place);
        }
    }
}
