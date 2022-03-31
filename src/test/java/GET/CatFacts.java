package GET;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;
import java.util.Map;

public class CatFacts {
    @Test
    public void catFacts(){
       Response response = RestAssured.given().header("Accept","application/json")
                .when()
                .get("https://cat-fact.herokuapp.com/facts")
                .then().statusCode(200).extract().response();

        List<Map<String, Object>> catFactsList =response.as(new TypeRef<List<Map<String, Object>>>() {
        });

       for (int i =0; i<catFactsList.size();i++){
           Map<String, Object> catMap= catFactsList.get(i);
           System.out.println(catMap.get("text"));

       }

    }
    @Test
    public void lastFactTest(){
        Response response = RestAssured.given().header("Accept","application/json")
                .when()
                .get("https://cat-fact.herokuapp.com/facts")
                .then().statusCode(200).extract().response();

        List<Map<String, Object>> parsedResound =response.as(new TypeRef<List<Map<String, Object>>>() {
        });
          String expectedLastFact="Cats are the most popular pet in the United States: There are 88 million pet cats and 74 million dogs.";

            Map<String, Object> lastMap= parsedResound.get(parsedResound.size()-1);
         String actualLastFact= lastMap.get("text").toString();

         Assert.assertEquals(expectedLastFact,actualLastFact);

    }
    }

