package com.example.thelastone.Model;

public class DoctorModel {
    String Fullname, Age, Address, Qualification, Specification, Discription,RegistrationNumber,id;
    String imageURL;

    public DoctorModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DoctorModel(String fullname, String age, String address, String qualification, String specification, String discription, String registrationNumber, String id, String imageURL) {
        Fullname = fullname;
        Age = age;
        Address = address;
        Qualification = qualification;
        Specification = specification;
        Discription = discription;
        RegistrationNumber = registrationNumber;
        this.id = id;
        this.imageURL = imageURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getQualification() {
        return Qualification;
    }

    public void setQualification(String qualification) {
        Qualification = qualification;
    }

    public String getSpecification() {
        return Specification;
    }

    public void setSpecification(String specification) {
        Specification = specification;
    }

    public String getDiscription() {
        return Discription;
    }

    public void setDiscription(String discription) {
        Discription = discription;
    }

    public String getRegistrationNumber() {
        return RegistrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        RegistrationNumber = registrationNumber;
    }
}
