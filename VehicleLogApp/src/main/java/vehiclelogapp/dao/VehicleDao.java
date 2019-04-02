
package vehiclelogapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import vehiclelogapp.domain.Vehicle;


public class VehicleDao implements Dao<Vehicle, Integer> {

    @Override
    public Vehicle create(Vehicle vehicle) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./logbook", "sa", "");
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Vehicle (plate, odometer) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, vehicle.getLicensePlate());
        stmt.setInt(2, vehicle.getKilometers());
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
    public Vehicle read(Integer key) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./logbook", "sa", "");
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

    @Override
    public Vehicle update(Vehicle vehicle) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Vehicle> list() throws SQLException {
        
        Connection conn = DriverManager.getConnection("jdbc:h2:./logbook", "sa", "");
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
    
    public Integer getVehicleId(String licensePlate) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./logbook", "sa", "");
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