package DAO;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import Connection.DataSource;
import Model.Carpark;

public class CarparkDAO extends AbstractDAO<Integer, Carpark> {
	public static final String SQL_SELECT_ALL = "SELECT * FROM carpark";
	public static final String SQL_FIND_BY_ID = "SELECT * FROM carpark WHERE auto_id = ?";
	public static final String SQL_DELETE = "DELETE FROM carpark WHERE auto_id = ?";
	public static final String SQL_CREATE = "INSERT INTO carpark VALUES (?,?,?,?,?)";
	public static final String SQL_UPDATE = "update carpark set state_id = ? where auto_id = ?";

	@Override
	public List<Carpark> findAll() {
		List<Carpark> carpark = new ArrayList<>();
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
				Carpark car = new Carpark();
				car.setId(resultSet.getInt("auto_id"));
				car.setLabel(resultSet.getString("label"));
				car.setPassengers(resultSet.getInt("passangers"));
				car.setCargo(resultSet.getInt("cargo"));
				car.setStatement_id(resultSet.getInt("state_id"));
				carpark.add(car);
			}
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			close(st);
		}
		return carpark;
	}

	@Override
	public Carpark findEntityById(Integer id) {
		Carpark item = new Carpark();
		Connection connection = null;
		try {
			try {
				connection = DataSource.getInstance().getConnection();
			} catch (IOException | PropertyVetoException e) {
				e.printStackTrace();
			}
			PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				item.setId(resultSet.getInt("auto_id"));
				item.setLabel(resultSet.getString("label"));
				item.setPassengers(resultSet.getInt("passangers"));
				item.setCargo(resultSet.getInt("cargo"));
				item.setStatement_id(resultSet.getInt("state_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection);
		}
		return item;
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
			PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
			statement.setLong(1, id);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection);
		}
	}

	@Override
	public boolean create(Carpark entity) {
		Connection connection = null;
        try {
            try {
				connection = DataSource.getInstance().getConnection();
			} catch (IOException | PropertyVetoException e) {
				e.printStackTrace();
			}
            PreparedStatement statement = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(2, entity.getLabel());
            statement.setInt(1, entity.getId());
            statement.setInt(3, entity.getPassengers());
            statement.setInt(4, entity.getCargo());
            statement.setInt(5, entity.getStatement_id());
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
	public void update(Carpark entity) {
		Connection connection = null;
        try {
            try {
				connection = DataSource.getInstance().getConnection();
			} catch (IOException | PropertyVetoException e) {
				e.printStackTrace();
			}
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);
            statement.setInt(2,entity.getId());
            statement.setInt(1,entity.getStatement_id());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
	}

	@Override
	public void delete(Carpark entity) {
		Connection connection = null;
		try {
			try {
				connection = DataSource.getInstance().getConnection();
			} catch (IOException | PropertyVetoException e) {
				e.printStackTrace();
			}
			PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
			statement.setInt(1, entity.getId());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection);
		}
	}

}
