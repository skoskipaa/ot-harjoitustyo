package vehiclelogapp.domain;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        licensePlate = licensePlate.toUpperCase().trim();

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
        licensePlate = licensePlate.toUpperCase().trim();

        Integer vehicleId = vehicleDao.getVehicleId(licensePlate);
        if (vehicleId == null) {
            return false;
        }
        Entry entryToAdd = new Entry(vehicleId, km, date, driver, entryType);
        Entry e = entryDao.create(entryToAdd);

        if (e == null) {
            return false;
        }
        return true;
    }

    public ArrayList<String> listVehicles() throws SQLException {
        ArrayList<Vehicle> vehicles = vehicleDao.list();
        ArrayList<String> queryResults = new ArrayList<>();
        vehicles.forEach((v) -> {
            queryResults.add(v.toString());
        });
        
        return queryResults;
    }

    public ArrayList<String> listEntriesForVehicle(String licensePlate) throws SQLException {
        licensePlate = licensePlate.toUpperCase().trim();
        Integer vehicleId = vehicleDao.getVehicleId(licensePlate);
        if (vehicleId == null) {
            return null;
        }

        ArrayList<Entry> entries = entryDao.listEntriesForVehicle(vehicleId);
        ArrayList<String> queryResults = new ArrayList<>();
        entries.forEach((e) -> {
            queryResults.add(e.toString());
        });
        return queryResults;
    }
    
    public int getLatestOdometer(String licensePlate) throws SQLException {
        licensePlate = licensePlate.toUpperCase().trim();
        Integer vehicleId = vehicleDao.getVehicleId(licensePlate);
        if (vehicleId == null) {
            return 0;
        }
        int result = entryDao.latestOdometerForVehicle(vehicleId);
        return result;
    }
    
    public boolean vehicleExists(String licensePlate) throws SQLException {
        licensePlate = licensePlate.toUpperCase().trim();
        Integer vehicleId = vehicleDao.getVehicleId(licensePlate);
        
        if (vehicleId == null) {
            return false;
        }
        
        return true;
    }
}
