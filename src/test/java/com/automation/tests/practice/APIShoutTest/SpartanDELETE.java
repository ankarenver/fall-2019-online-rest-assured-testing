package com.automation.tests.practice.APIShoutTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.*;


public class SpartanDELETE {

    @BeforeAll
    public static void setup(){
        baseURI = "http://54.152.21.73:8000";
        authentication = basic("admin","admin");
    }

    @Test
    public void deleteOneSpartan(){
        given().when().delete("/api/spartans/{id}",101).prettyPeek().then().assertThat().statusCode(204);
    }

}
