package vehiclelogapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import vehiclelogapp.domain.VehicleLogService;

public class VehicleLogServiceTest {

    static Connection conn;
    private VehicleLogService testService;
    
    
    @BeforeClass
    public static void setUp() {
        
        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "")) {

            conn.prepareStatement("DROP TABLE Vehicle IF EXISTS;").executeUpdate();
            conn.prepareStatement("DROP TABLE Entry IF EXISTS;").executeUpdate();

            conn.prepareStatement("CREATE TABLE Vehicle(id integer auto_increment primary key, plate varchar(30), odometer integer);").executeUpdate();
            conn.prepareStatement("CREATE TABLE Entry(id integer auto_increment primary key, vehicle_id integer, date timestamp(0), "
                    + "odometerread integer, driver varchar(30), type varchar(50), foreign key (vehicle_id) REFERENCES Vehicle(id));").executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage() + " " + e.getSQLState());

        }
    }

    @Before
    public void conn() {
        testService = new VehicleLogService("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
    }

    @Test
    public void addVehicleToDatabase() throws SQLException {
        assertTrue(testService.addVehicle("HII51", 15000));
    }
    
    @Test
    public void addExistingVehicleToDatabaseNotPossible() throws SQLException {
        testService.addVehicle("ABC123", 1);
        assertFalse(testService.addVehicle("ABC123", 500));
    }

    @Test
    public void listVehiclesNotNull() throws SQLException {
        testService.addVehicle("HIH111", 1100);
        ArrayList<String> list = testService.listVehicles();
        assertTrue(!list.isEmpty());
    }
    
    @Test
    public void vehicleExists() throws SQLException {
        assertFalse(testService.vehicleExists("AAA111"));
    }
    
    @Test
    public void vehicleExistsTrue() throws SQLException {
        testService.addVehicle("ELV15", 1977);
        assertTrue(testService.vehicleExists("ELV15"));
    }
    
    @Test
    public void lastOdometerCorrect() throws SQLException {
        testService.addVehicle("A4", 0);
        testService.addEntry("A4", 15, "MrX", "Kuljetus");
        assertEquals(15, testService.getLatestOdometer("A4"));
    }
    
    @Test
    public void addEntryOk() throws SQLException {
        testService.addVehicle("HJK99", 0);
        assertTrue(testService.addEntry("HJK99", 2400, "Jaska", "huolto"));
        
    }
    
    @Test
    public void listEntriesOk() throws SQLException {
        testService.addVehicle("PIP0", 1);
        testService.addEntry("PIP0", 15, "Jonne", "turha");
        ArrayList<String> res = testService.listEntriesForVehicle("PIP0");
        assertTrue(!res.isEmpty());
    }



}
