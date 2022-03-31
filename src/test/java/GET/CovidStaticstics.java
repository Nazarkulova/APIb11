package GET;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class CovidStaticstics {
    //Get"https://corona.lmao.ninja/v2/all"
    // Validate status code = 200
    //deserialize thew respond
    //validate affectedCountries=227
    @Test
    public void covidTest(){
        Response response = RestAssured.given()
                .header("Accept","application/json")
                .when()
                .get("https://corona.lmao.ninja/v2/all")
                .then().statusCode(200).extract().response();
        Map<String,Object> corona =response.as(new TypeRef<Map<String, Object>>() {
        });
     int affectedCountries =(int)corona.get("affectedCountries");
        Assert.assertEquals(227,affectedCountries);

    }

}
