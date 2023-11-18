package Day5;

import java.io.File;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

public class FileUpload_and_FileDownload {
	@Test
	
	void singlefileupload(){
		
		//Task--> upload a file 
		File myfile = new File("c:\\Notesfromclass\\Test1.txt");
		
		given()      
		                 //Key   Value (similar to postman)
		      .multiPart("file", myfile)            // this represent the form data for single file upload
		      .contentType("multipart/form-data")   //important
		      
		.when()
		       .post("requestlink")
		
		.then()
		       .statusCode(200)
		       .body("filename",equalTo("Test1.txt"))
		       .log().all();
		       
		
		
	}
@Test
	
	void multiplefileupload(){
	 
		
		File myfile1 = new File("c:\\Notesfromclass\\Test1.txt");
		File myfile2 = new File("c:\\Notesfromclass\\Test2.txt");
		
		// mainly we store multiple file in an array.
	   //  File filearr[] = {myfile1, myfile2};
		
		given()      
		      // .multiPart("files",filearr)          
		      .multiPart("files", myfile1)  
		      .multiPart("files", myfile2)
		      .contentType("multipart/form-data")   //important
		      
		.when()
		       .post("requestlink")
		
		.then()
		       .statusCode(200)
		       .body("[0]filename",equalTo("Test1.txt"))
		       .body("[1]filename",equalTo("Test2.txt"))
		       .log().all();		
	}
     @Test
      void FileDownload() {
    	  
    	 given()
    	 
    	 .when()
    	      .get("downloadURL from above file body")
    	 
    	 .then() 
    	     .statusCode(200);
    	  
    	  
      }

}
