package com.spotify.oauth2.pojo.api;

import java.time.Instant;
import java.util.HashMap;

import javax.management.RuntimeErrorException;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class TokenManager {

	private static String access_token;
	private static Instant expiry_time;

	public synchronized static String getToken() {
		try {
			if (access_token == null || Instant.now().isAfter(expiry_time)) {
				System.out.println("Renewing token");
				Response response = renewToken();
				access_token = response.path("access_token");
				int expirtTimeInt = response.path("expires_in");
				expiry_time = Instant.now().plusSeconds(expirtTimeInt - 300);
			} else {
				System.out.println("Token is good to use");
			}
		} catch (Exception e) {
			throw new RuntimeException("Abort...Unable to return token");
		}
		return access_token;

	}

	private static Response renewToken() {
		HashMap<String, String> formParams = new HashMap<>();
		formParams.put("client_id", ConfigLoader.getInstance().getClientId());
		formParams.put("client_secret", ConfigLoader.getInstance().getClientSecret());
		formParams.put("refresh_token",
				ConfigLoader.getInstance().getRefreshToken());
		formParams.put("grant_type", ConfigLoader.getInstance().getgrantType());

		Response response = RestResource.postAccount(formParams);
		
	/*	Response response = given().baseUri("https://accounts.spotify.com").formParams(formParams)
				.contentType(ContentType.URLENC).log().all().when().post("/api/token").then()
				.spec(SpecBuilder.getResponseSpec()).extract().response();*/

		if (response.statusCode() != 200) {
			throw new RuntimeException("Abort...Token Failed");
		} else {
			// return response.path("access_token");
			return response;
		}

	}

}
