package tests;

import base_url.RegresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.RegresPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class HW_02_Pojo extends RegresBaseUrl {


    //2: with Pojo Class
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
        //Set the Expected Data
        RegresPojo expectedData = new RegresPojo("morpheus", "leader");
         System.out.println("expectedData="+expectedData);
        //Send the Request and Get the Reponse
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();
        //Do Assertion
        RegresPojo actualData = response.as(RegresPojo.class);
        System.out.println("actualData="+actualData);

        assertEquals(201,response.getStatusCode());
        assertEquals(expectedData.getName(),actualData.getName());
        assertEquals(expectedData.getJob(),actualData.getJob());


    }
}
