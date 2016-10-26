package Model;

public class Carpark extends Entity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6349365574449075286L;
	String label;
	int passengers;
	int cargo;
	int statement_id;
	public int getStatement_id() {
		return statement_id;
	}
	public void setStatement_id(int statement_id) {
		this.statement_id = statement_id;
	}

	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getPassengers() {
		return passengers;
	}
	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}
	public int getCargo() {
		return cargo;
	}
	public void setCargo(int cargo) {
		this.cargo = cargo;
	}

	@Override
	public String toString(){
		return label + " ,passengers limit: " + passengers + ", cargo limit: " + cargo;
	}
}
