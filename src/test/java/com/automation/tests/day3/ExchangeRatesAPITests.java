package com.automation.tests.day3;

import io.restassured.http.Headers;
import io.restassured.response.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class ExchangeRatesAPITests {

    @BeforeAll
    public static void setup(){
        // for every single request this is a base URI
        baseURI = "http://api.openrates.io";
    }

    //get latest currency rates
    @Test
    @DisplayName("Get latest name")
    public void getLatestRate(){
        // after ? we specify query parameters. If there are couple pf them we use & to concatenate
        //http://www.google.com/index.html?q=apple&zip=123123
        // q - query parameter
        // zip = another query parameter
        // with rest assured, we provide query parameters into given() part
        // given() - request preparation
        // you can specify query parameters in URL explicitly: http://api.openrates.io/latest?base=USD
        // rest assured, will just assemble URL for you
        Response response = given().
                                queryParam("base","USD").
                            when().
                                get("/latest").prettyPeek();
        response.then().assertThat().statusCode(200);

        // same above
//        get("/latest").prettyPeek().then().statusCode(200);

        // "",  Matchers.is("")  -- check equallity
        response.then().assertThat().body("base", is("USD"));
        response.then().assertThat().body("date", is(LocalDate.now().toString().replace("<","").replace(">","")));

    }

    @Test
    public void getHistoryOfRates(){
       Response response= given().
                                    queryParam("base","USD").
                            when().
                                    get("/2008-01-02").prettyPeek();


        Headers headers = response.getHeaders();

        response.then().
                       assertThat().statusCode(200).
                and().
                       body("date", is("2008-01-02")).
                and().
                       body("rates.USD",is(1.0f));

        Float param = response.jsonPath().get("rates.EUR");
        System.out.println(param);

    }
}
