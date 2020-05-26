package com.automation.tests.practice.APIShoutTest;

import com.automation.pojos.Spartan;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;


public class SpartanTestsPOJODeserization {


    @Test
    public void gSonExampleConvertingBackAndFore(){
        Gson gson = new Gson();

        String myJsonBody = "{\n" +
                "  \"gender\": \"Male\",\n" +
                "  \"name\": \"denver\",\n" +
                "  \"phone\": \"13112312312\"\n" +
                "}";

        // gson.fromJson method
        //using Gson method to do de-serialize our json body to any java object
        Spartan spartan1 =gson.fromJson(myJsonBody,Spartan.class);
        System.out.println(spartan1);

        // gson.toJson method
        // using Gson method to do serialize our spartan object to json object
        Spartan spartanToConvertJson = new Spartan(42,"osman","male",1234567890);
        String jsonBody = gson.toJson(spartanToConvertJson);
        System.out.println(jsonBody);
    }
}
