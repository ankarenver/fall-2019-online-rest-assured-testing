package com.automation.tests.practice.APIShoutTest;

import com.automation.pojos.Spartan;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class SpartanPUT {

    @BeforeAll
    public static void setup(){
        baseURI = "http://54.152.21.73:8000";
        authentication = basic("admin","admin");
    }


    @Test
    public void PUTRequest(){
        // we gonna send request body with update value, and content type header

        get("/api/spartans/{id}",101).prettyPeek();


        Spartan updateSpartan = new Spartan("AnkaPUT","Male",4567982130L);

        Response responseChange = given().
                                    contentType(ContentType.JSON).body(updateSpartan).
                            when().
                                    put("/api/spartans/{id}",101).prettyPeek();

        get("/api/spartans/{id}",101).prettyPeek();

    }


    // The #1 and big difference between put and patch is put request hole body but patch request only part of the body

    @Test
    public void PATCHRequest(){
        // we gonna send request body with update value, and content type header

        get("/api/spartans/{id}",101).prettyPeek();


        Map<String,Object> updateSpartan = new HashMap<>();
        updateSpartan.put("gender","Female");

        Response responseChange = given().
                contentType(ContentType.JSON).body(updateSpartan).
                when().
                patch("/api/spartans/{id}",101).prettyPeek();

        get("/api/spartans/{id}",101).prettyPeek();
    }





}
