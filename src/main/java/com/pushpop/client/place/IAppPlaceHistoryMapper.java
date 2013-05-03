package com.pushpop.client.place;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers( {
    QuestionsPlace.Tokenizer.class,
<<<<<<< HEAD
    QuestionPlace.Tokenizer.class,
=======
>>>>>>> 9629e6d9c7b32e65882d3bcb6996414b8ba5ea22
    AskQuestionPlace.Tokenizer.class,
    AuthenticationPlace.Tokenizer.class
})
public interface IAppPlaceHistoryMapper extends PlaceHistoryMapper {

}
