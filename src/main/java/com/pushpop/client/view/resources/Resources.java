package com.pushpop.client.view.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface Resources extends ClientBundle {

    public interface Style extends CssResource {
        
        public String masterLayoutPanel();
        
        public String headerPanel();

        public String footerPanel();
        
        public String actionsPanel();
        
        public String topPanel();
        
        public String contentPanel();
        
        
        
    }
    
    /**
     * These are the obfuscated styles.
     * 
     * @return
     */
    @Source({ "default.css" })
    public Style style();
    
}
