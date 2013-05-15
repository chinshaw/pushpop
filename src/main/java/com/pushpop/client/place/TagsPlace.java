package com.pushpop.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class TagsPlace extends Place {

	public TagsPlace() {
	}

	public static class Tokenizer implements PlaceTokenizer<TagsPlace> {

		@Override
		public String getToken(TagsPlace place) {
			return "";
		}

		@Override
		public TagsPlace getPlace(String token) {
			return new TagsPlace();
		}
	}
}