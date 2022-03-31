package GetHome;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class hw1cat {
    /*
    GET https://cat-fact.herokuapp.com/facts
validate the verified facts count is more than 4

     */
    @Test
    public void validateCat(){
        Response response = RestAssured.given().header("Accept","application/json")
                .when().get("https://cat-fact.herokuapp.com/facts")
                .then().statusCode(200).extract().response();
        List<Map<String, Object>>factsCount = response.as(new TypeRef<List<Map<String, Object>>>() {
        });
        int countcat =0;
    for (int i =0; i < factsCount.size(); i++){
        Map<String, Object> catcount = factsCount.get(i);

        if (catcount.get(i).equals("count")){
            ++countcat;
        }

    }
        Assert.assertTrue(countcat>4);


    }
}
