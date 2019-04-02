
package vehiclelogapp.ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import vehiclelogapp.domain.VehicleLogService;


public class Main {
    
    public static void main(String[] args) throws SQLException {
        
        //setUpDatabase();
        Scanner reader = new Scanner(System.in);
        VehicleLogService service = new VehicleLogService();
        
        TextInterface ui = new TextInterface(reader, service);
        ui.startApplication();
        
       
    }
    
    private static void setUpDatabase() {

        try (Connection conn = DriverManager.getConnection("jdbc:h2:./logbook", "sa", "")) {

            conn.prepareStatement("DROP TABLE Vehicle IF EXISTS;").executeUpdate();
            conn.prepareStatement("DROP TABLE Entry IF EXISTS;").executeUpdate();

            conn.prepareStatement("CREATE TABLE Vehicle(id integer auto_increment primary key, plate varchar(30), odometer integer);").executeUpdate();
            conn.prepareStatement("CREATE TABLE Entry(id integer auto_increment primary key, vehicle_id integer, date timestamp(0), "
                    + "odometerread integer, driver varchar(30), type varchar(50), foreign key (vehicle_id) REFERENCES Vehicle(id));").executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage() + " " + e.getSQLState());
        }
    }
    
    
}
