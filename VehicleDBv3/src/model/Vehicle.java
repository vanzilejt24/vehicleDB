package model;

/**
 * The Vehicle class represents a single employee.
 * 
 * @author John Phillips
 * @version 20151015
 *
 */
public class Vehicle {

	private int vID;
	private String maker;
	private String model;
	private String mpg;
	private String hp;
	private String prodYear;

	public Vehicle() {
		vID = 0;
		maker = "";
		model = "";
		mpg = "";
		hp = "";
		prodYear = "";
	}

	public Vehicle(int vID, String maker, String model, String mpg, String hp, String prodYear) {
		this.vID = vID;
		this.maker = maker;
		this.model = model;
		this.mpg = mpg;
		this.hp = hp;
		this.prodYear = prodYear;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public int getVID() {
		return vID;
	}

	public void setVID(int vID) {
		this.vID = vID;
	}

	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}

	public String getMpg() {
		return mpg;
	}

	public void setMpg(String mpg) {
		this.mpg = mpg;
	}
	
	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getProdYear() {
		return prodYear;
	}

	public void setProdYear(String prodYear) {
		this.prodYear = prodYear;
	}

	@Override
	public String toString() {
		return String.format("%5d : MAKE: %s, MODEL: %s, HP: %s, MPG: %s, PRODUCTION YEAR: %s", this.getVID(), this.getMaker(),
				this.getModel(), this.getHp(), this.getMpg(), this.getProdYear());
	}
}