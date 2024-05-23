package com.example.covidmanagementsystem;

public class Patient {
    private String name;
    private String address;
    private String province;
    private String phone;
    private String pictureUrl;
    private String medicalStatus;
    private String patientId;

    // Constructor
    public Patient(String name, String address, String province, String phone, String pictureUrl, String medicalStatus, String patientId) {
        this.name = name;
        this.address = address;
        this.province = province;
        this.phone = phone;
        this.pictureUrl = pictureUrl;
        this.medicalStatus = medicalStatus;
        this.patientId = patientId;
    }

    // Default constructor required for calls to DataSnapshot.getValue(Patient.class)
    public Patient() {
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getProvince() {
        return province;
    }

    public String getPhone() {
        return phone;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public String getMedicalStatus() {
        return medicalStatus;
    }

    public String getPatientId() {
        return patientId;
    }

    // Setters (if needed)
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public void setMedicalStatus(String medicalStatus) {
        this.medicalStatus = medicalStatus;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}
