package com.pushpop.client.view.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.pushpop.client.view.IAuthenticationView;
import com.pushpop.client.view.resources.Resources;

public class AuthenticationView extends DesktopView implements IAuthenticationView {

    @UiTemplate(value = "Authentication.ui.xml")
    interface ViewBinder extends UiBinder<Widget, AuthenticationView> {
    }
    
    private ViewBinder uiBinder = GWT.create(ViewBinder.class);
    
    private Presenter presenter;
    
    @UiField
    InputElement username;
    
    @UiField
    InputElement password;
    
    public AuthenticationView(EventBus eventBus, Resources resources) {
        super(eventBus, resources);
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void reset() {
    }
    
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
    
    
    @UiHandler("login")
    void onLoginSubmit(ClickEvent clickEvent) {
        assert(presenter != null); 
        
        if (getUserName() == null || password == null) {
            showError("Username and password are required fields");
        }
        
        presenter.onAuthentication(getUserName(), getPassword());
    }
    
    
    @Override
    public String getUserName() {
        return username.getValue();
    }
    
    @Override
    public String getPassword() {
        return password.getValue();
    }

    @Override
    public HasText getErrorDisplay() {
        // TODO Auto-generated method stub
        return null;
    }
}
