package vehiclelogapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import vehiclelogapp.domain.Entry;

public class EntryDao implements Dao<Entry, Integer> {
    
    private String databaseUrl;
    

    public EntryDao(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }
    
    

    @Override
    public Entry create(Entry entry) throws SQLException {

        Connection conn = DriverManager.getConnection(databaseUrl, "sa", "");
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Entry (vehicle_id, date, odometerread, driver, type)"
                + " VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, entry.getVehicleId());
        stmt.setTimestamp(2, entry.getTime());
        stmt.setInt(3, entry.getOdometer());
        stmt.setString(4, entry.getDriver());
        stmt.setString(5, entry.getEntryType());
        stmt.executeUpdate();

        // Palautetaan olio luodulla avaimella:
        int id = -1;
        ResultSet generatedKeys = stmt.getGeneratedKeys();
        if (generatedKeys.next()) {
            id = generatedKeys.getInt(1);
        }
        generatedKeys.close();
        stmt.close();
        conn.close();
        return read(id);
    }

    @Override
    public Entry read(Integer key) throws SQLException {

        Connection conn = DriverManager.getConnection(databaseUrl, "sa", "");
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Entry WHERE id = ?");
        stmt.setInt(1, key);
        ResultSet rs = stmt.executeQuery();

        if (!rs.next()) {
            return null;
        }
        Entry returnEntry = new Entry(rs.getInt("id"), rs.getInt("vehicle_id"), rs.getInt("odometerread"),
                rs.getTimestamp("date"), rs.getString("driver"), rs.getString("type"));

        stmt.close();
        rs.close();
        conn.close();
        return returnEntry;

    }

   
    @Override
    public ArrayList<Entry> list() throws SQLException {

        Connection conn = DriverManager.getConnection(databaseUrl, "sa", "");
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Entry");
        ResultSet rs = stmt.executeQuery();

        ArrayList<Entry> entries = new ArrayList<>();

        while (rs.next()) {
            Entry toAdd = new Entry(rs.getInt("id"), rs.getInt("vehicle_id"), rs.getInt("odometerread"),
                    rs.getTimestamp("date"), rs.getString("driver"), rs.getString("type"));
            entries.add(toAdd);
        }
        stmt.close();
        rs.close();
        conn.close();
        return entries;
    }

    public ArrayList<Entry> listEntriesForVehicle(Integer vehicleId) throws SQLException {

        Connection conn = DriverManager.getConnection(databaseUrl, "sa", "");
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Entry WHERE vehicle_id = ?");
        stmt.setInt(1, vehicleId);
        ResultSet rs = stmt.executeQuery();

        ArrayList<Entry> entries = new ArrayList<>();

        while (rs.next()) {
            Entry toAdd = new Entry(rs.getInt("id"), rs.getInt("vehicle_id"), rs.getInt("odometerread"),
                    rs.getTimestamp("date"), rs.getString("driver"), rs.getString("type"));
            entries.add(toAdd);
        }
        stmt.close();
        rs.close();
        conn.close();
        return entries;
    }
    
    public int latestOdometerForVehicle(Integer vehicleId) throws SQLException {
        
        Connection conn = DriverManager.getConnection(databaseUrl, "sa", "");
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Entry WHERE vehicle_id = ? "
                + "ORDER BY date DESC");
        stmt.setInt(1, vehicleId);
        
        ResultSet rs = stmt.executeQuery();
        ArrayList<Entry> entries = new ArrayList<>();
        while (rs.next()) {
            Entry toAdd = new Entry(rs.getInt("id"), rs.getInt("vehicle_id"), rs.getInt("odometerread"),
                    rs.getTimestamp("date"), rs.getString("driver"), rs.getString("type"));
            entries.add(toAdd);
        }
        //lue Entryn odometer
        int res = entries.get(0).getOdometer();
        stmt.close();
        rs.close();
        conn.close();

        return res;
    }
}
