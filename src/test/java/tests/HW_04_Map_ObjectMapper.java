package tests;

import base_url.RegresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import test_data.RegresTestData;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class HW_04_Map_ObjectMapper extends RegresBaseUrl {

    //4: with Map and Object Mapper
  /*
       Given
           1) https://reqres.in/api/users/2
           2) {
                "name": "neo"
               }
       When
            I send PATCH Request to the Url
       Then
             Status code is 200
             And response body is like   {
                                                "name": "neo",
                                                "updatedAt": "2022-10-02T12:53:21.675Z"
                                         }
    */


    @Test
    public void test01() throws IOException {
        //Set the Url
        spec.pathParams("first", "users", "second", 2);
        //Set the Expected Data
        RegresTestData obj = new RegresTestData();
        String jsonString = obj.expectedDataInString("neo");
        System.out.println(jsonString);
        Map<String, Object> expectedData = new ObjectMapper().readValue(jsonString, HashMap.class);
        System.out.println("expectedData: " + expectedData);
        //Send the Request and Get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().patch("/{first}/{second}");
        response.prettyPrint();
        //Assertion
        HashMap actualData = new ObjectMapper().readValue(response.asString(), HashMap.class);
        System.out.println("actualData="+actualData);
        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.get("name"),actualData.get("name"));


    }
}
