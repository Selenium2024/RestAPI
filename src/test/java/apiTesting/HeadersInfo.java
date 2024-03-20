package apiTesting;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class HeadersInfo {

	@Test

	public void getHeadersInfo() {

		System.out.println("####################### Headers Info ######################################");
		Response res = given().when().get("https://www.google.com");

		Headers headers = res.getHeaders();

		for (Header header : headers) {
			String name = header.getName();
			String value = header.getValue();

			System.out.println(header + ":" + value);
		}
		System.out.println("#############################################################");
	}

	@Test

	public void getHeadersInfobylog() {

		given().when().get("https://www.google.com").then().log().headers();

	}

}
