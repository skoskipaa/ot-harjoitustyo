package vehiclelogapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import vehiclelogapp.domain.Vehicle;

/**
 * Luokka hoitaa ajoneuvojen (Vehicle) käsittelyyn liittyvät tietokantatoiminnot
 *
 */
public class VehicleDao implements Dao<Vehicle, Integer> {

    private DaoService service;
    private String databaseUrl;
    private String user;
    private String password;

    public VehicleDao(String databaseUrl, String user, String password) {

        this.service = new DaoService();
        this.databaseUrl = databaseUrl;
        this.user = user;
        this.password = password;

    }

    /**
     * Luo uuden ajoneuvon tietokantaan
     *
     * @param vehicle Ajoneuvoa kuvaava Vehicle-olio
     * @return Palauttaa luodun Vehicle-olion, käyttää apuna read()-metodia
     * @see vehiclelogapp.dao.VehicleDao#read(java.lang.Integer)
     * @throws SQLException poikkeus, mikäli epäonnistuu
     */
    @Override
    public Vehicle create(Vehicle vehicle) throws SQLException {
        Connection conn = service.getDbConnection(databaseUrl, user, password);
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Vehicle (plate, odometer) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, vehicle.getLicensePlate());
        stmt.setInt(2, vehicle.getKilometers());
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

    /**
     * Lukee tietokannasta ajoneuvon tiedot
     *
     * @param key avain (id)
     * @return Palauttaa Vehicle-olion
     * @throws SQLException poikkeus, mikäli epäonnistuu
     */
    @Override
    public Vehicle read(Integer key) throws SQLException {
        Connection conn = service.getDbConnection(databaseUrl, user, password);
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Vehicle WHERE id = ?");
        stmt.setInt(1, key);
        ResultSet rs = stmt.executeQuery();

        if (!rs.next()) {
            return null;
        }
        Vehicle returnVehicle = new Vehicle(rs.getInt("id"), rs.getString("plate"), rs.getInt("odometer"));
        stmt.close();
        rs.close();
        conn.close();
        return returnVehicle;

    }

    /**
     * Listaa kaikki tietokannan ajoneuvot
     *
     * @return Listan Vehicle-olioita
     * @throws SQLException poikkeus, mikäli epäonnistuu
     */
    @Override
    public ArrayList<Vehicle> list() throws SQLException {

        Connection conn = service.getDbConnection(databaseUrl, user, password);
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Vehicle");
        ResultSet rs = stmt.executeQuery();

        ArrayList<Vehicle> vehicles = new ArrayList<>();

        while (rs.next()) {
            Vehicle toAdd = new Vehicle(rs.getInt("id"), rs.getString("plate"), rs.getInt("odometer"));
            vehicles.add(toAdd);
        }
        rs.close();
        stmt.close();
        conn.close();

        return vehicles;
    }

    /**
     * Hakee ajoneuvon id:n tietokannasta
     *
     * @param licensePlate rekisteritunnus
     * @return Ajoneuvon id Integer-muodossa
     * @throws SQLException heittää SQL-poikkeuksen, mikäli epäonnistuu
     */
    public Integer getVehicleId(String licensePlate) throws SQLException {
        Connection conn = service.getDbConnection(databaseUrl, user, password);
        PreparedStatement stmt = conn.prepareStatement("SELECT id FROM Vehicle WHERE plate = ?");
        stmt.setString(1, licensePlate);
        ResultSet rs = stmt.executeQuery();

        if (!rs.next()) {
            return null;
        }

        Integer result = rs.getInt("id");
        stmt.close();
        rs.close();
        conn.close();

        return result;

    }

}
