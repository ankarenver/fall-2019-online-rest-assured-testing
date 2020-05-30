package com.automation.tests.day8;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class BearerAuthentication {

    @BeforeAll
    public static void setup(){
        baseURI = "https://cybertek-reservation-api-qa.herokuapp.com/";
    }

    @Test
    public void loginTest(){
       Response response = given().
                queryParam("email", "teacherva5@gmail.com").
                queryParam("password", "maxpayne").
        when().
                get("/sign").prettyPeek();


       String token = response.jsonPath().getString("accessToken");
        System.out.println(token);

    }


    @Test
    @DisplayName("Negative test: attempt to retrive ")
    public void getRoomTest(){
        // 422 ok.  because anyways we did not get data
        // but we supposed to get 401 status code
        get("/api/rooms").prettyPeek().then().statusCode(401);
    }


    @Test
    public void getRoomsTest2(){
        //1. Request: to get a token.
        Response response = given().
                queryParam("email", "teacherva5@gmail.com").
                queryParam("password", "maxpayne").
                when().
                get("/sign");


        String token = response.jsonPath().getString("accessToken");

        //2. Request: use gotten token to get data
        Response response2 = given().
                                     auth().oauth2(token).
                              when().
                                     get("/api/rooms").prettyPeek();

    }


    @Test
    public void getAllTeamsTest(){
        // another way
        //given().header("Authorization","Bearer "+getToken()).when().get("/api/teams").prettyPeek();


        Response response = given().
                                    auth().oauth2(getToken()).
                            when().
                                    get("/api/teams").prettyPeek();

        response.then().statusCode(200);
    }




    /**
     * This method will sign in and returns the token
     * @return
     */
    @Test
    public String getToken(){
        Response response = given().
                queryParam("email", "teacherva5@gmail.com").
                queryParam("password", "maxpayne").
                when().
                get("/sign");
        response.then().log().ifError();


        String token = response.jsonPath().getString("accessToken");
        return token;
    }

    /**
     * This method will sign in and returns the token with argument which provide by user
     * @return
     */
    @Test
    public String getToken(String email, String password){
        Response response = given().
                queryParam("email", email).
                queryParam("password", password).
                when().
                get("/sign");
        response.then().log().ifError();


        String token = response.jsonPath().getString("accessToken");
        return token;
    }
}
