package apiTesting;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

public class HttpRequests {

	int id;

	@Test(priority = 1)
	void getUsers() {
		given()

				.when().get("https://reqres.in/api/users?page=2")

				.then().statusCode(200).body("page", equalTo(2)).log().all();
	}

	@Test(priority = 2)
	void createUser() {

//		HashMap data = new HashMap();
//		data.put("name", "morpheus");
//		data.put("job", "leader");

		id = given().contentType("application/json")
				.body("{\r\n" + "    \"name\": \"morpheus\",\r\n" + "    \"job\": \"leader\"\r\n" + "}")

				.when().post("https://reqres.in/api/users").jsonPath().getInt("id");

//				.then().statusCode(201).log().all();

	}

	@Test(priority = 3, dependsOnMethods = { "createUser" })
	void updateUser() {
		given().contentType("application/json")
				.body("{\r\n" + "    \"name\": \"morpheus\",\r\n" + "    \"job\": \"zion resident\"\r\n" + "}")

				.when().put("https://reqres.in/api/users/" + id)

				.then().statusCode(200).log().all();
	}

	@Test(priority = 4)
	void deleteUser() {
		given().when().delete("https://reqres.in/api/users/" + id).then().statusCode(204).log().all();
	}

}
