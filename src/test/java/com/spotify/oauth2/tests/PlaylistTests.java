package com.spotify.oauth2.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.spotify.oauth2.pojo.Error;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.pojo.api.DataLoader;
import com.spotify.oauth2.pojo.api.StatusCode;
import com.spotify.oauth2.pojo.api.applicationApi.PlaylistApi;

import static com.spotify.oauth2.pojo.api.SpecBuilder.*;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class PlaylistTests extends BaseTest{
	
	
	public Playlist playlistBuilder(String name, String description, boolean _public) {
	return new Playlist()
		.setName("New Playlist")
		.setDescription("New playlist description")
		.setPublic(false);
	}
	
	public void assertPlayListEqual(Playlist responsePlayList, Playlist requestPlaylist) {
		assertThat(responsePlayList.getName(), equalTo(requestPlaylist.getName()));
		assertThat(responsePlayList.getDescription(), equalTo(requestPlaylist.getDescription()));
		assertThat(responsePlayList.getPublic(), equalTo(requestPlaylist.getPublic()));
	}
	
	public void assertStatusCode(int actualStatusCode, int expectedStatusCode) {
		assertThat(actualStatusCode,equalTo(expectedStatusCode));
	}

	@Test
	public void shouldBeAbleToCreateAPlaylist() {
		Playlist requestPlaylist = playlistBuilder("New Playlist", "New playlist description", false);
		
		/*Playlist requestPlaylist = new Playlist();
		requestPlaylist.setName("New Playlist");
		requestPlaylist.setDescription("New playlist description");
		requestPlaylist.setPublic(false);*/
		Response response = PlaylistApi.post(requestPlaylist);
		//assertThat(response.statusCode(),equalTo(201));
		assertStatusCode(response.statusCode(),StatusCode.CODE_201.getCode());
		Playlist responsePlayList = response.as(Playlist.class);
	/*	Playlist responsePlayList = given().spec(getRequestSpec()).body(reqPlaylist)
		.when().post("/users/31bjfwnggamenylyl7nqwegspcuq/playlists")
		.then().spec(getResponseSpec())
		.assertThat().statusCode(201)
		.extract()
		.response()
		.as(Playlist.class);*/
		
		assertPlayListEqual(responsePlayList,requestPlaylist);
		
		/*assertThat(responsePlayList.getName(), equalTo(requestPlaylist.getName()));
		assertThat(responsePlayList.getDescription(), equalTo(requestPlaylist.getDescription()));
		assertThat(responsePlayList.getPublic(), equalTo(requestPlaylist.getPublic()));*/
		
		/*.body("name", equalTo("New Playlist"),
				"description",equalTo("New playlist description"),
				"public",equalTo(false));*/
		
	}
	
	@Test
	public void shouldBeAbleToGetAPlaylist() {
		Playlist reqPlaylist = new Playlist();
		reqPlaylist.setName("New Playlist");
		reqPlaylist.setDescription("New playlist description");
		reqPlaylist.setPublic(false);
		
		
		Response response = PlaylistApi.get(DataLoader.getInstance().getPlayistId());
		assertThat(response.statusCode(),equalTo(200));
		
		Playlist responsePlayList = response.as(Playlist.class);
		
		/*Playlist responsePlayList = given().spec(getRequestSpec())
		.when().get("playlists/17Ssp3GvfgwtNDyH0TA2ym")
		.then().spec(getResponseSpec())
		.assertThat().statusCode(200)
		.extract()
		.response()
		.as(Playlist.class);*/
		assertThat(responsePlayList.getName(), equalTo(reqPlaylist.getName()));
		assertThat(responsePlayList.getDescription(), equalTo(reqPlaylist.getDescription()));
		assertThat(responsePlayList.getPublic(), equalTo(reqPlaylist.getPublic()));
		
	}
	
	@Test
	public void shouldBeAbleToUpdateAPlaylist() {
		Playlist requestPlaylist = new Playlist();
		requestPlaylist.setName("New Playlist");
		requestPlaylist.setDescription("New playlist description");
		requestPlaylist.setPublic(false);
		
		
		Response response = PlaylistApi.update(requestPlaylist, DataLoader.getInstance().getUpdatePlaylistId());
		assertThat(response.statusCode(),equalTo(200));
		
	/*	given().spec(getRequestSpec())
		.body(requestPlaylist)
		.when().put("playlists/17Ssp3GvfgwtNDyH0TA2ym")
		.then().spec(getResponseSpec())
		.assertThat().statusCode(200);*/
	}
	
	
	@Test
	public void shouldNotBeAbleToCreateAPlaylistWithoutName() {
		Playlist requestPlaylist = new Playlist();
		requestPlaylist.setName("");
		requestPlaylist.setDescription("New playlist description");
		requestPlaylist.setPublic(false);
		
		Response response = PlaylistApi.post(requestPlaylist);
		
		assertThat(response.statusCode(),equalTo(400));
		
		Error error = response.as(Error.class);
		
		
		/*com.spotify.oauth2.pojo.Error error = given().spec(getRequestSpec()).body(requestPlaylist)
		.when().post("/users/31bjfwnggamenylyl7nqwegspcuq/playlists")
		.then().spec(getResponseSpec())
		.assertThat().statusCode(400)
		.extract()
		.response()
		.as(com.spotify.oauth2.pojo.Error.class);*/
		assertThat(error.getError().getStatus(), equalTo(400));
		assertThat(error.getError().getMessage(), equalTo("Missing required field: name"));
	/*	.body("error.status", equalTo(400),
				"error.message",equalTo("Missing required field: name"));*/
		
	}
	
	@Test
	public void shouldNotBeAbleToCreateAPlaylistWithExpiredToken() {
		Playlist requestPlaylist = new Playlist();
		requestPlaylist.setName("New Playlist");
		requestPlaylist.setDescription("New playlist description");
		requestPlaylist.setPublic(false);
		
		
Response response = PlaylistApi.post("12345", requestPlaylist);
		
		assertThat(response.statusCode(),equalTo(401));
		
		Error error = response.as(Error.class);
		
		
		/*Error error = given().
		baseUri("https://api.spotify.com")
		.basePath("/v1")
		.header("Authorization", "Bearer "+"12345")
		.contentType(ContentType.JSON).log().all()
		.body(reqPlaylist)
		.when().post("/users/31bjfwnggamenylyl7nqwegspcuq/playlists")
		.then().spec(getResponseSpec())
		.assertThat().statusCode(401)
		.extract()
		.response()
		.as(com.spotify.oauth2.pojo.Error.class);*/
		assertThat(error.getError().getStatus(), equalTo(401));
		assertThat(error.getError().getMessage(), equalTo("Invalid access token"));
		
		
	}
	
	
}
