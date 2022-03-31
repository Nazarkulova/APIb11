package POST;

import POJO.PetPOJO;
import UTILS.PayloadUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class Pet {

    String petName="tommy";
    String petStatus="my pet";
    int id =2210901;
    @Test
    public void createPetTest(){

       Response response = RestAssured.given().header("Accept","application/json")
                .header("Content-Type","application/json")
                .body(PayloadUtil.getPetPayload(id,petName,petStatus))
                .when().post("https://petstore.swagger.io/v2/pet")
                .then().statusCode(200).extract().response();

               PetPOJO parsedResponse = response.as(PetPOJO.class);
        Assert.assertEquals(2210901,parsedResponse.getId());
        Assert.assertEquals("tommy",parsedResponse.getName());
        Assert.assertEquals("my pet",parsedResponse.getStatus());
        /*
        Add GET https:petstore.swagger.io/v2/pet/{yourPetId}
        Validate name, id, status are still same
         */
          RestAssured.given().header("Accept","application/json")
                .when().get("https://petstore.swagger.io/v2/pet/"+id)
                .then().statusCode(200)
                .and()
                .body("id", Matchers.is(id))
                .and()
                .body("name",Matchers.equalTo(petName))
                .body("status",Matchers.equalTo(petStatus))
                  .body("category.id",Matchers.is(22));

    }


}
