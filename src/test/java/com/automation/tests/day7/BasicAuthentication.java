package com.automation.tests.day7;

import static io.restassured.RestAssured.*;

import com.automation.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.io.File;

public class BasicAuthentication {


    @Test
    public void spartanAuthentication(){
        baseURI = ConfigurationReader.getProperty("SPARTAN.URI");
        given().
                auth().basic("admin","admin").
        when().
                get("/spartans").prettyPeek().
        then().
                statusCode(200);
    }


    @Test
    public void authorizationTest(){
        baseURI = ConfigurationReader.getProperty("SPARTAN.URI");
        File file = new File("spartan1.json");
        given().
                auth().basic("user","user").
                contentType(ContentType.JSON).body(file).
        when().
                post("/spartans").prettyPeek().
        then().
                statusCode(403);
        /**
         * user - user does not have wright to add, delete or edit users. only read.
         * admin - has a permission to add new users.
         * 403 - Forbidden access, you logged in, but you are trying to do something that you are not allowed.
         * Authentication problem - you didn't login
         * Authorization problem - you logged in but cannot do some actions.
         */
    }


    @Test
    public void authenticationTest(){
        baseURI = ConfigurationReader.getProperty("SPARTAN.URI");

        // if do not provide credentials, we must get 401 status code
        get("/spartans").prettyPeek().then().statusCode(401);

    }


    @Test
    public void authenticationTest2(){
        baseURI = "http://practice.cybertekschool.com";
        
        given().
                auth().basic("admin", "admin").
        when().
                get("/basic_auth").prettyPeek().
        then().
                statusCode(200).
                contentType(ContentType.HTML);

    }




}
