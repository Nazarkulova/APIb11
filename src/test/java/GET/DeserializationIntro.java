package GET;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class DeserializationIntro {

    @Test
    public void TestPet(){
      Response response = RestAssured.given().header("Accept","application/json")
                .when()
                .get("https://petstore.swagger.io/v2/pet/775")
                .then().statusCode(200).extract().response();

        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });
        System.out.println(deserializedResponse);
        Assert.assertEquals(775,deserializedResponse.get("id"));

    }

















}
