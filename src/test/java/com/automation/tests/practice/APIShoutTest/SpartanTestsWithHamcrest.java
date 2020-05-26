package com.automation.tests.practice.APIShoutTest;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SpartanTestsWithHamcrest {


    @BeforeAll
    public static void setup(){
        baseURI = "http://54.224.118.38:8000";
    }


    /**
     * given accept type is Json
     * and path param id is 657
     * when user sends a get request to spartans/{id}
     * then status code is 200
     * and content type is Json
     * and Json data has following
     *
     *           "id": 657,
     *           "name": "Shantel",
     *           "gender": "Male",
     *           "phone": 1074677053
     *
     */


    @Test
    public void test1(){
        Response response = given().
                                    auth().basic("admin","admin").contentType(ContentType.JSON).
                            when().
                                    get("/api/spartans/{id}",657).prettyPeek();


        response.then().statusCode(200);
        response.then().contentType(ContentType.JSON);
        response.then().body("id", Matchers.is(657));
        response.then().body("name",Matchers.is("Shantel"));
        response.then().body("gender",Matchers.is("Male"));
        response.then().body("phone",Matchers.is(1074677053));
    }






}
