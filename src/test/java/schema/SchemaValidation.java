package schema;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class SchemaValidation {

    @Test(priority = 1, description = "Validate JSON Schema")
    public void jsonSchemaValidation(){
        given()
                .when().get("https://reqres.in/api/users?page=2")
                .then()
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json-schema.json"));
    }

    @Test(priority = 2, description = "Validate XML Schema")
    public void xmlSchemaValidation(){
        given()
                .when().get("https://api.bart.gov/api/stn.aspx?cmd=stns&key=MW9S-E7SL-26DU-VV8V")
                .then()
                .assertThat().body(matchesXsdInClasspath("xml-schema.xsd"));
    }
}
