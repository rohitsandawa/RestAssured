package Day4;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Parsing_JSON_Response_Data {
	// Approach 1 
	@Test(priority=1)
	void testJSONResponse() {
		
		/*given()
		      .contentType(ContentType.JSON)
		
		.when()
		      .get("http://localhost:3000/book")
		
		.then()
		      .statusCode(200)
		      .header("Content-Type", "application/json; charset=utf-8")
		      .body("[3].title", equalTo("Grand Theft Auto V"));
		
	}*/
				
	// Approach 2
	/*Response res =given()
		              .contentType(ContentType.JSON)
		
		         .when()
		              .get("http://localhost:3000/book");
	
	       Assert.assertEquals(res.getStatusCode(),200);
           Assert.assertEquals(res.header("Content-Type"),"application/json; charset=utf-8");
           String bookname = res.jsonPath().get("[3].title").toString();
	       Assert.assertEquals(bookname, "Grand Theft Auto V");
}	*/
		
	//approach 3
	
	Response res =given()
            .contentType(ContentType.JSON)

       .when()
            .get("http://localhost:3000/book");
	  
	// converting response to Json object type
	
	JSONObject jo = new JSONObject(res.toString());
	
	//Task is to print all the titles of books present in json object.
	
	for(int i=0; i<jo.getJSONArray("book").length();i++) {
		  String booktitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
		  System.out.println(booktitle);
	}
	
	
	//task is to check the particular title is there or not
	boolean flag = false;
	for(int i=0; i<jo.getJSONArray("[]").length();i++) {
		  String booktitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
		  if(booktitle.equals("The Shawshank Redemption")) {
			  System.out.print("Title is found");
			  flag = true;
			  break;
		  }	  
	}
	   
	Assert.assertEquals(flag,true);
	
	// Task is to validate the total price of books.
	double totalprice=0;
	for(int i=0; i<jo.getJSONArray("book").length();i++) {
		 String price = jo.getJSONArray("book").getJSONObject(i).get("price").toString();
	     totalprice = totalprice +Double.parseDouble(price);
}
}
}