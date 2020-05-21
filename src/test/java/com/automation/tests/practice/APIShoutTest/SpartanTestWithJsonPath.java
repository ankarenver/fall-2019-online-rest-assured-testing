package com.automation.tests.practice.APIShoutTest;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class SpartanTestWithJsonPath {

    @BeforeAll
    public static void setup(){
        baseURI = "http://54.224.118.38:8000";
    }


    /**
     * given accept type os json
     * and path param spartan id is 11
     * when user sends a get request to /api/spartans/{id}
     * then status code is 200
     * and content type is Json
     * and
     *
     */

    @Test
    public void test1(){
        Response response = given().
                                    auth().basic("admin","admin").accept(ContentType.JSON).
                            when().
                                    get("/api/spartans/{id}",329).prettyPeek();

        // this is how to read value with path() method
        int id = response.path("id");
        System.out.println("id = " + id);

        // how to read value with JsonPath
        JsonPath jsonData = response.jsonPath();

        int idByJsonPath = jsonData.getInt("id");
        String nameByJsonPath = jsonData.getString("name");
        String genderByJsonPath = jsonData.getString("gender");
        Long phoneNumberByJsonPath = jsonData.getLong("phone");

        System.out.println("idByJsonPath = " + idByJsonPath);
        System.out.println("nameByJsonPath = " + nameByJsonPath);
        System.out.println("genderByJsonPath = " + genderByJsonPath);
        System.out.println("phoneNumberByJsonPath = " + phoneNumberByJsonPath);

    }

}
