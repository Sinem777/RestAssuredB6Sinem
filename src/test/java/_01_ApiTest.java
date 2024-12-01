
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class _01_ApiTest {

    @Test
    public void Test1() {
        //1-URL yi cagirmadan once hazirliklarin yapildigi bolum:Request;gidecek body,token
        //2-Endpoint in cagirildigi bolum: Endpointin cagirilmasi(METOD: GET,POST,...)
        //3-Endpoint cagirildiktan sonraki bolum:  Response;Test(Assertion),Data(donen bilgi)

        given().
                //1. bolumle ilgili isler: giden body, token
                        when().
                //2. bolunle ilgili isler: metod, endpoint

                        then();
        //3. bolumle ilgili isler:gelen data,assert, test


    }

    @Test
    public void statusCodeTest() {
        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()  //donen data kismi
                //.log().all() //donen butun bilgiler
                .statusCode(200)//donen deger 200 e esitmi , assert
        ;

    }

    @Test
    public void contentTypeTest() {
        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()  //donen data kismi
                .statusCode(200)//donen deger 200 e esitmi , assert
                .contentType(ContentType.JSON)//donen datanin tipi JSON mi
        ;

    }

    @Test
    public void checkCountryInResponseBody() {
        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()  //donen data kismi
                .statusCode(200)//donen deger 200 e esitmi , assert
                .contentType(ContentType.JSON)//donen datanin tipi JSON mi
                .body("country", equalTo("United States"))//country i disari almadan
        //bulundugu yeri (path) vererek iceride assertionyapiyor.Bunu hamcrest kutuphanesi yapiyor
        //pm.test("Ulke Bulunamadı", function () {
        //    pm.expect(pm.response.json().message).to.eql("Country not found");
        //buna karsilik gelmis oldu

        ;
    }

    @Test
    public void checkCountryInResponseBody2()
// Soru : "http://api.zippopotam.us/us/90210"  endpoint indne dönen
// place dizisinin ilk elemanının state değerinin  "California"
// olduğunu doğrulayınız
    {
        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("places[0].state", equalTo("California"))
        ;
    }

    @Test
    public void checkHasItem() {
        // Soru : "http://api.zippopotam.us/tr/01000"  endpoint in dönen
        // place dizisinin herhangi bir elemanında  "Dörtağaç Köyü" değerinin
        // olduğunu doğrulayınız
        given()

                .when()
                .get("http://api.zippopotam.us/tr/01000")

                .then()
                .body("places.'place name'", hasItem("Dörtağaç Köyü"))//places içindeki bütün place name ler in
        // içinde Dörtağaç Köyü var mı
        ;
    }

    @Test
    public void bodyArrayHasSizeTest() {
        // Soru : "http://api.zippopotam.us/us/90210"  endpoint in dönen
        // place dizisinin dizi uzunluğunun 1 olduğunu doğrulayınız.

        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .body("places", hasSize(1)) // places in eleman uzunluğuı 1 mi
        ;
    }

    @Test
    public void combiningTest() {

        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .body("places", hasSize(1))
                .body("places[0].state", equalTo("California"))
                .body("places.'place name'", hasItem("Beverly Hills"))
        //Yukarida oldugu gibi istenilen kadar test eklenebilir.

        ;
    }

    @Test
    public void pathParamTest() {

        given()
                .pathParam("ulke", "us")
                .pathParam("postaKodu", 90210)
                .log().uri()//olusacak endpoint i yazdiralim

                .when()
                .get("http://api.zippopotam.us/{ulke}/{postaKodu}")//Path parameter

                .then()
                .log().body()
        ;
    }

    @Test
    public void queryParamTest() {
        //https://gorest.co.in/public/v1/users?page=3
        //queryParam soru isaretleri icin kullaniliyor

        given()
                .param("page", 3)

                .when()
                .get("https://gorest.co.in/public/v1/users")//URL kismi buraya yazildi

                .then()
                .log().body()
        ;
    }

    @Test
    public void queryParamTest2() {
        //https://gorest.co.in/public/v1/users?page=3
        // bu linkteki 1 den 10 kadar sayfaları çağırdığınızda response daki donen page degerlerinin
        // çağrılan page nosu ile aynı olup olmadığını kontrol ediniz.

        for (int i = 1; i <= 10; i++) {

            given()
                    .param("page", i)

                    .when()
                    .get("https://gorest.co.in/public/v1/users")

                    .then()
                    .log().body()
                    .body("meta.pagination.page", equalTo(i))
            ;

        }

    }
}