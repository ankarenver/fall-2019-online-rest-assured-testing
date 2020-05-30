package com.automation.tests.day7;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Base64;
import java.util.List;
import java.util.Map;

import static  io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class APIKey {


    private final String API_KRY = "d2d79c8e";


    @BeforeAll
    public static void setup(){
        baseURI = "http://omdbapi.com/";
    }


    @Test
    public void getMovie(){
        /**
         * in this request, we do not have resource path
         * /movie - example of resource path
         */
        String moveToSearch = "Harry Potter";

        given().
                queryParam("t",moveToSearch).
                queryParam("apikey",API_KRY).
                accept(ContentType.JSON).
        when().
                get().prettyPeek().
        then().
                assertThat().statusCode(200);


        Response response =given().
                queryParam("t",moveToSearch).
                queryParam("apikey",API_KRY).
                accept(ContentType.JSON).
                when().
                get().prettyPeek();

        List<Map<String,String>> rating = response.jsonPath().get("Ratings");
        System.out.println(rating);
    }


    @Test
    public void authenticationTest(){
        String itemToSearch = "Frozen";
        Response response = given().
                queryParam("t", itemToSearch).
                when().
                get().prettyPeek();

        response.then().
                assertThat().
                statusCode(401).body("Error", is("No API key provided."));

    }


    /**
     *  this is how you decode Base64 code
     *  we can use Base64 class to encode or decode
     */
    @Test
    public void decodeBase64(){
        byte[] decode = Base64.getDecoder().decode("YWRtaW46YWRtaW4=");
        System.out.println(new String(decode));
    }


}
