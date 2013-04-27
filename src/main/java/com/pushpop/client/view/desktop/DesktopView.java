package com.pushpop.client.view.desktop;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.pushpop.client.view.IView;
import com.pushpop.client.view.resources.Resources;


public abstract class DesktopView extends Composite implements IView {

    private EventBus eventBus;
    
    private Resources resources;

    public DesktopView(EventBus eventBus, Resources resources) {
        this.eventBus = eventBus;
        this.resources = resources;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }
    
    public void showError(String error) {
        if (getErrorDisplay() == null) {
            Window.alert(error);
        }
        getErrorDisplay().setText(error); 
    }
    
    public void showFailure(ServerFailure failure) {
        if (getErrorDisplay() == null) {
            Window.alert(failure.getMessage());
        }
        getErrorDisplay().setText(failure.getMessage());
    }
    
    public abstract HasText getErrorDisplay();
       
}
