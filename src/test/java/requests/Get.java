package requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class Get {

    @Test(priority = 1, description = "Validate generic API Single User response")
    public void getSingleUser(){
        System.out.println("Starting 'getSingleUser' test...");

        given().when()
                    .get("https://reqres.in/api/users/2")
                .then()
                    .statusCode(200)
                    .log().body();

        System.out.println("Finishing 'getSingleUser' test...");
    }


    @Test(priority = 2, description = "Validate generic API All User response")
    public void getAllUsers(){
        System.out.println("Starting 'getAllUsers' test...");

        given()
                .when().get("https://reqres.in/api/users?page=2")
                        .then()
                                .statusCode(200)
                                .log().body();


        System.out.println("Finishing 'getAllUsers' test...");
    }


    @Test(priority = 3, description = "Validate API response using Response class")
    public void testResponse(){
        Response res = given().contentType(ContentType.JSON)
                .when().get("https://reqres.in/api/users?page=2");

        Assert.assertEquals(res.statusCode(), 200);
        Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");

        String email = res.jsonPath().get("data[1].email").toString();

        Assert.assertEquals(email, "lindsay.ferguson@reqres.in");

    }

    @Test(priority = 4, description = "Validate dynamic response")
    public void testDynamicResponse(){
        Response res = given().contentType(ContentType.JSON)
                .when().get("https://reqres.in/api/users?page=2");

        JSONObject jo = new JSONObject(res.asString());

        int totalUser = 0;

        for (int i=0; i<jo.getJSONArray("data").length(); i++){
            String email = jo.getJSONArray("data").getJSONObject(i).get("email").toString();

            System.out.println("User " + (i+1) + " Email: " + email);

            totalUser = totalUser + 1;
        }

        System.out.println("Total Number of Users: " + totalUser);
    }
}
