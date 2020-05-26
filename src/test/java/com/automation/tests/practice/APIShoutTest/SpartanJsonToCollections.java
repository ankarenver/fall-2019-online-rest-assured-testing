package com.automation.tests.practice.APIShoutTest;

import static io.restassured.RestAssured.*;

import com.automation.pojos.Spartan;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Map;

public class SpartanJsonToCollections {


    @BeforeAll
    public static void setup(){
        baseURI = "http://54.152.21.73:8000";
    }

    /**
     * Given accept type is json
     * and path param spartan id is 657
     * when user sends a get request to /api/spartan/{id}
     * then status code is 200
     * and content type is Json
     * and
     *
     *      *           "id": 657,
     *      *           "name": "Shantel",
     *      *           "gender": "Male",
     *      *           "phone": 1074677053
     *
     */

    @Test
    public void test1(){
        Response response =  given().
                                    auth().basic("admin","admin").contentType(ContentType.JSON).
                            when().
                                    get("/api/spartans/{id}",657);

        //convert Json response to Java Collections(Map)
        Map<String,?> allData =response.as(Map.class);
        System.out.println(allData);

        System.out.println(allData.get("name"));

        Assertions.assertEquals(657.0,allData.get("id"));
        Assertions.assertEquals("Shantel",allData.get("name"));
        Assertions.assertEquals("Male",allData.get("gender"));
        Assertions.assertEquals(1.074677053E9,allData.get("phone"));

    }

    @Test
    public void test2(){
        File file = new File("spartan1.json");

//        Response response = given().auth().basic("admin","admin").accept(ContentType.JSON).when().get("/api/spartans").prettyPeek();


        Response response = given().
                                    auth().basic("admin","admin").
                                    contentType(ContentType.JSON).body(file).
                            when().
                                    post("/api/spartans").prettyPeek();

        System.out.println(response.jsonPath().getString("success"));
    }

    @Test
    public void addSpartan(){
        //create Spartan object and used as a body for post request
        Spartan spartan = new Spartan("osman","Male",12345678900L);

        Response response = given().
                                    auth().basic("admin","admin").
                                    contentType(ContentType.JSON).body(spartan).
                            when().
                                    post("/api/spartans").prettyPeek();

        Assertions.assertEquals("A Spartan is Born!",response.jsonPath().getString("success"));


        int userId = response.jsonPath().getInt("data.id");
        System.out.println(userId);
        Response response1 = given().
                                    auth().basic("admin","admin").
                                    contentType(ContentType.JSON).
                                and().
                                    accept(ContentType.JSON).
                                when().
                                    get("/api/spartans/{id}",userId).prettyPeek();

        Spartan newSpartan = response1.body().as(Spartan.class);
        System.out.println(newSpartan);


    }



}
