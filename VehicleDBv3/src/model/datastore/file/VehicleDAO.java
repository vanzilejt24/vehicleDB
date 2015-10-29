package model.datastore.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import model.Vehicle;
import model.IVehicleDAO;

/**
 * @author John Phillips
 * @version 20151009
 * 
 */
public class VehicleDAO implements IVehicleDAO {

	protected String fileName = null;
	protected final List<Vehicle> myList;

	public VehicleDAO() {
		Properties props = new Properties();
		FileInputStream fis = null;

		// read the properties file
		try {
			fis = new FileInputStream("res/file/db.properties");
			props.load(fis);
			this.fileName = props.getProperty("DB_FILENAME");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.myList = new ArrayList<>();
		try {
			Files.createFile(Paths.get(fileName));
		} catch (FileAlreadyExistsException fae) {
			;
		} catch (IOException ioe) {
			System.out.println("Create file error with " + ioe.getMessage());
		}
		readList();
	}

	@Override
	public void createRecord(Vehicle vehicle) {
		myList.add(vehicle);
		writeList();
	}

	@Override
	public Vehicle retrieveRecordById(int id) {
		for (Vehicle vehicle : myList) {
			if (vehicle.getVID() == id) {
				return vehicle;
			}
		}
		return null;
	}

	@Override
	public List<Vehicle> retrieveAllRecords() {
		return myList;
	}

	@Override
	public void updateRecord(Vehicle updatedEmployee) {
		for (Vehicle vehicle : myList) {
			if (vehicle.getVID() == updatedEmployee.getVID()) {
				vehicle.setMaker(updatedEmployee.getMaker());
				vehicle.setModel(updatedEmployee.getModel());
				vehicle.setMpg(updatedEmployee.getMpg());
				vehicle.setHp(updatedEmployee.getHp());
				vehicle.setProdYear(updatedEmployee.getProdYear());
				break;
			}
		}
		writeList();
	}

	@Override
	public void deleteRecord(int id) {
		for (Vehicle vehicle : myList) {
			if (vehicle.getVID() == id) {
				myList.remove(vehicle);
				break;
			}
		}
		writeList();
	}
	
	@Override
	public void deleteAllRecords(){
		
	}
	@Override
	public void recordSort(String cat){
		
	}
	@Override
	public void deleteRecord(Vehicle vehicle) {
		myList.remove(vehicle);
		writeList();
	}

	protected void readList() {
		Path path = Paths.get(fileName);
		try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				int id = Integer.parseInt(data[0]);
				String maker = data[1];
				String model = data[2];
				String mpg = data[3];
				String hp = data[4];
				String yearProd = data[5];
				Vehicle vehicle = new Vehicle(id, maker, model, mpg, hp, yearProd);
				myList.add(vehicle);
			}
		} catch (IOException ioe) {
			System.out.println("Read file error with " + ioe.getMessage());
		}
	}

	protected void writeList() {
		Path path = Paths.get(fileName);
		try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
			for (Vehicle vehicle : myList) {
				writer.write(String.format("%d,%s,%s,%s,%s,%s\n", vehicle.getVID(), vehicle.getMaker(),
						vehicle.getModel(), vehicle.getMpg(), vehicle.getHp(), vehicle.getProdYear()));
			}
		} catch (IOException ioe) {
			System.out.println("Write file error with " + ioe.getMessage());
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (Vehicle vehicle : myList) {
			sb.append(vehicle.toString() + "\n");
		}

		return sb.toString();
	}
}
