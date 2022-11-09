package tests;

import base_url.RegresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.RegresTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class HW_02_Map extends RegresBaseUrl {

    //2:  with Map
/*
        Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "496",
                                                "createdAt": "2022-10-04T15:18:56.372Z"
                                              }
*/

    @Test
    public void test01() {
        //Set the URL
        spec.pathParam("first", "users");
        //Set the Expected Data(put,post,patch)
        RegresTestData obj = new RegresTestData();
        Map<String, String> expectedData = obj.regresDataMethod("morpheus", "leader");
        //Send the Request and Get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();
        //Do Assertion
        response.then().statusCode(201);
        Map<String, Object> actualData = response.as(HashMap.class);
        assertEquals(expectedData.get("name"),actualData.get("name"));
        assertEquals(expectedData.get("job"),actualData.get("job"));



    }



}
