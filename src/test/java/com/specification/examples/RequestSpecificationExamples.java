package com.specification.examples;

import static io.restassured.RestAssured.given;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpecificationExamples {
	
	static final String APIKEY = "75e3u4sgb2khg673cbv2gjup";
	static RequestSpecBuilder builder;
	static RequestSpecification requestSpec;


	@BeforeClass
	public static void init() {
		
		RestAssured.baseURI = "http://api.walmartlabs.com";
		RestAssured.basePath = "/v1";
		
		builder = new RequestSpecBuilder();
		builder.addQueryParam("query","phone");
		builder.addQueryParam("apiKey", APIKEY);
		builder.addQueryParam("format", "json");
		builder.addQueryParam("facet", "on");
		builder.addHeader("Accept", "*/*");
		
		requestSpec = builder.build();
		
	}

	/*
	 * When we have many queryparams, Headers, authorization fields to be added which is common for many requests
	 * We can add it using requestSpecBuilder for easy maintainence of the code
	 */
//	@Test
//	public void test001() {
//		given()
//				.queryParam("query","phone")
//				.queryParam("apiKey",APIKEY)
//				.queryParam("format","json")
//				.headers("Accept","*/*")
//				.when()
//				.get("/search")
//				.then()
//				.body("numItems", equalTo(10));
//		
//	} 
	
	@Test
	public void test001() {
		
		given()
		.spec(requestSpec)
		.when()
		.get("/search")
		.then()
		.log()
		.all();

	}
	
	@Test
	public void test002() {
		
		given()
		.spec(requestSpec)
		.when()
		.get("/search")
		.then()
		.log()
		.all();

	}

}
