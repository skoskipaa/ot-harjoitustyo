
package vehiclelogapp.domain;

import java.time.LocalDateTime;

/* This class is for creating an entry. */

public class Entry {
    
    private int id;                 
    private Vehicle vehicle;
    private int odometer;
    private LocalDateTime time;     //luodaan automaattisesti?
    private String driver;
    private String entryType;
    
    public Entry() {
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

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
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
        return  "Odometer=" + odometer + ", time=" + time + ", driver=" + driver + ", entryType=" + entryType;
    }
    
    
                                    
    
}
