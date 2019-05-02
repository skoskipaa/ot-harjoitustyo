package vehiclelogapp.domain;

import java.sql.Timestamp;

/**
 * Ajoneuvolle syötettävää tapahtumaa kuvaava luokka.
 *
 */
public class Entry {

    private int id;
    private Integer vehicleId;
    private int odometer;
    private Timestamp time;
    private String driver;
    private String entryType;
    private int lastTrip;

    /**
     * Tietokannan käyttämä konstruktori.
     * 
     * @param id    automaattisesti luotava tapahtuman yksilöivä id 
     * @param vehicleId automaattisesti tietokannan hakema ajoneuvon tapahtumalle yksilöivä id
     * @param odometer  matkamittarin lukema
     * @param time  tapahtuman aika
     * @param driver    kuljettaja
     * @param entryType tapahtuman selite
     * @param lastTrip  matkamittarin edellinen lukema
     */
    public Entry(int id, Integer vehicleId, int odometer, Timestamp time, String driver, String entryType, int lastTrip) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.odometer = odometer;
        this.time = time;
        this.driver = driver;
        this.entryType = entryType;
        this.lastTrip = lastTrip;
    }
    
    /**
     * VehicleLogservicen tietokantaan tallennuksessa käyttämä konstruktori
     * 
     * @param vehicleId ajoneuvon yksilöivä id (haetaan tietokannasta)
     * @param odometer  matkamittarin lukema
     * @param time  tapahtuman aika (luodaan automaattisesti)
     * @param driver    kuljettaja
     * @param entryType tapahtuman selite
     * @param lastTrip  matkamittarin edellinen lukema
     */
    public Entry(Integer vehicleId, int odometer, Timestamp time, String driver, String entryType, int lastTrip) {
        this.vehicleId = vehicleId;
        this.odometer = odometer;
        this.time = time;
        this.driver = driver;
        this.entryType = entryType;
        this.lastTrip = lastTrip;
    }

    /**
     * VehicleLogServicen käyttämä konstruktori käyttäjän syötteiden käsittelyyn.
     * @param odometer
     * @param driver
     * @param entryType 
     */
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

    public int getLastTrip() {
        return lastTrip;
    }

    public void setLastTrip(int lastTrip) {
        this.lastTrip = lastTrip;
    }

    /**
     * Tulostuksia varten muotoiltu toString()
     *
     * @return Palauttaa suoraan tulostettavan kuvauksen oliosta
     */
    @Override
    public String toString() {
        return time + "\tMatkamittarin lukema: " + odometer + " (Trip: " + lastTrip + " km)" + "\t"
                + driver + "; " + entryType;
    }

}
