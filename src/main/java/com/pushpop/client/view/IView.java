package com.pushpop.client.view;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public interface IView extends IsWidget {

    
    public void showError(String error);
    
    public void showFailure(ServerFailure failure);
    
    public void reset();
}
