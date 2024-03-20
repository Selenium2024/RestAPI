package apiTesting;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.Map;
import java.util.Set;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class CookieInfo {

	@Test
	public void getCookie() {

		Response res = given()

				.when().get("https://www.google.com/");

		String Cookie = res.getCookie("AEC");

		System.out.println("Single Cookies information : " + Cookie);

		System.out.println("------------------------------------------------------------");
		Map<String, String> cokkies_value = res.getCookies();

		Set<String> keys = cokkies_value.keySet();

		System.out.println("Keys are : " + keys);

		for (String key : keys) {
			String value = res.getCookie(key);
			System.out.println(key + ":" + value);
		}

	}

}
