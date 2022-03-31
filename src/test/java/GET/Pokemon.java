package GET;

import POJO.PokemonNamePojo;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.LineInputStream;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class Pokemon {
    @Before
    public void setup(){
        RestAssured.baseURI="https://pokeapi.co";
        RestAssured.basePath="api/v2/pokemon";
    }
    @Test
    public void pokemonTest1(){

        Response response = RestAssured.given().header("Accept","application/json")
                .when().get("https://pokeapi.co/api/v2/pokemon")
                .then().statusCode(200).extract().response();
        PokemonNamePojo pokemonNamePojo=response.as(PokemonNamePojo.class);
        Assert.assertEquals(1126,pokemonNamePojo.getCount());

    }
    @Test
    public void pokemonTest2(){

        Response response = RestAssured.given().header("Accept","application/json")
                .when().get("https://pokeapi.co/api/v2/pokemon")
                .then().statusCode(200).extract().response();
       JsonPath jsonPath = response.jsonPath();
       String nextURL = jsonPath.getString("next");
        System.out.println(nextURL);
       String pokemon1Name= jsonPath.getString("results[0].name");
        System.out.println(pokemon1Name);

        List<Map<String,String>> resultList = jsonPath.getList("results");
        for (Map<String,String> pokemon:resultList){
            System.out.println(pokemon.get("name"));
        }

        }
    @Test
    public void pokemonTest3(){
       Response respond = RestAssured.given().header("Accept","application/json")
                .when().get()
                .then().statusCode(200)
                .body("count", Matchers.equalTo(1126))
                .and()
                .body("next",Matchers.is("https://pokeapi.co/api/v2/pokemon?offset=20&limit=20"))
                .log().all().extract().response();



    }
}
