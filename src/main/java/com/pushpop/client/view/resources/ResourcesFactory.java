package com.pushpop.client.view.resources;

import com.google.gwt.core.client.GWT;

public class ResourcesFactory {

    private static Resources instance = null;

    private ResourcesFactory() {
    }

    public static final Resources getResources() {
        if (instance == null) {
            instance = GWT.create(Resources.class);
        }

        return instance;
    }
}