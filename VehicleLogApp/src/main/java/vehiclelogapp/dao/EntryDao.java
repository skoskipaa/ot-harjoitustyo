package vehiclelogapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import vehiclelogapp.domain.Entry;

/**
 * Luokka hoitaa tapahtumien (Entry) käsittelyyn liittyvät tietokantatoiminnot
 *
 */
public class EntryDao implements Dao<Entry, Integer> {

    private DaoService service;
    private String databaseUrl;
    private String user;
    private String password;

    public EntryDao(String databaseUrl, String user, String password) {
        this.service = new DaoService();
        this.databaseUrl = databaseUrl;
        this.user = user;
        this.password = password;
    }

    @Override
    public Entry create(Entry entry) throws SQLException {
        Connection conn = service.getDbConnection(databaseUrl, user, password);
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Entry (vehicle_id, date, odometerread, driver, type, last_trip)"
                + " VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, entry.getVehicleId());
        stmt.setTimestamp(2, entry.getTime());
        stmt.setInt(3, entry.getOdometer());
        stmt.setString(4, entry.getDriver());
        stmt.setString(5, entry.getEntryType());
        stmt.setInt(6, entry.getLastTrip());
        stmt.executeUpdate();
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

        Connection conn = service.getDbConnection(databaseUrl, user, password);
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Entry WHERE id = ?");
        stmt.setInt(1, key);
        ResultSet rs = stmt.executeQuery();

        if (!rs.next()) {
            return null;
        }
        Entry returnEntry = new Entry(rs.getInt("id"), rs.getInt("vehicle_id"), rs.getInt("odometerread"),
                rs.getTimestamp("date"), rs.getString("driver"), rs.getString("type"), rs.getInt("last_trip"));

        stmt.close();
        rs.close();
        conn.close();
        return returnEntry;
    }

    @Override
    public ArrayList<Entry> list() throws SQLException {
        Connection conn = service.getDbConnection(databaseUrl, user, password);
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Entry");
        ResultSet rs = stmt.executeQuery();
        ArrayList<Entry> entries = new ArrayList<>();
        while (rs.next()) {
            Entry toAdd = new Entry(rs.getInt("id"), rs.getInt("vehicle_id"), rs.getInt("odometerread"),
                    rs.getTimestamp("date"), rs.getString("driver"), rs.getString("type"), rs.getInt("last_trip"));
            entries.add(toAdd);
        }
        stmt.close();
        rs.close();
        conn.close();
        return entries;
    }

    public ArrayList<Entry> listEntriesForVehicle(Integer vehicleId) throws SQLException {
        Connection conn = service.getDbConnection(databaseUrl, user, password);

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Entry WHERE vehicle_id = ?");
        stmt.setInt(1, vehicleId);
        ResultSet rs = stmt.executeQuery();

        ArrayList<Entry> entries = new ArrayList<>();

        while (rs.next()) {
            Entry toAdd = new Entry(rs.getInt("id"), rs.getInt("vehicle_id"), rs.getInt("odometerread"),
                    rs.getTimestamp("date"), rs.getString("driver"), rs.getString("type"), rs.getInt("last_trip"));
            entries.add(toAdd);
        }
        stmt.close();
        rs.close();
        conn.close();
        return entries;
    }

    /**
     * Hakee ajoneuvon viimeisimmän matkamittarin lukeman
     *
     * @param vehicleId Ajoneuvon id tietokannassa
     * @return Lukeman int-muodossa
     * @throws SQLException Heittää SQL-poikkeuksen, mikäli epäonnistuu
     */
    public int latestOdometerForVehicle(Integer vehicleId) throws SQLException {
        Connection conn = service.getDbConnection(databaseUrl, user, password);
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Entry WHERE vehicle_id = ? "
                + "ORDER BY date DESC");
        stmt.setInt(1, vehicleId);
        ResultSet rs = stmt.executeQuery();
        ArrayList<Entry> entries = new ArrayList<>();
        while (rs.next()) {
            Entry toAdd = new Entry(rs.getInt("id"), rs.getInt("vehicle_id"), rs.getInt("odometerread"),
                    rs.getTimestamp("date"), rs.getString("driver"), rs.getString("type"), rs.getInt("last_trip"));
            entries.add(toAdd);
        }
        int res = 0;
        if (!entries.isEmpty()) {
            res = entries.get(0).getOdometer();
        }
        stmt.close();
        rs.close();
        conn.close();
        return res;
    }

    /**
     * Hakee tapahtumia tapahtumien tapahtumatyyppikenttään tehtävän sanahaun
     * perusteella.
     *
     * @param type Hakusana
     * @return Palauttaa kaikki tapahtumat (Entry), joissa haettu merkkijono
     * @throws SQLException Heittää SQL-poikkeuksen, mikäli epäonnistuu
     */
    public ArrayList<Entry> listEntriesByType(String type) throws SQLException {
        Connection conn = service.getDbConnection(databaseUrl, user, password);
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Entry WHERE type LIKE ?");
        stmt.setString(1, "%" + type + "%");
        ResultSet rs = stmt.executeQuery();

        ArrayList<Entry> entries = new ArrayList<>();

        while (rs.next()) {
            Entry toAdd = new Entry(rs.getInt("id"), rs.getInt("vehicle_id"), rs.getInt("odometerread"),
                    rs.getTimestamp("date"), rs.getString("driver"), rs.getString("type"), rs.getInt("last_trip"));
            entries.add(toAdd);
        }
        stmt.close();
        rs.close();
        conn.close();
        return entries;
    }
}
