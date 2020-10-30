package com.proxyserver.example;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.specification.ProxySpecification;

//Send Request via ProxySpecification
public class ProxySpecificationObject {
	
	@BeforeClass
	public static void Init() {
		RestAssured.baseURI ="http://localhost:8080/student";
	}
	
	@Test
	public void RequestAsProxySpecification() {
		ProxySpecification ps = new ProxySpecification("localhost",5555,"http");
		
		RestAssured.given()
		.proxy(ps)
		.when()
		.get("/list")
		.then()
		.log()
		.body();
	}

}
