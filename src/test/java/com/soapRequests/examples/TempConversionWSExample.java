package com.soapRequests.examples;

import org.junit.Test;

import io.restassured.RestAssured;

public class TempConversionWSExample {
	
	@Test
	public void FahrenheitToCelsius() {
		
		String wsdlURl = "https://www.w3schools.com/xml/tempconvert.asmx";
		String payload = "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" + 
				"\n" + 
				"  <soap12:Body>\n" + 
				"\n" + 
				"    <FahrenheitToCelsius xmlns=\"https://www.w3schools.com/xml/\">\n" + 
				"\n" + 
				"      <Fahrenheit>75</Fahrenheit>\n" + 
				"\n" + 
				"    </FahrenheitToCelsius>\n" + 
				"\n" + 
				"  </soap12:Body>\n" + 
				"\n" + 
				"</soap12:Envelope>";
		
		RestAssured.given()
		.contentType("text/xml")
		.body(payload)
		.post(wsdlURl)
		.then()
		.log()
		.all();
	}

}
