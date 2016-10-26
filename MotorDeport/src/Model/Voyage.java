package Model;

public class Voyage extends Entity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1147105724406501074L;
	String startDate;
	int duration;
	int statement;
	int employee_id;
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getStatement() {
		return statement;
	}
	public void setStatement(int statement) {
		this.statement = statement;
	}

	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	@Override 
	public String toString(){
		return "id: "+ getId() + ", startDate: " + startDate;
	}
}
