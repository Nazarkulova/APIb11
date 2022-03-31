package PUT;

import POJO.PetPOJO;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class Pet {
    @Before
    public void setup(){
        RestAssured.baseURI="https://petstore.swagger.io";
        RestAssured.basePath="v2/pet";
    }
    @Test
    public void updatePetTest(){
        PetPOJO pet =new PetPOJO();
        pet.setName("pet from java house");
        pet.setId(233333);
        pet.setStatus("FUNNY");
       Response response = RestAssured.given()
                .accept("application/json")
                .contentType("application/json")
                .body(pet)
                .put().then().statusCode(200).extract().response();
      Map<String,Object> deserializeResp = response.as(new TypeRef<Map<String,Object>>() {
       });
       String name = String.valueOf(deserializeResp.get("name"));
        Assert.assertEquals("pet from java house",name);
        deserializeResp.get("id");
        int id = (int) deserializeResp.get("id");
        Assert.assertEquals(233333,id);
    }
}
