package vehiclelogapp;

import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import vehiclelogapp.domain.VehicleLogService;

public class VehicleLogServiceTest {

    private VehicleLogService testService;
    
    
    @Before
    public void conn() {
        testService = new VehicleLogService("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");
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
    public void lastOdometerCorrectNewCar() throws SQLException {
        testService.addVehicle("A4", 0);
        assertEquals(0, testService.getLatestOdometer("A4"));
    }
    
    
    @Test
    public void cannotAddEntryWithoutVehicle() throws SQLException {
        assertFalse(testService.addEntry("HJK77", 0, "JJ", "nn"));
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

    @Test
    public void noEntriesForNonExistentVehicle() throws SQLException {
        assertNull(testService.listEntriesForVehicle("YYA48"));
    }
    
    @Test
    public void canNotAddWithoutLicensePlate() throws SQLException {
        assertFalse(testService.addVehicle("", 0));
    }
    
//    @Test
//    public void odometerOkAfterEntry() throws SQLException {
//        testService.addVehicle("JJ99", 0);
//        testService.addEntry("JJ99", 15, "JJ", "nn");
//        assertEquals(15, testService.getLatestOdometer("JJ99"));
//    }


}
