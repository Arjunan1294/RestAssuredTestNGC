package com.spotify.oauth2.pojo.api;

import java.util.Properties;

public class DataLoader {

	private static Properties properties;
	private static DataLoader dataLoader;
	
	private DataLoader() {
		properties = PropertyUtils.propertyLoader("src/test/resources/data.properties");
	}
	
	
	public static DataLoader getInstance() {
		if(dataLoader==null) {
			dataLoader=new DataLoader();
		}
		return dataLoader;
	}
	
	public String getPlayistId() {
		String prop = properties.getProperty("get_playlist_id");
		if(prop!=null) return prop;
		else throw new RuntimeException("Property get_playlist_id is not availale in prop file");
	}
	
	public String getUpdatePlaylistId() {
		String prop = properties.getProperty("update_playlist_id");
		if(prop!=null) return prop;
		else throw new RuntimeException("Property update_playlist_id is not availale in prop file");
	}
	
	
	
}
