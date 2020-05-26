package com.automation.tests.day6;

import com.automation.utilities.ConfigurationReader;
import org.junit.jupiter.api.BeforeAll;

import com.automation.pojos.Spartan;
import com.automation.utilities.ConfigurationReader;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.baseURI;

public class POJOPracticeWithSpartanApp {

    @BeforeAll
    public static void beforeAll(){
        baseURI = ConfigurationReader.getProperty("SPARTAN.URI");
        authentication = basic("admin","admin");
    }


    @Test
    public void addSpartanTest(){

        /**
         *{
         *   "gender": "Male",
         *   "name": "Nursultan",
         *   "phone": "123112312312"
         * }
         */

        Map<String, Object> spartan = new HashMap<>();
        spartan.put("gender","Male");
        spartan.put("name","enver");
        spartan.put("phone","123112387412");

        Response response = given().
                                    auth().basic("admin","admin").
                                    contentType(ContentType.JSON).accept(ContentType.JSON).
                                    body(spartan).
                            when().
                                    post("/spartans").prettyPeek();


        response.then().body("success", is("A Spartan is Born!"));
        response.then().statusCode(201);

        Spartan spartanResponse = response.jsonPath().getObject("data",Spartan.class);

        System.out.println("spartanResponse = " + spartanResponse);

        System.out.println(spartanResponse instanceof Spartan);// must be true if spartanResponse is Spartan Object


    }



    @Test
    public void updateSpartanTest(){
        int userToUpDate = 157;
        String name = "Nursultan";

        // HTTP put request to update exiting record.
        // PUT - requires to provide all parameters in body
        // PUT requires same body as POST
        // If you miss at least one parameter, it will not work


        Spartan spartan = new Spartan("ali","Male",56789123444L);

        //get spartan from web service
        Spartan spartanToUpdate = given().
                auth().basic("admin", "admin").
                accept(ContentType.JSON).
                when().
                get("/spartans/{id}", userToUpDate).as(Spartan.class);
        //update property that you need without affecting other properties
        System.out.println("Before update: " + spartanToUpdate);
        spartanToUpdate.setName(name);//change only name
        System.out.println("After update: " + spartanToUpdate);


        //request to update existing user with id 101
        Response response = given().
                                    auth().basic("admin","admin").contentType(ContentType.JSON).body(spartan).
                            when().
                                    put("/spartans/{id}",userToUpDate).prettyPeek();

        //verify that status code is 204 after update
        response.then().statusCode(204);


        System.out.println("***********************************************************");

        given().
                auth().basic("admin","admin").accept(ContentType.JSON).
        when().
                get("/spartans/{id}",userToUpDate).prettyPeek().
        then().
                statusCode(200).body("name", is(spartan.getName()));


    }


    @Test
    @DisplayName("Verify that user can perform PATCH request")
    public void patchUserTest1(){
        // patch - partial update of existing record

        int userId = 260;

        // let's put the code to take random user
        //get all list of spartans
        Response response0 = given().accept(ContentType.JSON).when().get("/spartans");
        // i can save them all into the array list
        List<Spartan> allSpartans = response0.jsonPath().getList("",Spartan.class);
        //  Spartan.class - data type of collection
        // getList - get JSON body as a list

        //generate random number
        Random random = new Random();
        int randomNumber = random.nextInt(allSpartans.size());
        int randomUserID = (int)allSpartans.get(randomNumber).getId();
        System.out.println(randomUserID);

        userId = randomUserID;  // to assign random user id


        System.out.println(allSpartans);

        Map<String, String> update = new HashMap<>();
        update.put("name","ainikeer");

        Response response = given().
                                    contentType(ContentType.JSON).
                                    body(update).
                            when().
                                    patch("/spartans/{id}",userId);

        response.then().assertThat().statusCode(204);

        // after we sent PATCH request, let's make sure that name is updated
        // this is a request to verify that name was updated and status code is correct as well
        given().
                accept(ContentType.JSON).
        when().
                get("/spartans/{id}",userId).prettyPeek().
        then().
                assertThat().statusCode(200).body("name", is("ainikeer"));






    }

}

