package com.pushpop.client.view.widgets;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHTML;
import com.pushpop.client.utils.Pagedown;

public class WikiTextViewer extends Composite implements HasHTML {
	
	private final String CSS_CLASS = "wmd-preview";
	
	private HTML htmlContainer = new HTML("<div class=\"" + CSS_CLASS + "\"></div>");
	
	private final Pagedown pageDown = Pagedown.create();

	public WikiTextViewer() {
		initWidget(htmlContainer);
		//setStyleName(CSS_CLASS);
	}
	
	
	@Override
	public String getText() {
		return htmlContainer.getText();
	}

	@Override
	public void setText(String text) {
		setHTML(pageDown.convertToHtml(text));
	}

	@Override
	public String getHTML() {
		return htmlContainer.getHTML();
	}

	@Override
	public void setHTML(String html) {
		htmlContainer.setHTML(html);
	}

}
