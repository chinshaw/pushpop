package com.pushpop.client.view.widgets;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;

public class TagEditor extends Composite {


	//<a class="post-tag" rel="tag" title="" href="/questions/tagged/java">java</a>
	public class TagWidget extends Composite {
		
		Anchor anchor = new Anchor();
		public TagWidget() {
			initWidget(anchor);
		}
	}
}
