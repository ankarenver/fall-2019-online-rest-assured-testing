package com.automation.tests.practice.APIShoutTest;


import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SpartanTest {


    String spartanBaseUrl = "http://54.224.118.38:8000";

    @Test
    public void getSpartans1(){
         Response response = given().auth().basic("admin","admin").baseUri(spartanBaseUrl).when().get("/api/spartans");

         // to get all data in
         response.prettyPeek();

         //print status code
        System.out.println(response.statusCode());

    }

    /**
     * when user send GET request to /api/spartans end point
     * then status code must be 200
     * and body should contains Audrey
     */
    @Test
    public void getSpartans2(){
        Response response = given().auth().basic("admin","admin").get(spartanBaseUrl+"/api/spartans");

        // verify status code 200
        Assertions.assertEquals(200,response.statusCode());

        //verify body should contains Audrey
        Assertions.assertTrue(response.body().asString().contains("Audrey"));
    }


    /**
     * Given accept type is Json
     * when user sends a get request to spartanAllURL
     * then response status code is 200
     * and response body should be json format
     */
    @Test
    public void  getSpartans3(){
        Response response = given().
                                   auth().basic("admin","admin").accept(ContentType.JSON).baseUri(spartanBaseUrl).
                            when().
                                   get("/api/spartans");

        // verify status code
        Assertions.assertEquals(200,response.statusCode());


        //verify response body json
        Assertions.assertEquals("application/json;charset=UTF-8",response.contentType());
        // another way to verify
        response.then().assertThat().contentType(ContentType.JSON);
    }


}
