package com.pushpop.client.activity;

import com.pushpop.client.IClientFactory;
import com.pushpop.client.place.TagsPlace;
import com.pushpop.client.view.ITagsView;

public class TagsActivity extends AbstractActivity<ITagsView> {

	public TagsActivity(TagsPlace place, IClientFactory clientFactory, ITagsView display) {
		super(clientFactory, display);
	}

	@Override
	protected void bindToView() {
		
	}

}
