import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;


public class _03_ApiTestExtract {
    @Test
    public void extractingJsonPath() {
        String ulkeAdi =
                given()
                        .when()
                        .get("http://api.zippopotam.us/us/90210")

                        .then()
                        .extract().path("country")//PATH i country olan degeri extract yap
                ;
        System.out.println("ulkeAdi: " + ulkeAdi);
        Assert.assertEquals(ulkeAdi, "United States");

    }

    @Test
    public void extractingJsonPath2() {
        // Soru : "http://api.zippopotam.us/us/90210"  endpoint indne dönen
        // place dizisinin ilk elemanının state değerinin  "California"
        // olduğunu testNG Assertion ile doğrulayınız

        String state =
                given()
                        .when()
                        .get("http://api.zippopotam.us/us/90210")

                        .then()
                        .extract().path("places[0].state");
        System.out.println("state: " + state);
        Assert.assertEquals(state, "California");


    }

    @Test
    public void extractingJsonPath3() {
        // Soru : "https://gorest.co.in/public/v1/users"  endpoint in den dönen
        // limit bilgisinin 10 olduğunu testNG ile doğrulayınız.

        int limit =
                given()
                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        .extract().path("meta.pagination.limit");
        System.out.println("limit: " + limit);
        Assert.assertTrue(limit == 10);


    }

    @Test
    public void extractingJsonPath4() {
        // Soru : "https://gorest.co.in/public/v1/users"  endpoint in den dönen
        // datadaki tum id leri nasil aliriz

        ArrayList<Integer> idler =
                given()
                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        .extract().path("data.id");
        System.out.println("Idler= " + idler);

    }

    @Test
    public void extractingJsonPath5() {
        // Soru : "https://gorest.co.in/public/v1/users"  endpoint in den dönen
        // datadaki tum name leri nasil aliriz

        ArrayList<String> name =
                given()
                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        .extract().path("data.name");
        System.out.println("name= " + name);

    }

    @Test
    public void extractingJsonPath6() {
        // Soru : "https://gorest.co.in/public/v1/users"  endpoint in den dönen
        // datadaki tum e mail leri nasil aliriz

        ArrayList<String> email =
                given()
                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        .extract().path("data.email");
        System.out.println("email= " + email);

    }

    @Test
    public void extractingJsonPathResponseAll() {

        Response donenData =
                given()
                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        .extract().response();

        List<Integer> idler= donenData.path("data.id");
        List<String> name= donenData.path("data.name");
        int limit = donenData.path("meta.pagination.limit");

        System.out.println("idler= " + idler);
        System.out.println("name= " + name);
        System.out.println("limit= " + limit);

        Assert.assertTrue(idler.contains(7529386));
        Assert.assertTrue(name.contains("Chaitan Talwar"));
        Assert.assertTrue(limit == 10);
    }

    @Test
    public void extractingJsonPath7() {

        String name =
                given()
                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        .extract().path("data[1].name");
        System.out.println("name: " + name);
        Assert.assertEquals(name, "Chaitan Talwar");


    }
    @Test
    public void extractingJsonPath8() {

        int id =
                given()
                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        .extract().path("data[9].id");
        System.out.println("id: " + id);
        //Assert.assertEquals(id, 7529381);


    }
}
