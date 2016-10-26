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
import Model.Aplication;

public class AplicationDAO extends AbstractDAO<Integer, Aplication>{
	public static final String SQL_SELECT_ALL_APLICATION = "SELECT * FROM aplications";
	public static final String SQL_SELECT_ALL_FREE = "SELECT * FROM aplications WHERE voyage_id is NULL";
	public static final String SQL_FIND_BY_ID = "SELECT * FROM aplications WHERE aplication_id = ?";
	public static final String SQL_UPDATE = "update aplications set voyage_id = ? where aplication_id = ?";
	public static final String SQL_DELETE = "DELETE FROM aplications WHERE aplication_id = ?";
	public static final String SQL_CREATE = "INSERT INTO aplications VALUES (?,?,?,?)";
	
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
				Aplication post = new Aplication();
				post.setId(resultSet.getInt("aplication_id"));
				post.setCargo(resultSet.getInt("cargo_weight"));
				post.setNumberOfPassengers(resultSet.getInt("number_of_passengers"));
				post.setVoyage_id(resultSet.getInt("voyage_id"));
				posts.add(post);
				
			}
		}catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			close(st);
		}
		return posts;
	}
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
				Aplication post = new Aplication();
				post.setId(resultSet.getInt("aplication_id"));
				post.setCargo(resultSet.getInt("cargo_weight"));
				post.setNumberOfPassengers(resultSet.getInt("number_of_passengers"));
				post.setVoyage_id(resultSet.getInt("voyage_id"));
				posts.add(post);
				
			}
		}catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			close(st);
		}
		return posts;
	}

	@Override
	public Aplication findEntityById(Integer id) {
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
	public void delete(Integer id) {
		Connection connection = null;
		try {
			try {
				connection = DataSource.getInstance().getConnection();
			} catch (IOException | PropertyVetoException e) {
				e.printStackTrace();
			}
			PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
			statement.setInt(1, id);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection);
		}
	}

	@Override
	public void delete(Aplication entity) {
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

	@Override
	public boolean create(Aplication entity) {
		Connection connection = null;
        try {
            try {
				connection = DataSource.getInstance().getConnection();
			} catch (IOException | PropertyVetoException e) {
				e.printStackTrace();
			}
            PreparedStatement statement = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, entity.getId());
            statement.setInt(2,entity.getVoyage_id());
            statement.setInt(3, entity.getNumberOfPassengers());
            statement.setInt(4, entity.getCargo());
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
	public void update(Aplication entity) {
		Connection connection = null;
        try {
            try {
				connection = DataSource.getInstance().getConnection();
			} catch (IOException | PropertyVetoException e) {
				e.printStackTrace();
			}
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);
            statement.setInt(2,entity.getId());
            statement.setInt(1,entity.getVoyage_id());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
		
	}
	

}
