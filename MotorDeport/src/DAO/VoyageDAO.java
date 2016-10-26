package DAO;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Connection.DataSource;
import Model.Voyage;

public class VoyageDAO extends AbstractDAO<Integer, Voyage> {
	public static final String SQL_SELECT_ALL = "SELECT * FROM voyages";
	public static final String SQL_FIND_BY_ID = "SELECT * FROM voyages WHERE voyage_id = ?";
	public static final String SQL_FIND_BY_DRIVER = "SELECT * FROM voyages WHERE employee_id = ?";
	public static final String SQL_FIND_NON_BY_DRIVER = "SELECT * FROM voyages WHERE employee_id = ? AND statement = 0";
	public static final String SQL_FIND_BY_VOYAGE = "SELECT * FROM voyages WHERE employee_id = ?";
	public static final String SQL_DELETE = "DELETE FROM voyages WHERE voyage_id = ?";
	public static final String SQL_CREATE = "INSERT INTO voyages VALUES (?,?,?,?,?)";
	public static final String SQL_UPDATE_STATE = "update voyages set statement = ? where voyage_id = ?";
	public static final String SQL_UPDATE_DRIVER = "update voyages set employee_id = ? where voyage_id = ?";

	@Override
	public List<Voyage> findAll() {
		List<Voyage> voyage = new ArrayList<>();
		Connection cn = null;
		Statement st = null;
		try {
			try {
				cn = DataSource.getInstance().getConnection();
			} catch (IOException | PropertyVetoException e) {
				e.printStackTrace();
			}
			st = cn.createStatement();
			ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL);
			while (resultSet.next()) {
				Voyage item = new Voyage();
				item.setId(resultSet.getInt("voyage_id"));
				item.setEmployee_id(resultSet.getInt("employee_id"));
				item.setDuration(resultSet.getInt("duration"));
				item.setStatement(resultSet.getInt("statement"));
				item.setStartDate(resultSet.getString("start_date"));
				voyage.add(item);
			}
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			close(st);
		}
		return voyage;
	}

	@Override
	public Voyage findEntityById(Integer id) {
		Voyage item = new Voyage();
		Connection connection = null;
		try {
			try {
				connection = DataSource.getInstance().getConnection();
			} catch (IOException | PropertyVetoException e) {
				e.printStackTrace();
			}
			PreparedStatement statement = connection
					.prepareStatement(SQL_FIND_BY_ID);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				item.setId(resultSet.getInt("voyage_id"));
				item.setDuration(resultSet.getInt("duration"));
				item.setEmployee_id(resultSet.getInt("employee_id"));
				item.setStartDate(resultSet.getString("start_date"));
				item.setStatement(resultSet.getInt("statement"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection);
		}
		return item;
	}
	public List<Voyage> findAllByEmp(Integer id) {
		List<Voyage> voyage = new ArrayList<>();
		Voyage item = new Voyage();
		Connection connection = null;
		try {
			try {
				connection = DataSource.getInstance().getConnection();
			} catch (IOException | PropertyVetoException e) {
				e.printStackTrace();
			}
			PreparedStatement statement = connection
					.prepareStatement(SQL_FIND_BY_DRIVER);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				item.setId(resultSet.getInt("voyage_id"));
				item.setDuration(resultSet.getInt("duration"));
				item.setEmployee_id(resultSet.getInt("employee_id"));
				item.setStartDate(resultSet.getString("start_date"));
				item.setStatement(resultSet.getInt("statement"));
				voyage.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection);
		}
		return voyage;
	}
	public List<Voyage> findAllNonByEmp(Integer id) {
		List<Voyage> voyage = new ArrayList<>();
		Voyage item = new Voyage();
		Connection connection = null;
		try {
			try {
				connection = DataSource.getInstance().getConnection();
			} catch (IOException | PropertyVetoException e) {
				e.printStackTrace();
			}
			PreparedStatement statement = connection
					.prepareStatement(SQL_FIND_NON_BY_DRIVER);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				item.setId(resultSet.getInt("voyage_id"));
				item.setDuration(resultSet.getInt("duration"));
				item.setEmployee_id(resultSet.getInt("employee_id"));
				item.setStartDate(resultSet.getString("start_date"));
				item.setStatement(resultSet.getInt("statement"));
				voyage.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection);
		}
		return voyage;
	}

	public List<Voyage> findAllDrivers() {
		List<Voyage> voyage = new ArrayList<>();
		Connection cn = null;
		Statement st = null;
		try {
			try {
				cn = DataSource.getInstance().getConnection();
			} catch (IOException | PropertyVetoException e) {
				e.printStackTrace();
			}
			st = cn.createStatement();
			ResultSet resultSet = st.executeQuery(SQL_FIND_BY_VOYAGE);
			while (resultSet.next()) {
				Voyage item = new Voyage();
				item.setId(resultSet.getInt("voyage_id"));
				item.setEmployee_id(resultSet.getInt("employee_id"));
				item.setDuration(resultSet.getInt("duration"));
				item.setStatement(resultSet.getInt("statement"));
				item.setStartDate(resultSet.getString("start_date"));
				voyage.add(item);
			}
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			close(st);
		}
		return voyage;
	}

	@Override
	public void delete(Integer id) {
		Connection connection = null;
		try {
			try {
				connection = DataSource.getInstance().getConnection();
			} catch (IOException | PropertyVetoException e) {
				e.printStackTrace();
			}
			PreparedStatement statement = connection
					.prepareStatement(SQL_DELETE);
			statement.setLong(1, id);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection);
		}
	}

	@Override
	public void delete(Voyage entity) {
		Connection connection = null;
		try {
			try {
				connection = DataSource.getInstance().getConnection();
			} catch (IOException | PropertyVetoException e) {
				e.printStackTrace();
			}
			PreparedStatement statement = connection
					.prepareStatement(SQL_DELETE);
			statement.setInt(1, entity.getId());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection);
		}
	}

	@Override
	public boolean create(Voyage entity) {
		Connection connection = null;
		try {
			try {
				connection = DataSource.getInstance().getConnection();
			} catch (IOException | PropertyVetoException e) {
				e.printStackTrace();
			}
			PreparedStatement statement = connection.prepareStatement(
					SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, entity.getId());
			statement.setString(2, entity.getStartDate());
			statement.setInt(3, entity.getDuration());
			statement.setInt(4, entity.getStatement());
			statement.setInt(5, entity.getEmployee_id());
			statement.execute();

			ResultSet generatedkeys = statement.getGeneratedKeys();
			if (generatedkeys.next()) {
				entity.setId(generatedkeys.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection);
		}
		return true;
	}

	@Override
	public void update(Voyage entity) {
		Connection connection = null;
		try {
			try {
				connection = DataSource.getInstance().getConnection();
			} catch (IOException | PropertyVetoException e) {
				e.printStackTrace();
			}
			PreparedStatement statement = connection
					.prepareStatement(SQL_UPDATE_STATE);
			statement.setInt(2, entity.getId());
			statement.setInt(1, entity.getStatement());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection);
		}
	}

	public void update_driver(Voyage entity) {
		Connection connection = null;
		try {
			try {
				connection = DataSource.getInstance().getConnection();
			} catch (IOException | PropertyVetoException e) {
				e.printStackTrace();
			}
			PreparedStatement statement = connection
					.prepareStatement(SQL_UPDATE_DRIVER);
			statement.setInt(2, entity.getId());
			statement.setInt(1, entity.getEmployee_id());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection);
		}
	}

}

