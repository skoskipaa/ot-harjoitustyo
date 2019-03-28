
package vehiclelog.domain.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import vehiclelogapp.domain.Vehicle;

public class VehicleTest {
    
    private Vehicle vehicle;
    
    
    @Before
    public void setUp() {
        vehicle = new Vehicle("abc123", 123456);
    }
    
    @Test
    public void vehicleExists() {
        assertTrue(vehicle != null);
    }

    
}
