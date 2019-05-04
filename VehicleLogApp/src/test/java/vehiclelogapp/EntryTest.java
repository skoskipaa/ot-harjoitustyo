package vehiclelogapp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import vehiclelogapp.domain.Entry;

public class EntryTest {

    private Entry entry;
    private Entry entry1;
    private Entry entry2;

    @Before
    public void setUp() {
        Timestamp date = Timestamp.valueOf(LocalDateTime.now());

        entry1 = new Entry(1, 1, 13000, date, "Pekka", "Kuljetus", 0);
        entry2 = new Entry(2, 13500, date, "Jukka", "Huolto", 0);

    }

    @Test
    public void constructorsOk() {
        assertTrue(entry1 != null && entry2 != null);
    }

    @Test
    public void getId() {
        assertEquals(1, entry1.getId());
    }

    @Test
    public void setId() {
        entry2.setId(5);
        assertEquals(5, entry2.getId());
    }

    @Test
    public void getVehicleId() {
        Integer res = 1;
        assertEquals(res, entry1.getVehicleId());
    }

    @Test
    public void getOdometer() {
        assertEquals(13500, entry2.getOdometer());
    }

    @Test
    public void setOdometer() {
        entry2.setOdometer(14000);
        assertEquals(14000, entry2.getOdometer());
    }

    @Test
    public void getDriverOk() {
        String res = entry2.getDriver();
        assertEquals("Jukka", res);
    }

    @Test
    public void getEntryType() {
        assertEquals("Kuljetus", entry1.getEntryType());
    }

}
