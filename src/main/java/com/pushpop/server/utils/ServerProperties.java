package com.pushpop.server.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * 
 * Singleton instance of the server properties.
 * 
 * @author chinshaw
 */
public class ServerProperties {

    /**
     * Our properties instance.
     */
    private static Properties props;

    /**
     * Location of where to find properties file.
     */
    private static final String WAR_PROPERTIES_FILE = "/META-INF/Configuration.properties";

    static {
        try {
            load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ServerProperties() {
    }

    /**
     * Returns the whole Properties instance. This can be used for instance with
     * the task engine that requires a properties instance to start.
     * 
     * @return all properties.
     */
    public static Properties getProperties() {
        return props;
    }

    /**
     * Generic getter function for any property.
     * 
     * @param key
     * @return value or null if not found.
     */
    public static String getProperty(String key) {
        return props.getProperty(key);
    }

    /**
     * By default this will return Ldap if not set up in the properties file.
     * 
     * @return
     */
    public static String getAuthenticationMethod() {
        String authMethod = getProperty("com.hp.vf.server.auth");
        authMethod = (authMethod == null) ? "Ldap" : authMethod;
        return authMethod;
    }


    public static final void load() throws IOException {        
        props = new Properties();
        
        InputStream stream = ServerProperties.class.getResourceAsStream(WAR_PROPERTIES_FILE);
        try {
            props.load(stream);
        } finally {
            stream.close();
        }
    }

}