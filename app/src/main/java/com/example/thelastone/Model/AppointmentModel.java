package com.example.thelastone.Model;

public class AppointmentModel {
    String name, doctor, AppointmentDate, AppointmentTime;

    public AppointmentModel() {
    }

    public AppointmentModel(String name, String doctor, String appointmentDate, String appointmentTime) {
        this.name = name;
        this.doctor = doctor;
        AppointmentDate = appointmentDate;
        AppointmentTime = appointmentTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getAppointmentDate() {
        return AppointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        AppointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return AppointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        AppointmentTime = appointmentTime;
    }


}

