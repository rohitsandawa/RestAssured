package Day3;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;


public class LoggingDemo {
	   @Test
		void testlogging(){
	             given()

	                  .when()
	                     .get("https://www.google.com/")
	                    
	                   .then()
	                      // .log().all()
	                      //.log().body()
	                     //  .log().headers()
	                        .log().cookies();

}
}