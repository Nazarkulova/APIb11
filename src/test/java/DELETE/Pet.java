package DELETE;

import UTILS.PayloadUtil;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

public class Pet {
    int id =22223334;
    String name ="hatiko";
    String status ="waiting";
    @Test
    public void deletePublic(){
        RestAssured.given().accept("application/json")
                .when().delete(String.valueOf(id))
                .then().statusCode(200);
    }
    @Before
    public void setup(){
        RestAssured.baseURI="https://petstore.swagger.io";
        RestAssured.basePath="v2/pet";

        //delete: https://petstore.swagger.io/v2/pet/7777777777782;
        //post: https://petstore.swagger.io/v2/pet
        RestAssured.given()
                .contentType("application/json")
                .accept("application/json")
                .body(PayloadUtil.getPetPayload(id,name,status))
                .when().post().then().statusCode(200);

    }
}
