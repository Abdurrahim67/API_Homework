package tests;

import base_url.RegresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.RegresNamePojo;
import pojos.RegresPojo;
import utils.ObjectMapperUtils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class HW_04_Pojo_ObjectMapper extends RegresBaseUrl {

    //4: with Pojo Class and Object Mapper
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
        RegresNamePojo expectedData = new RegresNamePojo("neo");
        System.out.println("expectedData=" + expectedData);
        //Send the Request and Get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().patch("/{first}/{second}");
        response.prettyPrint();
        //Do Assertion
        RegresNamePojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), RegresNamePojo.class);
        System.out.println("actualData=" + actualData);
        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.getName(), actualData.getName());

    }
}