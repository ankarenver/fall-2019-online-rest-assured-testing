package com.automation.tests.day6;

import com.automation.pojos.Employee;
import com.automation.utilities.ConfigurationReader;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class POJOPracticeWithOPDS {

    @BeforeAll
    public static void beforeAll(){
        baseURI = ConfigurationReader.getProperty("ORDS.URI");
    }


    @Test
    public void getEmployeeTest(){
        Response response = get("/employees/{id}",101).prettyPeek();


        Employee employee = response.as(Employee.class);
        System.out.println(employee);

        System.out.println();

        System.out.println(employee.getLinks());


    }
}
