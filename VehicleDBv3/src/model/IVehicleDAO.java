package model;

import java.util.List;

/**
 * @author John Phillips
 * @version 20151009
 *
 */
public interface IVehicleDAO {

	void createRecord(Vehicle vehicle);

	Vehicle retrieveRecordById(int id);

	List<Vehicle> retrieveAllRecords();
	
	void recordSort(String cat);

	void updateRecord(Vehicle updatedEmployee);

	void deleteRecord(int id);

	void deleteRecord(Vehicle vehicle);
	
	void deleteAllRecords();

	String toString();

}