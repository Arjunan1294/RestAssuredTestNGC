package com.spotify.oauth2.pojo.api;

import java.util.Properties;

public class ConfigLoader {

	private static Properties properties;
	private static ConfigLoader configloader;
	
	private ConfigLoader() {
		properties = PropertyUtils.propertyLoader("src/test/resources/config.properties");
	}
	
	
	public static ConfigLoader getInstance() {
		if(configloader==null) {
			configloader=new ConfigLoader();
		}
		return configloader;
	}
	
	public String getClientId() {
		String prop = properties.getProperty("client_id");
		if(prop!=null) return prop;
		else throw new RuntimeException("Property ClientId is not availale in prop file");
	}
	
	public String getClientSecret() {
		String prop = properties.getProperty("client_secret");
		if(prop!=null) return prop;
		else throw new RuntimeException("Property Clientsecret is not availale in prop file");
	}
	
	public String getgrantType() {
		String prop = properties.getProperty("grant_type");
		if(prop!=null) return prop;
		else throw new RuntimeException("Property grant_type is not availale in prop file");
	}
	
	public String getRefreshToken() {
		String prop = properties.getProperty("refresh_token");
		if(prop!=null) return prop;
		else throw new RuntimeException("Property refresh_token is not availale in prop file");
	}
	
	public String getUserId() {
		String prop = properties.getProperty("userId");
		if(prop!=null) return prop;
		else throw new RuntimeException("Property userId is not availale in prop file");
	}
	
}
