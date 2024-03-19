package apiTesting;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
//1> using hashMap 2> using or.json that is jsonobject 3> POJO plain old java object 4> external file 
public class WaysToHandlePOSTMethod {

	// 1 using hashMAP
	@Test(priority = 1)
	void WaysPostMethod() {
		HashMap data = new HashMap();

		data.put("name", "SCOTT");
		data.put("location", "Farnce");
		data.put("phone", "987654556677");

		String courseArry[] = { "C", "C++" };
		data.put("Course", courseArry);

		given().contentType("application/json").body(data)

				.when().post("http://localhost:3000/students")

				.then().statusCode(201).body("name", equalTo("SCOTT")).body("location", equalTo("Farnce"))
				.body("Course[0]", equalTo("C")).body("Course[1]", equalTo("C++"))
				.header("Content-Type", "application/json; charset=utf-8").log().all();

	}

	// 2> Post request body using org.json library
	@Test(priority = 1)
	void WaysPostMethodJsonLib() {
		JSONObject data = new JSONObject();

		data.put("name", "SCOTT");
		data.put("location", "Farnce");
		data.put("phone", "987654556677");

		String courseArry[] = { "C", "C++" };
		data.put("Course", courseArry);

		given().contentType("application/json").body(data.toString())

				.when().post("http://localhost:3000/students")

				.then().statusCode(201).body("name", equalTo("SCOTT")).body("location", equalTo("Farnce"))
				.body("Course[0]", equalTo("C")).body("Course[1]", equalTo("C++"))
				.header("Content-Type", "application/json; charset=utf-8").log().all();

	}

	// 3 using POJO plain old java object

	void WaysPostMethodPOJO() {
		Pojo_PostRequest data = new Pojo_PostRequest();

		data.setName("Scott");
		data.setLocation("France");
		data.setPhone("123456");
		String Courses[] = { "C", "C++" };
		data.setCourses(Courses);

		given().contentType("application/json").body(data)

				.when().post("http://localhost:3000/students")

				.then().statusCode(201).body("name", equalTo("SCOTT")).body("location", equalTo("Farnce"))
				.body("Course[0]", equalTo("C")).body("Course[1]", equalTo("C++"))
				.header("Content-Type", "application/json; charset=utf-8").log().all();

	}

	// 4 using external Json external file
	@Test(priority = 1)
	void WaysPostMethodjsonFile() throws FileNotFoundException {

		File f = new File(".//body.json");

		FileReader fr = new FileReader(f);

		JSONTokener jt = new JSONTokener(fr);

		JSONObject data = new JSONObject(jt);

		given().contentType("application/json").body(data.toString())

				.when().post("http://localhost:3000/students")

				.then().statusCode(201).body("name", equalTo("SCOTT")).body("location", equalTo("Farnce"))
				.body("Course[0]", equalTo("C")).body("Course[1]", equalTo("C++"))
				.header("Content-Type", "application/json; charset=utf-8").log().all();

	}

	@Test(priority = 2)
	void deleteRecord() {

		given().when().delete("http://localhost:3000/students/4").then().statusCode(2000);
	}

}
