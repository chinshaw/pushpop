package com.pushpop.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.pushpop.client.proxy.PersonProxy;

public class UserPlace extends Place {

    private Long id;
    
    public UserPlace() {
    }
    
    public UserPlace(PersonProxy person) {
        this.id = person.getId();
    }
    
    public UserPlace(Long id) {
        this.id = id;
    }
    
    public Long getPersonId() {
        return id;
    }
    
    
    public static class Tokenizer implements PlaceTokenizer<UserPlace> {
        
        @Override
        public String getToken(UserPlace place) {
            return place.getPersonId().toString();
        }

        @Override
        public UserPlace getPlace(String token) {
            if (token != null) {
                return new UserPlace(Long.parseLong(token));
            }
            return new UserPlace();
        }
    }
}
