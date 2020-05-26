package com.automation.review;

import io.restassured.response.Response;

import java.util.List;
import java.util.Scanner;

import static io.restassured.RestAssured.*;

public class WeatherAPP {
    static {
        baseURI = "https://www.metaweather.com/api/location";
    }



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter city name: ");
        String city = scanner.nextLine();
        String woeid= getWOEID(city);
        printWeatherInfo(woeid);





    }

    public static String getWOEID(String city){
        Response response = given().queryParams("query",city).get("/search").prettyPeek();
        return response.jsonPath().getString("woeid");
    }

    public static void printWeatherInfo(String woeid){
        woeid = woeid.replaceAll("\\D","");
        Response response = get("{woeid}",woeid).prettyPeek();

        List<String> weatherStateName = response.jsonPath().getList("consolidated_weather.weather_state_name");
        System.out.println(weatherStateName);

        List<Double> temp = response.jsonPath().getList("consolidated_weather.the_temp");
        System.out.println(temp);

        List<String> dates = response.jsonPath().getList("consolidated_weather.applicable_date");
        System.out.println(dates);
    }
}
