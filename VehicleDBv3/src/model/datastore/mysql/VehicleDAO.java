package model.datastore.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Vehicle;
import model.IVehicleDAO;

/**
 * @author John Phillips
 * @version 20151015
 *
 */
public class VehicleDAO implements IVehicleDAO {
	
	protected final static boolean DEBUG = true;

	@Override
	public void createRecord(Vehicle vehicle) {
		final String QUERY = "insert into vehicle (vID, maker, model, mpg, hp, prodYear) VALUES (null, ?, ?, ?, ?, ?)";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY);) {
			stmt.setString(1, vehicle.getMaker());
			stmt.setString(2, vehicle.getModel());
			stmt.setString(3, vehicle.getMpg());
			stmt.setString(4, vehicle.getHp());
			stmt.setString(5, vehicle.getProdYear());
			if(DEBUG) System.out.println(stmt.toString());
			stmt.executeUpdate();

		} catch (SQLException ex) {
			System.out.println("createRecord SQLException: " + ex.getMessage());
		}
	}

	@Override
	public Vehicle retrieveRecordById(int id) {
		final String QUERY = "select vID, maker, model, mpg, hp, prodYear from vehicle where vID = " + id;
		// final String QUERY = "select empId, lastName, firstName, homePhone,
		// salary from vehicle where empId = ?";
		Vehicle emp = null;

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			// stmt.setInt(1, id);
			if(DEBUG) System.out.println(stmt.toString());
			ResultSet rs = stmt.executeQuery(QUERY);

			if (rs.next()) {
				emp = new Vehicle(rs.getInt("vID"), rs.getString("maker"), rs.getString("model"),
						rs.getString("mpg"), rs.getString("hp"), rs.getString("prodYear"));
			}
		} catch (SQLException ex) {
			System.out.println("retrieveRecordById SQLException: " + ex.getMessage());
		}

		return emp;
	}

	@Override
	public List<Vehicle> retrieveAllRecords() {
		final List<Vehicle> myList = new ArrayList<>();
		final String QUERY = "select vID, maker, model, mpg, hp, prodYear from vehicle";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			if(DEBUG) System.out.println(stmt.toString());
			ResultSet rs = stmt.executeQuery(QUERY);
			
			while (rs.next()) {
				myList.add(new Vehicle(rs.getInt("vID"), rs.getString("maker"), rs.getString("model"),
						rs.getString("mpg"), rs.getString("hp"), rs.getString("prodYear")));
			}
		} catch (SQLException ex) {
			System.out.println("retrieveAllRecords SQLException: " + ex.getMessage());
		}

		return myList;
	}

	@Override
	public void updateRecord(Vehicle updatedEmployee) {
		final String QUERY = "update vehicle set maker=?, model=?, mpg=?, hp=?, prodYear=? where vID=?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			stmt.setString(1, updatedEmployee.getMaker());
			stmt.setString(2, updatedEmployee.getModel());
			stmt.setString(3, updatedEmployee.getMpg());
			stmt.setString(4, updatedEmployee.getHp());
			stmt.setString(5, updatedEmployee.getProdYear());
			stmt.setInt(6, updatedEmployee.getVID());
			if(DEBUG) System.out.println(stmt.toString());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("updateRecord SQLException: " + ex.getMessage());
		}
	}

	@Override
	public void deleteRecord(int id) {
		final String QUERY = "delete from vehicle where vID = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			stmt.setInt(1, id);
			if(DEBUG) System.out.println(stmt.toString());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("deleteRecord SQLException: " + ex.getMessage());
		}
	}
	
	@Override
	public void deleteAllRecords(){
		final String QUERY = "truncate vehicle";
		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)){
			if(DEBUG) System.out.println(stmt.toString());
			stmt.executeUpdate();
		} catch (SQLException ex){
			System.out.println("deleteAllRecords SQLException: " + ex.getMessage());
		}
	}

	@Override
	public void deleteRecord(Vehicle vehicle) {
		final String QUERY = "delete from vehicle where vID = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			stmt.setInt(1, vehicle.getVID());
			if(DEBUG) System.out.println(stmt.toString());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("deleteRecord SQLException: " + ex.getMessage());
		}
	}

	@Override
	public void recordSort(String cat) {
		final String QUERY = "select vID, maker, model, mpg, hp, prodYear from vehicle order by " +cat;
	try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)){
			if(DEBUG) System.out.println(stmt.toString());
			//stmt.executeUpdate();
			
		} catch (SQLException ex){
			System.out.println("recordSort SQLException: " + ex.getMessage());
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (Vehicle vehicle : retrieveAllRecords()) {
			sb.append(vehicle.toString() + "\n");
		}

		return sb.toString();
	}
}
