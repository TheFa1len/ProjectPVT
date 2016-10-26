package DAO.Interface.Imp;

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
import DAO.Interface.AplicationDAO;
import Model.Aplication;

public class AplicationImp implements AplicationDAO{
	public static final String SQL_SELECT_ALL_APLICATION = "SELECT * FROM aplications";
	public static final String SQL_SELECT_ALL_FREE = "SELECT * FROM aplications WHERE voyage_id is NULL";
	public static final String SQL_FIND_BY_ID = "SELECT * FROM aplications WHERE aplication_id = ?";
	public static final String SQL_UPDATE = "update aplications set voyage_id = ? where aplication_id = ?";
	public static final String SQL_DELETE = "DELETE FROM aplications WHERE aplication_id = ?";
	public static final String SQL_CREATE = "INSERT INTO aplications VALUES (?,?,?,?)";
	
	public void close(Statement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
		}
	}

	public void close(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
		}
	}
	@Override
	public void add(Aplication t) {
		Connection connection = null;
        try {
            try {
				connection = DataSource.getInstance().getConnection();
			} catch (IOException | PropertyVetoException e) {
				e.printStackTrace();
			}
            PreparedStatement statement = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, t.getId());
            statement.setInt(2, t.getVoyage_id());
            statement.setInt(3, t.getNumberOfPassengers());
            statement.setInt(4, t.getCargo());
            statement.execute();
             
            ResultSet generatedkeys = statement.getGeneratedKeys();
            if (generatedkeys.next()) {
                t.setId(generatedkeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
	}

	@Override
	public void update(Aplication t) {
		Connection connection = null;
        try {
            try {
				connection = DataSource.getInstance().getConnection();
			} catch (IOException | PropertyVetoException e) {
				e.printStackTrace();
			}
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);
            statement.setInt(2,t.getId());
            statement.setInt(1,t.getVoyage_id());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
		
	}


	@Override
	public Aplication get(int id) {
		Aplication item = new Aplication();
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
				item.setId(resultSet.getInt("aplication_id"));
				item.setNumberOfPassengers(resultSet.getInt("number_of_passengers"));
				item.setCargo(resultSet.getInt("cargo_weight"));
				item.setVoyage_id(resultSet.getInt("voyage_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection);
		}
		return item;
	}

	@Override
	public void delete(Aplication t) {
		Connection connection = null;
		try {
			try {
				connection = DataSource.getInstance().getConnection();
			} catch (IOException | PropertyVetoException e) {
				e.printStackTrace();
			}
			PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
			statement.setInt(1, t.getId());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection);
		}
	}

	@Override
	public List<Aplication> findAll() {
		List<Aplication> posts = new ArrayList<Aplication>();
		Connection cn = null;
		Statement st = null;
		try {
			try {
				cn = DataSource.getInstance().getConnection();
			} catch (IOException | PropertyVetoException e) {
				e.printStackTrace();
			}
			st=cn.createStatement();
			ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL_APLICATION);
			while(resultSet.next()){
				Aplication temp = new Aplication();
				temp.setId(resultSet.getInt("aplication_id"));
				temp.setCargo(resultSet.getInt("cargo_weight"));
				temp.setNumberOfPassengers(resultSet.getInt("number_of_passengers"));
				temp.setVoyage_id(resultSet.getInt("voyage_id"));
				posts.add(temp);
				
			}
		}catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			close(st);
		}
		return posts;
	}

	@Override
	public List<Aplication> findFree() {
		List<Aplication> posts = new ArrayList<Aplication>();
		Connection cn = null;
		Statement st = null;
		try {
			try {
				cn = DataSource.getInstance().getConnection();
			} catch (IOException | PropertyVetoException e) {
				e.printStackTrace();
			}
			st=cn.createStatement();
			ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL_FREE);
			while(resultSet.next()){
				Aplication temp = new Aplication();
				temp.setId(resultSet.getInt("aplication_id"));
				temp.setCargo(resultSet.getInt("cargo_weight"));
				temp.setNumberOfPassengers(resultSet.getInt("number_of_passengers"));
				temp.setVoyage_id(resultSet.getInt("voyage_id"));
				posts.add(temp);
				
			}
		}catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			close(st);
		}
		return posts;

}
}
