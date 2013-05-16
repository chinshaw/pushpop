package com.pushpop.client.utils;

import com.google.gwt.core.client.JavaScriptObject;

public class Pagedown extends JavaScriptObject {

	
	protected Pagedown() {	
	}
	
	private static final  native void init() /*-{
		this.converter = new $wnd.Markdown.Converter();
	}-*/;
	
	public native final String convertToHtml(String wiki) /*-{
		return converter.makeHtml(wiki);
	}-*/;
	
	public static final Pagedown create() {
		init();
		return JavaScriptObject.createObject().cast();
	}
}
