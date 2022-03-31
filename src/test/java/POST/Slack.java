package POST;

import POJO.SlackMessagePojo;
import UTILS.PayloadUtil;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Slack {
    public static final String APPLICATION_JSON ="application/json";
    public static final String TOKEN="UPDATE_TOKEN";

    @Before // hook
    public void setup(){
        RestAssured.baseURI="https://slack.com";
        RestAssured.basePath="api/chat.postMessage";
    }

    @Test
    public void slackMessageTest(){

        RestAssured.given()
                .accept("application/json")   // instead of header("accept" application/json
                .contentType("application/json") // alternative for header("accept" application/
                .header("Authorization","Bearer xoxb-2694972852931-3301004561938-5HbvEoX49duFra8t1Gmd8iyj")
                .body(PayloadUtil.getSlackMessagePayload("Hello channel from Java practice"))
                .when().post()
                .then().statusCode(200).body("ok", Matchers.is(true));
    }
    @Test
    public void sendMessageTest(){
        Map<String,String> slackmessageMap=new HashMap<>();
        slackmessageMap.put("channel","C0397J4JY3T");
        slackmessageMap.put("text","Atyra: Hello channel from  Java serialization practice");

        RestAssured.given()
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .auth().oauth2("xoxb-2694972852931-3301004561938-5HbvEoX49duFra8t1Gmd8iyj")
                .body(slackmessageMap)
                .when().post()
                .then().statusCode(200)
                .and()
                .body("ok",Matchers.equalTo(true));
    }
    @Test
    public void sendMessage(){
        File slackFile = new File("src/test/resources/SlackMessage.json");

        RestAssured.given()
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .auth().oauth2("xoxb-2694972852931-3301004561938-5HbvEoX49duFra8t1Gmd8iyj")
                .body(slackFile)
                .when().post()
                .then().statusCode(200)
                .and().body("ok",Matchers.is(true));
        //xoxb-2694972852931-3301004561938-5HbvEoX49duFra8t1Gmd8iyj
    }
    @Test
    public void sendMessageWithPojoTest(){
        SlackMessagePojo slackMessage =new SlackMessagePojo();
        slackMessage.setChannel("C0397J4JY3T");
        slackMessage.setText("Atyra: Hello from Java POJO");
        RestAssured.given()
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .auth().oauth2(TOKEN)
                .body(slackMessage)
                .when().post()
                .then().statusCode(200)
                .body("ok",Matchers.equalTo(true));
        //"xoxb-2694972852931-3301004561938-aacdTvY8jFOXXe6YvdGZ7efn"
    }
}
