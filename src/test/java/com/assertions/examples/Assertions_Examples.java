package com.assertions.examples;

import static io.restassured.RestAssured.given;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;

public class Assertions_Examples {
	static final String APIKEY = "75e3u4sgb2khg673cbv2gjup";

	@BeforeClass
	public static void init() {

		RestAssured.baseURI = "http://api.walmartlabs.com";
		RestAssured.basePath = "/v1";
	}

	// 1) Verify if the number of items is equal to 10
	@Test
	public void test001() {
		given()
				.queryParam("query","phone")
				.queryParam("apiKey",APIKEY)
				.queryParam("format","json")
				.when()
				.get("/search")
				.then()
				.body("numItems", equalTo(10));
		
	}

	// 2) Verify Query
	@Test
	public void test002() {
		given()
		.queryParam("query","phone")
		.queryParam("apiKey",APIKEY)
		.queryParam("format","json")
		.when()
		.get("/search")
		.then()
		.body("query", equalToIgnoringCase("PHONE"));
	}


	// 3) Check Single Name in ArrayList
	@Test
	public void test003() {
		given()
		.queryParam("query","phone")
		.queryParam("apiKey",APIKEY)
		.queryParam("format","json")
		.when()
		.get("/search")
		.then()
		.body("items.name",hasItem("Verizon SAMSUNG Galaxy A01, 16GB - Prepaid Smartphone"));
	}

	// 4) Check Multiple Names in ArrayList
	@Test
	public void test004() {
		given()
		.queryParam("query","phone")
		.queryParam("apiKey",APIKEY)
		.queryParam("format","json")
		.when()
		.get("/search")
		.then()
		.body("items.name",hasItems("Verizon SAMSUNG Galaxy A01, 16GB - Prepaid Smartphone","Tracfone LG Rebel 4, 16GB Black - Prepaid Smartphone"));
	}
	
	// 5) Verify the attributes options for the first product (Checking Values inside Map using hasValue())
		@Test
		public void test005() {
			given()
			.queryParam("query","phone")
			.queryParam("apiKey",APIKEY)
			.queryParam("format","json")
			.when()
			.get("/search")
			.then()
			.body("items[0].attributes", hasKey("color"));
			}

	// 6) Check hashmap values inside a list
	@Test
	public void test006() {

				given()
				.queryParam("query","phone")
				.queryParam("apiKey",APIKEY)
				.queryParam("format","json")
				.when()
				.get("/search")
				.then()
				.body("items.findAll{it.name=='Verizon SAMSUNG Galaxy A01, 16GB - Prepaid Smartphone'}", hasItems(hasEntry("name", "Verizon SAMSUNG Galaxy A01, 16GB - Prepaid Smartphone")));
				
	}
	

	// 7) Checking multiple values in the same statement
	@Test
	public void test007() {
		given()
		.queryParam("query","phone")
		.queryParam("apiKey",APIKEY)
		.queryParam("format","json")
		.when()
		.get("/search")
		.then()
		.body("items[0].attributes", hasKey("color"))
		.body("items.findAll{it.name=='Verizon SAMSUNG Galaxy A01, 16GB - Prepaid Smartphone'}", hasItems(hasEntry("name", "Verizon SAMSUNG Galaxy A01, 16GB - Prepaid Smartphone")))
		.body("items.name",hasItem("Tracfone LG Rebel 4, 16GB Black - Prepaid Smartphone"))
		.statusCode(200);
	}

	// 8) Logical Assertions
	@Test
	public void test008() {
		given()
		.queryParam("query","phone")
		.queryParam("apiKey",APIKEY)
		.queryParam("format","json")
		.when()
		.get("/search")
		.then()
		.body("items.size()",equalTo(10))
		.body("items.size()",greaterThan(5))
		.body("items.size()",lessThan(11))
		.body("items.size()",greaterThanOrEqualTo(10))
		.body("items.size()",lessThanOrEqualTo(10));
			
	}

}
