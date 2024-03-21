package apiTesting;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ParseJson {

	@Test(priority = 1)
	public void testJsonResponse() {

		// Approach 1
//		given().contentType("ContentType.json").when().get("https://reqres.in/api/users?page=2").then().statusCode(200)
//				.header("Via", "1.1 vegur").body("data[1].first_name", equalTo("Lindsay")).log().all();

		// Approach 2

		Response res = given().contentType(ContentType.JSON).when().get("https://reqres.in/api/users?page=2");

		Assert.assertEquals(res.statusCode(), 200);

		String name = res.jsonPath().get("data[1].first_name").toString();

		Assert.assertEquals(name, "Lindsay");

	}

	@Test(priority = 2)
	public void testJsonResponsevalidate() {

		Response res = given().contentType(ContentType.JSON).when().get("https://reqres.in/api/users?page=2");

		JSONObject jo = new JSONObject(res.asString());

		boolean status=false;
		for (int i = 0; i < jo.getJSONArray("data").length(); i++) {

			String email = jo.getJSONArray("data").getJSONObject(i).get("email").toString();
			System.out.println(email);

			if (email.equals("lindsay.ferguson@reqres.in")) {
				status=true;
				break;
			}
		}
		
		Assert.assertEquals(status, true);
	}

}
