package com.pushpop.client.place;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers( {
    QuestionsPlace.Tokenizer.class,
    AskQuestionPlace.Tokenizer.class,
    AuthenticationPlace.Tokenizer.class
})
public interface IAppPlaceHistoryMapper extends PlaceHistoryMapper {

}
