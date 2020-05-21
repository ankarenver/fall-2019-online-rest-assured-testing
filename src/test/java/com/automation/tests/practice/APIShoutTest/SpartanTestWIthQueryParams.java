package com.automation.tests.practice.APIShoutTest;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpartanTestWIthQueryParams {

    @BeforeAll
    public static void setup(){
        baseURI="http://54.224.118.38:8000";
    }

    /**
     * Given accept type is json
     * and query parameter values are:
     * gender| female
     * nameContains|j
     * when user sends get request to /api/spartans/search
     * then response status code should be 200
     * and response content-type :
     * and 'female' should be in response payload
     * and 'janette' should be in response payload
     */
    @Test
    public void test1(){
        Response response = given().
                                    auth().basic("admin","admin").
                                    accept(ContentType.JSON).
                            and().
                                    queryParam("gender","Female").
                            and().
                                    queryParam("nameContains","A").
                            when().
                                     get("/api/spartans/search").prettyPeek();

        response.then().contentType(ContentType.JSON);
        response.then().statusCode(200);


    }


    @Test
    public void test2(){
        Map<String, Object> map = new HashMap<>();
        map.put("gender","Female");
        map.put("nameContains","uy");

        Response response = given().
                                    auth().basic("admin","admin").accept(ContentType.JSON).
                            and().
                                    queryParams(map).
                            when().
                                    get("/api/spartans/search").prettyPeek();
    }




}
