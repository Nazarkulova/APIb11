package Stef_Def;

import POJO.PetPOJO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

import java.io.File;

public class PetStepDefs {
    Response response;
    @Given("I have valid url to create a pet")
    public void i_have_valid_url_to_create_a_pet() {

        RestAssured.baseURI="https://petstore.swagger.io";
        RestAssured.basePath="v2/pet";

    }
    @When("I send POST request to create a pet")
    public void i_send_post_request_to_create_a_pet() {
        PetPOJO pet =new PetPOJO();
        pet.setStatus("available");
        pet.setName("jimmy");
        pet.setId(1122444);
        response = RestAssured.given()
                .accept("application/json")
                .contentType("application/json")
                .body(pet).when().post();
    }
    @Then("status code should be {int}")
    public void status_code_should_be(int expectedStatus) {
        int actualStatus = response.statusCode();
        Assert.assertEquals(expectedStatus,actualStatus);

    }
    @Then("response should be in json format")
    public void response_should_be_in_json_format() {
       String actualContentType =response.getContentType();
       Assert.assertEquals("application/json",actualContentType);
    }

}
