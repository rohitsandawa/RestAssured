package Day3;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class PathAndQueryParameter {
      @Test
	  void testPathAndQueryParameter() {
    	  //https://reqres.in/api/users?page=2&id=5
    	  given()
    	        .pathParam("mypath","users" )  // path param
    	        .queryParam( "page",2)         // query param
    	        .queryParam("id",5)            // query param
    	  
    	  .when()
    	         .get("https://reqres.in/api/{mypath}")
    	  .then()
    	         .statusCode(200)
    	         .log().all();

    	  
      }
	
}
