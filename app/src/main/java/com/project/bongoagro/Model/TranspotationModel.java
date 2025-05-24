package com.project.bongoagro.Model;

public class TranspotationModel {

    private int id;
    private int vehicleTypeId;
    private String departureStation;
    private String destination;
    private double rent;
    private String contactNumber;
    private int userId;

    // Constructor
    public TranspotationModel(int id, int vehicleTypeId, String departureStation, String destination, double rent, String contactNumber, int userId) {
        this.id = id;
        this.vehicleTypeId = vehicleTypeId;
        this.departureStation = departureStation;
        this.destination = destination;
        this.rent = rent;
        this.contactNumber = contactNumber;
        this.userId = userId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(int vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
        this.rent = rent;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

