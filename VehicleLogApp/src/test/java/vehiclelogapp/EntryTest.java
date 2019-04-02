
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
        entry2 = new Entry(1, 1, 13000, date, "Pekka", "Kuljetus");
        entry3 = new Entry(1, 13500, date, "Jukka", "Huolto");
        
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
    
    
}
