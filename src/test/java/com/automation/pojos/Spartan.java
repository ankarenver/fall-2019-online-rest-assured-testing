package com.automation.pojos;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * This class represents spartan POJO
 * Example of JSON response:
 * {
 *     "id": 393,
 *     "name": "Michael Scott",
 *     "gender": "Male",
 *     "phone": 6969696969
 *
 * }
 */

public class Spartan {

    private int id;
    private String name;
    private  String gender;
    @SerializedName("phone")
    private long phoneNumber;

    public Spartan(String name, String gender, long phoneNumber){
        this.name=name;
        this.gender=gender;
        setPhoneNumber(phoneNumber);
    }

    public Spartan(int id, String name, String gender, long phoneNumber){
        this.id=id;
        this.name=name;
        this.gender=gender;
       setPhoneNumber(phoneNumber);
    }

    public Spartan(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhoneNumber(long phoneNumber) {
        if (String.valueOf(phoneNumber).length()<10){
            throw new RuntimeException("Phone number is too short!!");
        }
        this.phoneNumber = phoneNumber;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }


    @Override
    public String toString() {
        return "Spartan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spartan spartan = (Spartan) o;
        return id == spartan.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender, phoneNumber);
    }
}
