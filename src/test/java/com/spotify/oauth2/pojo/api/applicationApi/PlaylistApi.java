package com.spotify.oauth2.pojo.api.applicationApi;

import static com.spotify.oauth2.pojo.api.SpecBuilder.getRequestSpec;
import static com.spotify.oauth2.pojo.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.pojo.api.ConfigLoader;
import com.spotify.oauth2.pojo.api.RestResource;
import static com.spotify.oauth2.pojo.api.Route.*;
import com.spotify.oauth2.pojo.api.TokenManager;

import io.restassured.response.Response;

public class PlaylistApi {
	//static String token = "BQC_Y6ykexBJ763Tg2Z0_vKw-aJe5SXQXnhA0g_SP3c6atas-zn2r8DTgE2u4NXDszGUnCm6zmBCD0gRpKRSI68OwT4XucYR9qUsH8wjY2pv6iBJc7Iks80Tc-BZSCHk1bd5jmDWWnUa4k57IQTUI2PKJUWUuDm8TExciF0Rq8sw4rABX8HcquAjaGqTsoru3Z3Sm0zoGFJDEyPfBHDRoEBWfRRkIIta0tO2BRrwMS2TbBeoUjtKfPuqLFWBkB_cHPlApR_z0wxVJVfX";

	
	public static Response post(Playlist requestPlaylist) {
		return RestResource.post(USERS+"/"+ConfigLoader.getInstance().getUserId()+PLAYLISTS, TokenManager.getToken(), requestPlaylist);
		/*return given().spec(getRequestSpec()).body(requestPlaylist)
				.header("Authorization", "Bearer "+access_token)
				.when().post("/users/31bjfwnggamenylyl7nqwegspcuq/playlists")
				.then().spec(getResponseSpec())
				.extract()
				.response();*/
	}
	
public static Response post(String token, Playlist requestPlaylist) {
	return RestResource.post(USERS+"/"+ConfigLoader.getInstance().getUserId()+PLAYLISTS, token, requestPlaylist);	
		/*return given().spec(getRequestSpec()).body(requestPlaylist)
				.header("Authorization", "Bearer "+token)
				.when().post("/users/31bjfwnggamenylyl7nqwegspcuq/playlists")
				.then().spec(getResponseSpec())
				.extract()
				.response();*/
	}
	
	public static Response get(String playlistId) {
		return RestResource.get(PLAYLISTS+"/"+playlistId, TokenManager.getToken());	
	
		/*return given().spec(getRequestSpec())
				.header("Authorization", "Bearer "+access_token)
				.when().get("playlists/"+playlistId)
				.then().spec(getResponseSpec())
				.extract()
				.response();*/
				//17Ssp3GvfgwtNDyH0TA2ym
	}
	
	public static Response update(Playlist requestPlaylist, String playlistId) {
		return RestResource.update(PLAYLISTS+"/17Ssp3GvfgwtNDyH0TA2ym", TokenManager.getToken(),requestPlaylist);	
		/*return given().spec(getRequestSpec())
				.header("Authorization", "Bearer "+access_token)
		.body(requestPlaylist)
		.when().put("playlists/17Ssp3GvfgwtNDyH0TA2ym")
		.then().spec(getResponseSpec())
		.extract()
		.response();*/
	}
	
}
