package com.proxyserver.example;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;

// Send Request via RestAssured
public class StaticProxyConfiguration_Example1 {

	@BeforeClass
	public static void Init() {
		RestAssured.baseURI ="http://localhost:8080/student";
		RestAssured.proxy(5555);
		//		RestAssured.proxy("localhost",5555);

	}

	// No Proxy in the request method

	@Test
	public void RequestAsProxySpecification() {

		RestAssured.given()
		.when()
		.get("/list")
		.then()
		.log()
		.body();
	}


}
