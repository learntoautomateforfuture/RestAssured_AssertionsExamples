package com.proxyserver.example;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.ProxySpecification;
import io.restassured.specification.RequestSpecification;

//Send Request via ProxySpecification & RequestSpecification
public class ProxyInRequestSpecification {
	
	public static RequestSpecBuilder rspec;
	public static RequestSpecification rp;
	
	@BeforeClass
	public static void Init() {
		ProxySpecification ps = new ProxySpecification("localhost",5555,"http");
		
		RestAssured.baseURI ="http://localhost:8080/student";
		
		rspec = new RequestSpecBuilder();
		rspec.setProxy(ps);
		rp = rspec.build();
		
	}

	
	@Test
	public void RequestAsProxySpecification() {
			
		RestAssured.given()
		.spec(rp)
		.when()
		.get("/list")
		.then()
		.log()
		.body();
	}

}
