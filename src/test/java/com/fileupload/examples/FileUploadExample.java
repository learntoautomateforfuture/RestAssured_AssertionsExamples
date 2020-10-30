package com.fileupload.examples;


//Static imports
import static io.restassured.RestAssured.given;

import java.io.File;

import org.junit.Test;

public class FileUploadExample {

	/**
	 * Upload a gif file to zamzar.com and convert it into a png file
	 * Generate a api Key from the site
	 */
	@Test
	public void uploadFileExample(){

		//PUT YOU API KEY HERE
		String apiKey="7873677866f8cc4238a9c7094d2b39e87bcf434b";
	
		File inputFile = new File(System.getProperty("user.dir")+File.separator+"dancing_banana.gif");
		System.out.println(inputFile.length());
		String endpoint = "https://sandbox.zamzar.com/v1/jobs";
		
		given()
		.auth()
		.basic(apiKey,"") // (username,password)
		.multiPart("source_file",inputFile)
		.multiPart("target_format","png")
		.when()
		.post(endpoint)
		.then()
		.log()
		.all();
	}
}
