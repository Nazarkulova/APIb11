package GET;

import POJO.StarWarsCharactersPojo;
import POJO.StarWarsPojo;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class StarWars {
    /*
    GET https://swapi.dev/api/people
    Validate we have more than 0 female characyters in the movie
    on page 1
     */

    @Test
    public void StarWarGFemale(){
        Response response = RestAssured.given().header("Access", "application/Json")
                .when().get("https://swapi.dev/api/people")
                .then().statusCode(200).extract().response();
          Map<String,Object> parseRespond = response.as(new TypeRef<Map<String, Object>>() {
          });
        List<Map<String, Object>>listOfCharacters = (List<Map<String, Object>>) parseRespond.get("result");
        int count=0;
                  for (int i =0; i <listOfCharacters.size();i++){
                      Map<String,Object>charMap =listOfCharacters.get(i);
                      if (charMap.get("gender").equals("female")){
                          ++count;
                      }
                  }
        Assert.assertTrue(count>0);
    }
    @Test
    public void deserializeWithPojo(){

        Response response = RestAssured.given().header("Accept","application/json")
                .when().get("https://swapi.dev/api/people")
                .then().statusCode(200).extract().response();

      StarWarsPojo deserializedResp = response.as(StarWarsPojo.class);
      Assert.assertEquals(82,deserializedResp.getCount());

    /*List<Map<String,Object>> results = deserializedResp.getResults();
        for (Map<String,Object> map: results){
            System.out.println(map.get("name"));
        }


     */
      List<StarWarsCharactersPojo> results = deserializedResp.getResults();
        System.out.println(results.get(0).getName());
        for (StarWarsCharactersPojo character : results) {
            System.out.println(character.getName());
        }
    }
}
