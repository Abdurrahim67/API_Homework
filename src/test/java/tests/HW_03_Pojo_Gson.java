package tests;

import base_url.RegresBaseUrl;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.RegresPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class HW_03_Pojo_Gson extends RegresBaseUrl {

    //3: with Pojo Class and  Gson

/*
        Given
            1) https://reqres.in/api/users/2
            2) {
                "name": "morpheus",
                "job": "zion president"
                }
        When
            I send Put Request to the Url
        Then
            Status code is 200
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "zion president",
                                                "updatedAt": "2022-10-02T11:35:05.693Z"
                                            }
*/

    @Test
    public void test01() {//---> Pojo ile
        //Set the URL
        spec.pathParams("first", "users", "second", 2);
        //Set the Expected Data
        RegresPojo expectedData = new RegresPojo("morpheus", "zion president");
        System.out.println("expectedData=" + expectedData);
        //Send the Request and Get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{first}/{second}");
        response.prettyPrint();
        //Do Assertion
        Gson gson = new Gson();
        RegresPojo actualData = gson.fromJson(response.asString(), RegresPojo.class);
        System.out.println("actualData=" + actualData);
        response.then().statusCode(200);
        assertEquals(expectedData.getName(), actualData.getName());
        assertEquals(expectedData.getJob(), actualData.getJob());


    }


}
