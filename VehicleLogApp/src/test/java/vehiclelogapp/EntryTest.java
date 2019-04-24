
package vehiclelogapp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import vehiclelogapp.domain.Entry;


public class EntryTest {
    private Entry entry;
    private Entry entry2;
    private Entry entry3;
    
    @Before
    public void setUp() {
        Timestamp date = Timestamp.valueOf(LocalDateTime.now());
        
        entry = new Entry();
        entry2 = new Entry(1, 1, 13000, date, "Pekka", "Kuljetus", 0);
        entry3 = new Entry(1, 13500, date, "Jukka", "Huolto", 0);
        
    }
    
    @Test
    public void constructorsOk() {
        assertTrue(entry != null && entry2 != null && entry3 != null);
    }
    
    @Test
    public void getId() {
        assertEquals(1, entry2.getId());
    }
    
    @Test
    public void setId() {
        entry3.setId(2);
        assertEquals(2, entry3.getId());
    }
    
    @Test
    public void getVehicleId() {
        Integer res = 1;
        assertEquals(res, entry2.getVehicleId());
    }
    
    @Test
    public void setVehicleId() {
        entry.setVehicleId(3);
        Integer res = 3;
        assertEquals(res, entry.getVehicleId());
    }
    
    @Test
    public void getOdometer() {
        assertEquals(13500, entry3.getOdometer());
    }
    
    @Test
    public void setOdometer() {
        entry3.setOdometer(14000);
        assertEquals(14000, entry3.getOdometer());
    }
    
    @Test
    public void getDriverOk() {
        String res = entry3.getDriver();
        assertEquals("Jukka", res);
    }
    
    @Test
    public void setDriverOk() {
        entry.setDriver("Pirjo");
        assertEquals("Pirjo", entry.getDriver());
    }
    
    @Test
    public void entryType() {
        entry.setEntryType("Huolto");
        assertEquals("Huolto", entry.getEntryType());
    }
    
    @Test
    public void getEntryType() {
        assertEquals("Kuljetus", entry2.getEntryType());
    }
    
}
