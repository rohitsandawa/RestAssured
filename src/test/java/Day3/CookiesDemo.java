package Day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.Map;
import org.testng.annotations.Test;
import io.restassured.response.Response;

public class CookiesDemo {
	//@Test(priority=1)
	void TestCookie() {
		 given()
		 
		 .when()
		       .get("https://www.google.com/")
		 
		 .then()
		       //AEC cookie value will always changing
		      .cookie("AEC", "Ackid1T5BVX56UuuzDtRev57TDqMZqmYJtpHP2_o697tvjQKEO_4COvQqYo")
		      .log().all();
		
	}
	@Test(priority=2)
	void TestAllCookies() {
		Response res= given()

		              .when()
		                     .get("https://www.google.com/");
		/*single cookie inform
		//String cookie_value= res.getCookie("AEC");
		System.out.println("Value of AEC Cookie is ====>"+cookie_value);
		*/
		
		// All cookies Values
		Map<String, String> all_cookies  =res.getCookies();
		//System.out.println(all_cookies.keySet());
		for(String k : all_cookies.keySet())
		{ 
			String cookie_value1= res.getCookie(k);
			System.out.println(k + "===>" +cookie_value1);
		}
	
	}

}
