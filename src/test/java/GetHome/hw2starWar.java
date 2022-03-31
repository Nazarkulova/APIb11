package GetHome;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class hw2starWar {
    /*
    GET https://swapi.dev/api/people
validate the R2-D2 height is 96
     */
    @Test
    public void StarWarR2(){
        Response response = RestAssured.given().header("Accept","application/json")
                .when().get("https://swapi.dev/api/people")
                .then().statusCode(200).extract().response();
        Map<String,Object> starrespond=response.as(new TypeRef<Map<String, Object>>() {
        });
        List<Map<String,Object>>namerespond=(List<Map<String, Object>>) starrespond.get("results");
        for (int i =0; i< namerespond.size();i++){
            Map<String,Object>name= namerespond.get(i);

            if (name.get("name").equals("R2-D2")){
                System.out.println(name.get("name"));
            }
            if (name.get("height").equals("96")){
                System.out.println(name.get("height"));
            }
        }
    }
}
