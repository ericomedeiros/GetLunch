package com.getLunch.backend.utils;


import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class ClientUtility {
	
	private static final String PROVIDER_URL = 
	        "127.0.0.1:1099";
	 
	    private static final String JNP_INTERFACES = 
	        "org.jboss.naming";
	 
	    private static final String INITIAL_CONTEXT_FACTORY_S = 
	        "org.jnp.interfaces.NamingContextFactory";
	 
	    private static Context initialContext;
	 
	    public static Context getInitialContextForClient() throws NamingException {
	        if (initialContext == null) {
	        	
	        	Properties prop = new Properties();
	        	
	            prop.setProperty(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY_S);
	            prop.setProperty(Context.URL_PKG_PREFIXES, JNP_INTERFACES);
	            prop.setProperty(Context.PROVIDER_URL, PROVIDER_URL);
	            
	            initialContext = new InitialContext(prop);
	        }
	        return initialContext;
	    }

}
