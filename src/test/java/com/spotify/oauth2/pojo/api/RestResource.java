package com.spotify.oauth2.pojo.api;

import static com.spotify.oauth2.pojo.api.SpecBuilder.getRequestSpec;
import static com.spotify.oauth2.pojo.api.Route.*;
import static com.spotify.oauth2.pojo.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

import java.util.HashMap;

import com.spotify.oauth2.pojo.Playlist;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestResource {
	//static String access_token = "BQC_Y6ykexBJ763Tg2Z0_vKw-aJe5SXQXnhA0g_SP3c6atas-zn2r8DTgE2u4NXDszGUnCm6zmBCD0gRpKRSI68OwT4XucYR9qUsH8wjY2pv6iBJc7Iks80Tc-BZSCHk1bd5jmDWWnUa4k57IQTUI2PKJUWUuDm8TExciF0Rq8sw4rABX8HcquAjaGqTsoru3Z3Sm0zoGFJDEyPfBHDRoEBWfRRkIIta0tO2BRrwMS2TbBeoUjtKfPuqLFWBkB_cHPlApR_z0wxVJVfX";

	
	public static Response post(String path, String token, Object requestPlaylist) {
		
		return given().spec(getRequestSpec()).body(requestPlaylist)
				.auth().oauth2(token)
				//.header("Authorization", "Bearer "+token)
				.when().post(path)
				.then().spec(getResponseSpec())
				.extract()
				.response();
	}
	///users/31bjfwnggamenylyl7nqwegspcuq/playlists
/*public static Response post(String token, Playlist requestPlaylist) {
		
		return given().spec(getRequestSpec()).body(requestPlaylist)
				.header("Authorization", "Bearer "+token)
				.when().post("/users/31bjfwnggamenylyl7nqwegspcuq/playlists")
				.then().spec(getResponseSpec())
				.extract()
				.response();
	}*/
	
	public static Response postAccount(HashMap<String, String> formParams) {
		return given(SpecBuilder.getAccountRequestSpec()).formParams(formParams)
				.when().post(API+TOKEN).then()
				.spec(SpecBuilder.getResponseSpec()).extract().response();
	}
	
	public static Response get(String path,String token) {
		return given().spec(getRequestSpec())
				.auth().oauth2(token)
				//.header("Authorization", "Bearer "+token)
				.when().get(path)
				.then().spec(getResponseSpec())
				.extract()
				.response();
	}
	//"playlists/"+playlistId
	
	public static Response update(String path, String token, Object requestPlaylist) {
		return given().spec(getRequestSpec())
				.auth().oauth2(token)
				//.header("Authorization", "Bearer "+token)
		.body(requestPlaylist)
		.when().put(path)
		.then().spec(getResponseSpec())
		.extract()
		.response();
	}
	//"playlists/"+playlistId
}
