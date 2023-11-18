package Day3;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeaderDemo {
	//@Test
	void testheader(){
             given()

                  .when()
                     .get("https://www.google.com/")
                    
                   .then()
                          .header("Content-Type", "text/html; charset=ISO-8859-1")
                          .header("Content-Encoding", "gzip")
                          .header("Server", "gws")
                          .log().all();
               }  
	
	// this one is not important because we already got all header from .log().all()
	    @Test
		void tesalltheader(){
	    Response res =given()

	                  .when()
	                     .get("https://www.google.com/");
	    String content_type= res.getHeader("Content-Type");
	    System.out.println(content_type);
	    
	    //get all headers
	    
	       Headers myheaders=  res.getHeaders();
	       for(Header hd: myheaders) {
	    	   System.out.println(hd.getName()+"===>" +hd.getValue());
	    	   
	    	   
	       }
	     
	             
	             

}
}

