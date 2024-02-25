package com.spotify.oauth2.pojo.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static com.spotify.oauth2.pojo.api.Route.*;
public class SpecBuilder {
	
	static String access_token = "BQC_Y6ykexBJ763Tg2Z0_vKw-aJe5SXQXnhA0g_SP3c6atas-zn2r8DTgE2u4NXDszGUnCm6zmBCD0gRpKRSI68OwT4XucYR9qUsH8wjY2pv6iBJc7Iks80Tc-BZSCHk1bd5jmDWWnUa4k57IQTUI2PKJUWUuDm8TExciF0Rq8sw4rABX8HcquAjaGqTsoru3Z3Sm0zoGFJDEyPfBHDRoEBWfRRkIIta0tO2BRrwMS2TbBeoUjtKfPuqLFWBkB_cHPlApR_z0wxVJVfX";
	
	public static RequestSpecification getRequestSpec() {
		
		return new RequestSpecBuilder()
				.setBaseUri("https://api.spotify.com")
				.setBasePath(BASE_PATH)
				
				.setContentType(ContentType.JSON).log(LogDetail.ALL).build();
	}
	
	public static RequestSpecification getAccountRequestSpec() {
		
		return new RequestSpecBuilder()
				.setBaseUri("https://accounts.spotify.com")
				
				.setContentType(ContentType.URLENC).log(LogDetail.ALL).build();
	}
	
public static ResponseSpecification getResponseSpec() {
		return new ResponseSpecBuilder().log(LogDetail.ALL).build();
	}

}
