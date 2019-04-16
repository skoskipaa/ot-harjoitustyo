package vehiclelogapp.domain;

import java.util.ArrayList;

public class Vehicle {

    private int id;
    private String licensePlate;
    private int kilometers;

    public Vehicle() {
    }

    public Vehicle(String licensePlate, int kilometers) {
        this.licensePlate = licensePlate;
        this.kilometers = kilometers;
    }

    public Vehicle(int id, String licensePlate, int kilometers) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.kilometers = kilometers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getKilometers() {
        return kilometers;
    }

    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }

    @Override
    public String toString() {
        return licensePlate; // + ", matkamittarin ensimmäinen lukema: " + kilometers;
    }

}
