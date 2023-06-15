package get;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Get {

    @Test(priority = 1)
    public void getAllUsers(){
        System.out.println("Starting 'getAllUsers' test...");

        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .log().body();

        System.out.println("Finishing 'getAllUsers' test...");
    }

    @Test(priority = 2)
    public void getSingleUser(){
        System.out.println("Starting 'getSingleUser' test...");

        given().when()
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .log().body();

        System.out.println("Finishing 'getSingleUser' test...");
    }
}
