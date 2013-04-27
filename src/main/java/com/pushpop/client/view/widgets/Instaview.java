package com.pushpop.client.view.widgets;

import com.google.gwt.core.client.JavaScriptObject;

public class Instaview extends JavaScriptObject {

    
    protected Instaview() {
        
    }
    
    public static native String convert(String wikiText) /*-{
        return $wnd.InstaView.convert(wikiText);
    }-*/;
}