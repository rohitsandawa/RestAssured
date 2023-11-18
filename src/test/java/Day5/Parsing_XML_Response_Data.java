package Day5;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

public class Parsing_XML_Response_Data {
	
	@Test(priority=1)
	void testXMLResponse() {
		//approach 1
		/*given()
		      
		
		.when()
		      .get("http://restapi.adequateshop.com/api/Traveler?page=1")
		
		.then()
		      .statusCode(200)
		      .header("Content-Type", "application/xml; charset=utf-8")
		      .body("TravelerinformationResponse .page",equalTo("1"))
		      .body("TravelerinformationResponse .travelers.Travelerinformation.name[0]", equalTo("Developer"));
		   */
		
		//approach 2
		Response res=
        given()

		.when()
		      .get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		Assert.assertEquals(res.contentType(), "application/xml; charset=utf-8");
		Assert.assertEquals(res.getStatusCode(),200 );
		
		String pagenum= res.xmlPath().get("TravelerinformationResponse .page").toString();
		Assert.assertEquals(pagenum,"1");
		
		String travelname= res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString();
		Assert.assertEquals(travelname,"Developer");
		
     }
	@Test(priority=2)
	void testXMLBody() {
		Response res=
		        given()

				.when()
				      .get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		
		XmlPath xmlobj = new XmlPath(res.asString());
		List<String>travel_names=xmlobj.getList("TravelerinformationResponse .travelers.Travelerinformation.name");
		
		for(int i=0;i<travel_names.size();i++) {
			System.out.println(travel_names.get(i));
			
		}
		
		//Verify that specific name present in the response body
		boolean flag = false;
		for(String travel_name: travel_names) {
			if(travel_name.equals("Developer123")){
				flag=true;
			    System.out.println("Name found");
				break;
				
			}
			
		}
		Assert.assertEquals(flag, true);
		
	}
				
	
}

