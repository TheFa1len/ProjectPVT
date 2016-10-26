package Model;

public class Aplication extends Entity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5461519969587813058L;
	int voyage_id;
	int numberOfPassengers;
	int cargo;

	public int getNumberOfPassengers() {
		return numberOfPassengers;
	}
	public void setNumberOfPassengers(int numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}
	public int getCargo() {
		return cargo;
	}
	public int getVoyage_id() {
		return voyage_id;
	}
	public void setVoyage_id(int voyage_id) {
		this.voyage_id = voyage_id;
	}
	public void setCargo(int cargo) {
		this.cargo = cargo;
	}
	@Override
	public String toString(){
		return "id: " + getId() + ", passengers reuqirement: " + numberOfPassengers + ", cargo requirement: " + cargo;
	}
}
