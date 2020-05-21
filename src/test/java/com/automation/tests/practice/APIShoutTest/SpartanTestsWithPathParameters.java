package com.automation.tests.practice.APIShoutTest;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SpartanTestsWithPathParameters {

    @BeforeAll
    public static void setup(){
        baseURI = "http://54.224.118.38:8000";
    }


    /**
     * Given accept type is Json
     * and id parameter value is 18
     * when user sends Get request to /api/spartans/{id}
     * then response status code should be 200
     * and response content-type: application/json;charset=UTF-8
     * and "Allen" should be in response payload
     */

    @Test
    public void test1(){
       Response response = given().auth().basic("admin","admin").accept(ContentType.JSON).when().get("/api/spartans/{id}",329).prettyPeek();

       // verify status code is 200
       response.then().assertThat().statusCode(200);

       // verify contentType is Json
       response.then().assertThat().contentType(ContentType.JSON);

       // verify body name is Loren
       response.then().assertThat().body("name", Matchers.is("Loren"));

       int id = response.body().path("id");
        System.out.println("id = " + id);

    }


    /**
     * Given accept type is Json
     * and id parameter value is 500
     * when user sends get request to /api/spartans/{id}
     * then response status code should be 404
     * and response content-type: application/json;charset=UTF-8
     * and "spartans not found" message should be in response payload
     */

    @Test
    public void test3(){
        Response response = given().auth().basic("admin", "admin").accept(ContentType.JSON).get("/api/spartans/{id}",120).prettyPeek();

        // verify response statusCode is 404
        response.then().assertThat().statusCode(404);

        // verify response contentType is Json
        response.then().assertThat().contentType(ContentType.JSON);

        // verify body as message "Spartan Not Found"
        response.then().body("message", Matchers.is("Spartan Not Found"));
    }


}
