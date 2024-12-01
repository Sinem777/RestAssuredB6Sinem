import Model.Location;
import Model.Place;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class _06_PathAndJsonPath {

    @Test
    public void extractingPath() {
        //gelen body de bilgiyi disari almanin iki yontemini gorduk
        //.extract.path("") ve as(ToDo.class)

        String postCode =
                given()
                        .when()
                        .get("http://api.zippopotam.us/us/90210")

                        .then()
                        .log().body()
                        .extract().path("postCode");
        System.out.println("Post Code: " + postCode);
        int postCodeInt = Integer.parseInt(postCode);
        System.out.println("Post Code: " + postCodeInt);

    }

    @Test
    public void extractingJsonPath() {
        //gelen body de bilgiyi disari almanin iki yontemini gorduk
        //.extract.path("") ve as(ToDo.class)

        int postCode =
                given()
                        .when()
                        .get("http://api.zippopotam.us/us/90210")

                        .then()
                        .log().body()
                        .extract().jsonPath().getInt("postCode")
                //1. avantaj: tip donusumu otomatik, uygun tip verilmeli
                ;
        System.out.println("Post Code: " + postCode);


    }

    @Test
    public void extractingJsonPathIcNesne() {
        Response donenData=
        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .extract().response();

        Location locationAsPathNesnesi=donenData.as(Location.class);//Butun class yapisini yazmak zorundayim
        //System.out.println("locationAsPathNesnesi: " + locationAsPathNesnesi);
        //bana sadece place dizxisi lazim olsa bile, butun diger claslari yazmak zorundayim

        //eger icerideki bir nesne tipli parcayi (buraca Places) almak isteseydim
        List<Place> places=donenData.jsonPath().getList("places", Place.class);
        System.out.println("places: " + places);

        //Sadece place dizisi lazim ise digerlerini yazmak zorunda degilsin
        //Daha onceki porneklerde (as) Class donusleri icin tum yapioyta karsil8ik gelen
        //gereken tum classlari  yazarak donusturup istedigimiz lemenlara ulasiyorduk

        //burada isr (jsonPath) aradaki bior veriyi classa donusturerek bir li8st olarak almamiza
        //imkan veren jsonPath i kullandik.boylece tek class ile diger classlara gerek kalmadan veri almis olduk

    }
    @Test
    public void getUsersV1(){
        // https://gorest.co.in/public/v1/users  endpointte dönen Sadece Data Kısmını POJO
        // dönüşümü ile alarak yazdırınız.




    }



}