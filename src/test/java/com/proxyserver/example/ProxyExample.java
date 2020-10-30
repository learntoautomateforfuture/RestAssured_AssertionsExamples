package com.proxyserver.example;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;

// Send request via Proxy Method
public class ProxyExample {
	
	@BeforeClass
	public static void Init() {
		RestAssured.baseURI ="http://localhost:8080/student";
	}

	@Test
	public void Request_ProxyPortNum() {
		RestAssured.given()
		.proxy(5555)
		.when()
		.get("/list")
		.then()
		.log()
		.body();
	}
	
	@Test
	public void Request_ProxyPortNumb_ServerAdd() {
		RestAssured.given()
		.proxy("localhost",5555)
		.when()
		.get("/list")
		.then()
		.log()
		.body();
	}
}
