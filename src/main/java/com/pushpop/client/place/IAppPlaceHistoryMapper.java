package com.pushpop.client.place;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;


@WithTokenizers( {
    AskQuestionPlace.Tokenizer.class,
    AuthenticationPlace.Tokenizer.class,
    QuestionsPlace.Tokenizer.class,
    QuestionPlace.Tokenizer.class,
    TagsPlace.Tokenizer.class

})
public interface IAppPlaceHistoryMapper extends PlaceHistoryMapper {

}
