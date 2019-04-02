package vehiclelogapp.domain;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jdk.nashorn.internal.runtime.regexp.joni.EncodingHelper;
import vehiclelogapp.dao.EntryDao;
import vehiclelogapp.dao.VehicleDao;

public class VehicleLogService {

    private VehicleDao vehicleDao;
    private EntryDao entryDao;

    public VehicleLogService() {

        this.vehicleDao = new VehicleDao();
        this.entryDao = new EntryDao();

    }

    public boolean addVehicle(String licensePlate, int kilometers) throws SQLException {
        licensePlate = licensePlate.toUpperCase().trim();    //////////

        Integer key = vehicleDao.getVehicleId(licensePlate);
        if (key != null) {
            return false;
        }

        Vehicle vehicleToAdd = new Vehicle(licensePlate, kilometers);
        Vehicle v = vehicleDao.create(vehicleToAdd);
        if (v == null) {
            return false;
        }
        return true;
    }

    public boolean addEntry(String licensePlate, int km, String driver, String entryType) throws SQLException {
        Timestamp date = Timestamp.valueOf(LocalDateTime.now());
        licensePlate = licensePlate.toUpperCase().trim();    //////////

        Integer vehicle_id = vehicleDao.getVehicleId(licensePlate);
        if (vehicle_id == null) {
            return false;
        }
        Entry entryToAdd = new Entry(vehicle_id, km, date, driver, entryType);
        Entry e = entryDao.create(entryToAdd);

        if (e == null) {
            return false;
        }
        return true;
    }

    public ArrayList<Vehicle> listVehicles() throws SQLException {
        ArrayList<Vehicle> vehicles = vehicleDao.list();
        return vehicles;
    }

    public ArrayList<Entry> listAllEntries() throws SQLException {
        ArrayList<Entry> entries = entryDao.list();
        return entries;
    }

    public ArrayList<Entry> listEntriesForVehicle(String licensePlate) throws SQLException {
        licensePlate = licensePlate.toUpperCase().trim();    //////////
        Integer vehicle_id = vehicleDao.getVehicleId(licensePlate);
        if (vehicle_id == null) {
            return null;
        }

        ArrayList<Entry> entries = entryDao.listEntriesForVehicle(vehicle_id);
        return entries;
    }
}
