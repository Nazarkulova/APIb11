package GET;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class PokemonDeserialize {
    @Test
    public void pokemonTest(){
       Response response = RestAssured.given().header("Accept","application/json")
                .when().get("https://pokeapi.co/api/v2/pokemon")
                .then().statusCode(200).extract().response();
        Map<String, Object>pokemonResponse =response.as(new TypeRef<Map<String, Object>>() {
        });
        System.out.println(pokemonResponse);
        List<Map<String,Object>>results = (List<Map<String, Object>>) pokemonResponse.get("results");
        boolean isbulbasaur=false;
        for (Map<String,Object> info :results ){
           if (info.get("name").toString().equals("bulbasaur")){
               isbulbasaur=true;
               break;
           }
        }
        Assert.assertTrue("There is no buldasaur ", isbulbasaur);


    }
}
