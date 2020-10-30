package com.proxyserver.example;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.specification.ProxySpecification;

//Send Request via RestAssured
public class StaticProxyConfiguration_Example2 {
	
	@BeforeClass
	public static void Init() {
		ProxySpecification ps = new ProxySpecification("localhost",5555,"http");
		
		RestAssured.baseURI ="http://localhost:8080/student";
		RestAssured.proxy(ps);
		
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
