package vehiclelogapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import vehiclelogapp.dao.EntryDao;
import vehiclelogapp.dao.VehicleDao;
import vehiclelogapp.domain.VehicleLogService;

// Muokkaa daoa? Tietokantayhteyden muodostus jonnekin muualle?

public class VehicleLogServiceTest {
    
    private Connection conn;
    VehicleLogService testService;
    private VehicleDao testVehicleDao;
    private EntryDao testEntryDao;
    

    @Before
    public void setUpDatabase() {
        
       

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
    
   

    @After
    public void tearDown() throws SQLException {
        
        conn.close();
    }

    
}
