package tests;

import base_url.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.PetStorePojo;
import test_data.DeleteTestData;
import utils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HW_05_Post_Get_Delete extends PetStoreBaseUrl {
    //5:
    /*
    https://petstore.swagger.io/ documantation adresini kullanarak 'user' oluşturan ve oluşsan bu user'ı silen bir otomasyon kodu yazınız.
    */


    @Test
    public void test01() {

        //--->Post Request
        //Set the URL
        spec.pathParams("first", "v2", "second", "user");
        //Set the Expected Data(post)
        PetStorePojo expectedData = new PetStorePojo("alp497", "Alphonso", "Meier", "alphonso@gmail.com", "lkasj.433", "012354555");
        System.out.println("expectedData=" + expectedData);
        //Send the Request and Get the Response
        Response responsePost = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}/{second}");



        //---> Get Request
        //Set the Url
        spec.pathParams("first", "v2", "second", "user", "third", "alp497");
        //Send the Request and Get the Response(Get)
        Response responseGet = given().spec(spec).contentType(ContentType.JSON).when().get("/{first}/{second}/{third}");
        //Do Assertion
        PetStorePojo actualData = ObjectMapperUtils.convertJsonToJava(responseGet.asString(), PetStorePojo.class);
        System.out.println("actualData=" + actualData);

        assertEquals(200, responsePost.getStatusCode());
        assertEquals(expectedData.getUsername(), actualData.getUsername());
        assertEquals(expectedData.getFirstName(), actualData.getFirstName());
        assertEquals(expectedData.getLastName(), actualData.getLastName());
        assertEquals(expectedData.getEmail(), actualData.getEmail());
        assertEquals(expectedData.getPassword(), actualData.getPassword());
        assertEquals(expectedData.getPhone(), actualData.getPhone());

        //--->Delete
        //Set the Url
        spec.pathParams("first", "v2", "second", "user", "third", "alp497");
        //Set the Expected Data
        Map expectedDeleteData= new DeleteTestData().deleteTestMethod(200,"unknown","alp497");
        System.out.println("expectedDeleteData="+expectedDeleteData);
        //Send the Request and Get the Response
        Response responseDelete = given().spec(spec).contentType(ContentType.JSON).when().delete("/{first}/{second}/{third}");
        responseDelete.prettyPrint();
        //Do Assertion
        Map actualDeleteData = ObjectMapperUtils.convertJsonToJava(responseDelete.asString(), HashMap.class);
        System.out.println("actualDeleteData"+actualDeleteData);
        assertEquals(expectedDeleteData, expectedDeleteData);




    }

}
