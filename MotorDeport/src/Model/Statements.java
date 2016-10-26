package Model;

public class Statements extends Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1239029771984673866L;
	String statement;

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}
	@Override
	public String toString(){
		return statement;
	}
}
