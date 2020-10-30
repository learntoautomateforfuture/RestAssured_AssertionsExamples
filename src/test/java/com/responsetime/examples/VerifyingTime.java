package com.responsetime.examples;

//Static Imports
import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.Test;

public class VerifyingTime {

	
	static RequestSpecBuilder builder;
	static RequestSpecification rspec;
	
	static ResponseSpecBuilder responsebuilder;
	static ResponseSpecification responseSpec;

	static Map<String,Object> responseHeaders = new HashMap<String,Object>();
	
	static final String APIKEY = "75e3u4sgb2khg673cbv2gjup";

	@BeforeClass
	public static void init() {
		RestAssured.baseURI = "http://api.walmartlabs.com";
		RestAssured.basePath = "/v1";
		
		builder = new RequestSpecBuilder();
		
		builder.addParam("query","phone");
		builder.addParam("format","json");
		builder.addParam("apiKey",APIKEY);
		
		rspec= builder.build();
		
		//Building response
		responseHeaders.put("Content-Type","application/json;charset=utf-8");
		
		responsebuilder= new ResponseSpecBuilder();
		responsebuilder.expectBody("numItems",equalTo(10));
		responsebuilder.expectStatusCode(200);
		responsebuilder.expectHeaders(responseHeaders);
		responsebuilder.expectResponseTime(lessThan(1L),TimeUnit.SECONDS);
		
		responseSpec= responsebuilder.build();
	
	}

	// 1)Get the time value
	@Test
	public void test001() {
	long responseTime=	given()
		.spec(rspec)
		.log()
		.all()
		.when()
		.get("/search")
		.timeIn(TimeUnit.SECONDS);
	
	System.out.println("The time taken is: "+responseTime+" seconds");
	
	given()
	.spec(rspec)
	.log()
	.all()
	.when()
	.get("/search")
	.then()
	.spec(responseSpec);		
	
	}

}
