package com.pushpop.client.view;

public interface IAuthenticationView extends IView {
    
    public interface Presenter {
        
        public void onAuthentication(String username, String password);
        
    }
    
    public abstract void setPresenter(Presenter presenter);

    public abstract String getUserName();
    
    public abstract String getPassword();
}
