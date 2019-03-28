
package vehiclelogapp.domain;

import java.util.ArrayList;


public class Vehicle {
    
    private int id;                 
    private String licensePlate;
    private int kilometers;
    private ArrayList<Entry> vehicleEntries;

    public Vehicle() {
        
    }

    public Vehicle(String licensePlate, int kilometers) {
        this.licensePlate = licensePlate;
        this.kilometers = kilometers;
        vehicleEntries = new ArrayList<>();
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

    public ArrayList<Entry> getVehicleEntries() {
        return vehicleEntries;
    }

    public void setVehicleEntries(ArrayList<Entry> vehicleEntries) {
        this.vehicleEntries = vehicleEntries;
    }
    
    
    
    
}
