package vehiclelogapp;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import vehiclelogapp.domain.Vehicle;

public class VehicleTest {

    private Vehicle vehicle;
    private Vehicle vehicle2;

    @Before
    public void setUp() {
        vehicle = new Vehicle("abc123", 123456);
        vehicle2 = new Vehicle(1, "hih111", 13000);

    }

    @Test
    public void vehicleExists() {
        assertTrue(vehicle != null && vehicle2 != null);

    }

    @Test
    public void getId() {
        assertEquals(1, vehicle2.getId());
    }

    @Test
    public void setId() {
        vehicle.setId(2);
        assertEquals(2, vehicle.getId());
    }

    @Test
    public void getLicensePlate() {
        assertEquals("hih111", vehicle2.getLicensePlate());
    }

    @Test
    public void setlicensePlate() {
        vehicle2.setLicensePlate("hii51");
        assertEquals("hii51", vehicle2.getLicensePlate());
    }

    @Test
    public void getKilometers() {
        assertEquals(123456, vehicle.getKilometers());
    }

    @Test
    public void setKilometers() {
        vehicle2.setKilometers(14000);
        assertEquals(14000, vehicle2.getKilometers());
    }

}
