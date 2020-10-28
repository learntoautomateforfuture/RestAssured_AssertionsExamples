package com.assertions.examples;

import org.junit.Test;

import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;

public class SoftHard_AssertionsExample {
	
	/* HARD ASSERTS:
	 *  - Assertions that do halt the test on a single failure
	 *  - It displays the result whenever there is a single failure and does not execute remaining lines of code
	 * */
	
	@Test
	public void hardAsserts(){
		RestAssured.given()
		.when()
		.get("http://localhost:8080/student/list")
		.then()
		.body("[0].firstName",equalTo("Vernon"))
		.body("[0].lastName",equalTo("Harper"))
		.body("[0].email",equalTo("egestas.rhoncus.Proin@massaQuisqueporttitor.org"))
		.body("[0].programme",equalTo("Financial Analysis"));
		
	}
	
	/* SOFT ASSERTS:
	 *  - Assertions that do not halt the test on a single failure
	 *  - All the assertions will be performed and displays result at the end of the test
	 * */
	
	
	@Test
	public void softAsserts(){
		RestAssured.given()
		.when()
		.get("http://localhost:8080/student/list")
		.then()
		.body("[0].firstName",equalTo("Vernonw"),
				"[0].lastName",equalTo("Harper"),
				"[0].email",equalTo("egestas.rhoncus.Proin@massaQuisqueporttitor.orge"),
				"[0].programme",equalTo("Financial Analysis"));
		
		
	}
}
