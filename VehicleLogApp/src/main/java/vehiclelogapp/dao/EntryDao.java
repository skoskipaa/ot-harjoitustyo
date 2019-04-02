package vehiclelogapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import vehiclelogapp.domain.Entry;
import vehiclelogapp.domain.Vehicle;

public class EntryDao implements Dao<Entry, Integer> {

    @Override
    public Entry create(Entry entry) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:h2:./logbook", "sa", "");
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Entry (vehicle_id, date, odometerread, driver, type)"
                + " VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, entry.getVehicle_id());
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

        Connection conn = DriverManager.getConnection("jdbc:h2:./logbook", "sa", "");
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
    public Entry update(Entry object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Entry> list() throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:h2:./logbook", "sa", "");
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

    public ArrayList<Entry> listEntriesForVehicle(Integer vehicle_id) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:h2:./logbook", "sa", "");
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Entry WHERE vehicle_id = ?");
        stmt.setInt(1, vehicle_id);
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
}
