import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class _02_ApiTestSpec {
    RequestSpecification requestSpec;
    ResponseSpecification responsespec;

    @BeforeClass
    public void setUp() {
        baseURI = "https://gorest.co.in/public/v1";

        requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .log(LogDetail.URI)
                .build();

        responsespec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .log(LogDetail.BODY)
                .build();
    }

    @Test
    public void Test1() {

        given()
                .spec(requestSpec)

                .when()
                .get("/users")//baseUri ekleniyor

                .then()
                .spec(responsespec)
        ;
    }

    @Test
    public void Test2() {

        given()
                .spec(requestSpec)

                .when()
                .get("/users") //baseUri ekleniyor

                .then()
                .spec(responsespec)
        ;
    }
}
