package base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class PetStoreBaseUrl {
    protected  RequestSpecification spec;
    @Before
    public void SetUp(){

        spec=new RequestSpecBuilder().setBaseUri("https://petstore.swagger.io/").build();

    }



}
