package com.automation.tests.practice.APIShoutTest;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class SpartanTestsWithPathMethod {


    @BeforeAll
    public static void setup(){
        baseURI="http://54.224.118.38:8000";
    }


    @Test
    public void test3(){
        Response response = given().
                auth().basic("admin","admin").
                get("/api/spartans").
                prettyPeek();

        // extract first id
        int firstID = response.path("id[0]");
        System.out.println("firstID = " + firstID);

        // extract fist 1st name
        String first1stName = response.path("name[0]");
        System.out.println("first1stName = " + first1stName);

        //extract fist gender
        String firstGender = response.path("gender[0]");
        System.out.println("firstGender = " + firstGender);

        //extract fist phone number
        Long firstPhoneNumber = response.path("phone[0]");
        System.out.println("firstPhoneNumber = " + firstPhoneNumber);


        //extract last last_name
        String last_lastName = response.path("name[-1]");
        System.out.println("last_lastName = " + last_lastName);


        //extract all firstName and print them
        List<String> allNames = response.path("name");
        System.out.println("allNames = " + allNames);

        // extract all phone number
        List<Long> allPhoneNumber = response.path("phone");
        System.out.println("allPhoneNumber = " + allPhoneNumber);
    }
}
