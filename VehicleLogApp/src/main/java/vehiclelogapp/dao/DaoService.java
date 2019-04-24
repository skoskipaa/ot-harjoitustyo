package vehiclelogapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Tämä luokka huolehtii tietokannan luomisesta ja tietokantayhteydestä.
 *
 */
public class DaoService {

    private String database;
    private String username;
    private String password;

    public DaoService(String database, String username, String password) {

        this.database = database;
        this.username = username;
        this.password = password;

        setUpDatabase(database, username, password);
    }

    public DaoService() {
    }
    
    public Connection getDbConnection(String db, String user, String pwd) throws SQLException {
        Connection conn = DriverManager.getConnection(db, user, pwd);
        return conn;
    }

    private static void setUpDatabase(String database, String user, String pw) {

        try (Connection conn = DriverManager.getConnection(database, user, pw)) {

            conn.prepareStatement("CREATE TABLE IF NOT EXISTS Vehicle(id integer auto_increment primary key, plate varchar(30), odometer integer);").executeUpdate();
            conn.prepareStatement("CREATE TABLE IF NOT EXISTS Entry(id integer auto_increment primary key, vehicle_id integer, date timestamp(0), "
                    + "odometerread integer, driver varchar(30), type varchar(50), last_trip integer, foreign key (vehicle_id) REFERENCES Vehicle(id));").executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage() + " " + e.getSQLState());
        }
    }

}
