package com.pushpop.server;

import com.google.web.bindery.requestfactory.server.ExceptionHandler;
import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.server.ServiceLayerDecorator;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class PushPopRequestFactoryServlet extends RequestFactoryServlet {

    /**
     * SerialziationId 
     */
    private static final long serialVersionUID = 1L;
    
    public static class ServletExceptionHandler implements ExceptionHandler {

        @Override
        public ServerFailure createServerFailure(Throwable throwable) {
            throwable.printStackTrace();
            
            return new ServerFailure("Server Error: "
                    + (throwable == null ? null : throwable.getMessage()),
                    throwable.getClass().getName(), getStackTrace(throwable), true);
        }
    }
    
    
    static String getStackTrace(Throwable throwable) {
        StackTraceElement[] stack = throwable.getStackTrace();
        String exception = "";
        for (StackTraceElement s : stack) {
            exception = exception + s.toString() + "\n\t\t";
        }
        return exception;
    }
    
    public PushPopRequestFactoryServlet() {
        super(new ServletExceptionHandler());
    }
    
    public PushPopRequestFactoryServlet( ServiceLayerDecorator... serviceDecorators) {
        super(new ServletExceptionHandler(), serviceDecorators);
    }
    
}
