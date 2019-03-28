
package vehiclelogapp.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleLogService {
    
    private Map<String, ArrayList<Entry>> vehicleEntriesLog;
    private Map<String, Vehicle> vehicles;

    public VehicleLogService() {
        this.vehicleEntriesLog = new HashMap<>();
        this.vehicles = new HashMap<>();
    }
    
    public boolean addVehicle(String licensePlate, int kilometers) {
        if (vehicleEntriesLog.containsKey(licensePlate)) {
            return false;
        }
        Vehicle v = new Vehicle(licensePlate, kilometers);
        vehicles.put(licensePlate, v);
        vehicleEntriesLog.put(v.getLicensePlate(), v.getVehicleEntries());
        return true;
    }
    
    public boolean addEntry(String licenseplate, int km, String driver, String entryType) {
        if (!vehicleEntriesLog.containsKey(licenseplate)) {
            return false;
        }
        Vehicle v = vehicles.get(licenseplate);
        Entry e = new Entry(km, driver, entryType);
        e.setTime(LocalDateTime.now());
        ArrayList<Entry> entries = v.getVehicleEntries();
        entries.add(e);
        v.setVehicleEntries(entries);
        return true;
    }
    
    public ArrayList<Entry> list(String licensePlate) {
        if (!vehicleEntriesLog.containsKey(licensePlate)) {
            return null;
        }
        return vehicleEntriesLog.get(licensePlate);
    }
    
    
}
