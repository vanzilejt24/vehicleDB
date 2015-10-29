package viewui;

import java.util.Scanner;

import model.Vehicle;
import model.IVehicleDAO;
import model.datastore.mysql.VehicleDAO;

/**
 * @author John Phillips
 * @version 20151015
 * 
 */
public class VehicleApp {

	IVehicleDAO vehList = new VehicleDAO();
	Scanner sc = new Scanner(System.in);

	public VehicleApp() {
		menuLoop();
	}

	private void menuLoop() {
		int id;
		String maker, model, mpg, hp, prodYear;
		String choice = "1";
		while (!choice.equals("0")) {
			System.out.println("\nEmployee App");
			System.out.println("0 = Quit");
			System.out.println("1 = List All Vehicle Records");
			System.out.println("2 = Create New Vehicle Record");
			System.out.println("3 = Retrieve Vehicle Record");
			System.out.println("4 = Update Vehicle Record");
			System.out.println("5 = Delete Vehicle Record");
			System.out.println("6 = Delete All Records In Vehicle Table");
			System.out.println("7 = Sort records");
			choice = Validator.getLine(sc, "Number of choice: ", "^[0-7]$");

			switch (choice) {
			case "1":
				System.out.println(vehList.toString());
				break;
			case "2":
				id = Validator.getInt(sc, "New Vehicle ID: ");
				maker = Validator.getLine(sc, "Maker: ");
				model = Validator.getLine(sc, "Model: ");
				mpg = Validator.getLine(sc, "Miles Per Gallon: ");
				hp = Validator.getLine(sc, "Horse Power: ");
				prodYear = Validator.getLine(sc, "Production Year: ");
				vehList.createRecord(new Vehicle(id, maker, model, mpg, hp, prodYear));
				break;
			case "3":
				id = Validator.getInt(sc, "Vehicle ID to retrieve: ");
				System.out.println(vehList.retrieveRecordById(id));
				break;
			case "4":
				id = Validator.getInt(sc, "Vehicle ID to update: ");
				maker = Validator.getLine(sc, "Maker: ");
				model = Validator.getLine(sc, "Model: ");
				mpg = Validator.getLine(sc, "MPG: ");
				hp = Validator.getLine(sc, "HP: ");
				prodYear = Validator.getLine(sc, "Production Year: ");
				vehList.updateRecord(new Vehicle(id, maker, model, mpg, hp, prodYear));
				break;
			case "5":
				id = Validator.getInt(sc, "Vehicle ID to delete: ");
				System.out.println(vehList.retrieveRecordById(id));
				String ok = Validator.getLine(sc, "Delete this record? (y/n) ", "^[yYnN]$");
				if (ok.equalsIgnoreCase("Y")) {
					vehList.deleteRecord(id);
				}
				break;
			case "6":
				String affirm = Validator.getLine(sc, "Delete all records from vehicle table? (y/n): ", "^[yYnN]$");
				if (affirm.equalsIgnoreCase("Y")) {
					String affirm2 = Validator.getLine(sc, "This is permanent, are you sure? (y/n): ", "^[yYnN]$");
						if (affirm2.equalsIgnoreCase("Y")) {
							vehList.deleteAllRecords();}}
				break;
			case "7":
				String sortCat = Validator.getLine(sc, "Which category would you like to sort by? ID / make / model / mpg / hp / prodYear?: ");
				if ((sortCat.contentEquals("ID"))||(sortCat.contentEquals("make"))||(sortCat.contentEquals("model"))||(sortCat.contentEquals("mpg"))||(sortCat.contentEquals("hp"))||(sortCat.contentEquals("prodYear"))){
					vehList.recordSort(sortCat);
					System.out.println(vehList.toString());
					}
				
				break;
				}
			}
		}
	

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		new VehicleApp();
	}
}
