package com.example.thelastone.Model;

public class ChildModel {

     String name, age, vaccine, gender, hospital, dob;


    public ChildModel() {
    }

    public ChildModel(String name, String age, String vaccine, String gender, String hospital, String dob) {
        this.name = name;
        this.age = age;
        this.vaccine = vaccine;
        this.gender = gender;
        this.hospital = hospital;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
