package vehiclelogapp.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Entry {

    private int id;
    private Integer vehicleId;
    private int odometer;
    private Timestamp time;
    private String driver;
    private String entryType;

    public Entry() {
    }

    public Entry(int id, Integer vehicleId, int odometer, Timestamp time, String driver, String entryType) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.odometer = odometer;
        this.time = time;
        this.driver = driver;
        this.entryType = entryType;
    }

    public Entry(Integer vehicleId, int odometer, Timestamp time, String driver, String entryType) {
        this.vehicleId = vehicleId;
        this.odometer = odometer;
        this.time = time;
        this.driver = driver;
        this.entryType = entryType;
    }

    public Entry(int odometer, String driver, String entryType) {

        this.odometer = odometer;
        this.driver = driver;
        this.entryType = entryType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getEntryType() {
        return entryType;
    }

    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    @Override
    public String toString() {
        return time + ", " + odometer + ", " + driver + ", " + entryType;
    }

}
