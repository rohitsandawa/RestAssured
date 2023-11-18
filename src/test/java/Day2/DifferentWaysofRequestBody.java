package Day2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

/*1) Using HashMap
 *2)Using Org.json
 *3)Using POJO (Plain old java object)
 *4)Using external JSON File 
 */

public class DifferentWaysofRequestBody {
//*1) Using HashMap
	//@Test(priority=1)
	void Hashmap1() {
    HashMap data = new HashMap();
    data.put("name","ronaldo");
    data.put("location", "portugal");
    data.put("phone", "9203010410");
    
    String CourseArr[] = {"rubi","python"};
    
    data.put("courses", CourseArr);
    given().contentType("application/json").body(data)
    
    .when().post("http://localhost:3000/students")
    
    .then().statusCode(201)
           .body("name", equalTo("ronaldo"))
           .body("location", equalTo("portugal"))
           .body("phone", equalTo("9203010410"))
           .body("courses[0]",equalTo("rubi"))
           .body("courses[1]",equalTo("python"))
           .log().all();
    
	}
	

//-------------------------------------------------------------------------------------------------------------------	
//2)Using Org.json
	// @Test(priority=1)
	 void TestPostUsingJsonLibrary() {
	
		
		   JSONObject data = new JSONObject();
		   
		    data.put("name","jason");
		    data.put("location", "england");
		    data.put("phone", "9203010410");
		    String CourseArr[] = {"rubi","python"};
		    
		    data.put("courses", CourseArr);
		    
			given().contentType("application/json").body(data.toString())
		    
		    .when().post("http://localhost:3000/students")
		    
		    .then().statusCode(201)
		           .body("name", equalTo("jason"))
		           .body("location", equalTo("england"))
		           .body("phone", equalTo("9203010410"))
		           .body("courses[0]",equalTo("rubi"))
		           .body("courses[1]",equalTo("python"))
		           .log().all();
		    
			
	 }
	 

	//-----------------------------------------------------------------------------------------------------------------------
//3)Using POJO (Plain old java object)
	//@Test(priority=1)
	 void TestPostUsingPOJO() {
		   
		PojoClass PJ= new PojoClass();
		PJ.setName("Mahesh");
		PJ.setLocation("nayapura");
		PJ.setPhone("8384020421");
		String CourseArr[] = {"nodeJS",".NET"};
		PJ.setCourses(CourseArr);
		

			given().contentType("application/json").body(PJ)
		    
		    .when().post("http://localhost:3000/students")
		    
		    .then().statusCode(201)
		           .body("name", equalTo("Mahesh"))
		           .body("location", equalTo("nayapura"))
		           .body("phone", equalTo("8384020421"))
		           .body("courses[0]",equalTo("nodeJS"))
		           .body("courses[1]",equalTo(".NET"))
		           .log().all();
		    
			
	 }
	 

//-----------------------------------------------------------------------------------------------------------------------

// 4)Using external JSON File 
	@Test(priority=1)
	 void TestPostUsingexternalJSONFile() throws FileNotFoundException {
		   
		    File F = new File(".//JSONfile");
		    FileReader FR = new FileReader(F);
		    JSONTokener jt = new JSONTokener(FR);
		    JSONObject data = new JSONObject(jt);

			given().contentType("application/json").body(data.toString())
		    
		    .when().post("http://localhost:3000/students")
		    
		    .then().statusCode(201)
		           .body("name", equalTo("Alina"))
		           .body("location", equalTo("German"))
		           .body("phone", equalTo("9424020234"))
		           .body("courses[0]",equalTo("JS"))
		           .body("courses[1]",equalTo(".NET"))
		           .log().all();
		    
			
	 }
	 
     @Test(priority=2)
		void deletestudent() {
			given()
			.when().delete("http://localhost:3000/students/218")
			.then().statusCode(200);

			}
	
	
	
}


