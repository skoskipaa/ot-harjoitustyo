package vehiclelogapp.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import vehiclelogapp.dao.EntryDao;
import vehiclelogapp.dao.VehicleDao;

public class VehicleLogService {

    private VehicleDao vehicleDao;
    private EntryDao entryDao;
    private String database;


    public VehicleLogService(String database, String user, String pw) {

        this.database = database;
        this.entryDao = new EntryDao(database);
        this.vehicleDao = new VehicleDao(database);
        setUpDatabase(database, user, pw);

    }

    public boolean addVehicle(String licensePlate, int kilometers) throws SQLException {
        if (isNotValid(licensePlate)) {
            return false;
        }
        licensePlate = licensePlate.toUpperCase().trim();
        Integer key = vehicleDao.getVehicleId(licensePlate);
        if (key != null) {
            return false;
        }
        Vehicle vehicleToAdd = new Vehicle(licensePlate, kilometers);
        Vehicle v = vehicleDao.create(vehicleToAdd);
        if (v == null) {
            return false;
        } else {
            addEntry(licensePlate, kilometers, "admin", "aloitussyöttö");
        }
        return true;
    }

    public boolean addEntry(String licensePlate, int km, String driver, String entryType) throws SQLException {
        if (isNotValid(licensePlate)) {
            return false;
        }
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

    private static void setUpDatabase(String database, String user, String pw) {

        try (Connection conn = DriverManager.getConnection(database, user, pw)) {

            conn.prepareStatement("CREATE TABLE IF NOT EXISTS Vehicle(id integer auto_increment primary key, plate varchar(30), odometer integer);").executeUpdate();
            conn.prepareStatement("CREATE TABLE IF NOT EXISTS Entry(id integer auto_increment primary key, vehicle_id integer, date timestamp(0), "
                    + "odometerread integer, driver varchar(30), type varchar(50), foreign key (vehicle_id) REFERENCES Vehicle(id));").executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage() + " " + e.getSQLState());
        }
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

    public static boolean isNotValid(String s) {
        return (s.isEmpty() || s.equals(" "));
    }
}
