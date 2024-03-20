package apiTesting;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

//https://reqres.in/api/users?page=2&id=5

public class PathAndQueryParamaters {

	
	@Test
	public void parameters() {
		given()
		.pathParam("mypath","users")
		.queryParam("page", 2)
		.queryParam("id", 5)
		
		.when()
		.get("https://reqres.in/api/{mypath}")
		
		.then().statusCode(200).log().all();
	}
}
