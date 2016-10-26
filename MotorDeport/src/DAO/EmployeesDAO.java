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
import Model.Empployees;

public class EmployeesDAO extends AbstractDAO<Integer,Empployees>{
	public static final String SQL_SELECT_ALL_DRIVERS = "SELECT * FROM empployees WHERE id_post=2";
	public static final String SQL_SELECT_ALL= "SELECT * FROM empployees";
	public static final String SQL_FIND_BY_ID = "SELECT * FROM empployees WHERE empployee_id = ?";
	public static final String SQL_FIND_BY_LOGIN = "SELECT * FROM empployees WHERE login = ?";
	public static final String SQL_FIND_BY_CAR = "SELECT * FROM empployees WHERE auto_id = ?";
	public static final String SQL_DELETE = "DELETE FROM empployees WHERE empployee_id = ?";
	public static final String SQL_CREATE = "INSERT INTO empployees VALUES (?,?,?,?,?,?,?,?)";

	@Override
	public List<Empployees> findAll() {
		List<Empployees> empployees = new ArrayList<>();
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
				Empployees emp = new Empployees();
				emp.setId(resultSet.getInt("employee_id"));
				emp.setName(resultSet.getString("first_name"));
				emp.setLastname(resultSet.getString("last_name"));
				emp.setPhone(resultSet.getInt("telephone"));
				emp.setPost_id(resultSet.getInt("id_post"));
				emp.setAutoId(resultSet.getInt("auto_id"));
				emp.setLogin(resultSet.getString("login"));
				emp.setPassword(resultSet.getString("pass"));
				empployees.add(emp);
			}
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			close(st);
		}
		return empployees;
	}
	public List<Empployees> findAllDrivers() {
		List<Empployees> empployees = new ArrayList<>();
		Connection cn = null;
		Statement st = null;
		try {
			try {
				cn = DataSource.getInstance().getConnection();
			} catch (IOException | PropertyVetoException e) {
				e.printStackTrace();
			}
			st = cn.createStatement();
			ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL_DRIVERS);
			while (resultSet.next()) {
				Empployees emp = new Empployees();
				emp.setId(resultSet.getInt("employee_id"));
				emp.setName(resultSet.getString("first_name"));
				emp.setLastname(resultSet.getString("last_name"));
				emp.setPhone(resultSet.getInt("telephone"));
				emp.setPost_id(resultSet.getInt("id_post"));
				emp.setAutoId(resultSet.getInt("auto_id"));
				emp.setLogin(resultSet.getString("login"));
				emp.setPassword(resultSet.getString("pass"));
				empployees.add(emp);
			}
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			close(st);
		}
		return empployees;
	}

	@Override
	public Empployees findEntityById(Integer id) {
		return null;
	}
	public Empployees findByLogin(String login){
		Empployees item = new Empployees();
		Connection connection = null;
		try {
			try {
				connection = DataSource.getInstance().getConnection();
			} catch (IOException | PropertyVetoException e) {
				e.printStackTrace();
			}
			PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_LOGIN);
			statement.setString(1, login);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				item.setId(resultSet.getInt("employee_id"));
				item.setName(resultSet.getString("first_name"));
				item.setLastname(resultSet.getString("last_name"));
				item.setPhone(resultSet.getInt("telephone"));
				item.setPost_id(resultSet.getInt("id_post"));
				item.setAutoId(resultSet.getInt("auto_id"));
				item.setLogin(resultSet.getString("login"));
				item.setPassword(resultSet.getString("pass"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection);
		}
		return item;
	}
	public Empployees findEntityByCarId(Integer id) {

		Empployees item = new Empployees();
		Connection connection = null;
		try {
			try {
				connection = DataSource.getInstance().getConnection();
			} catch (IOException | PropertyVetoException e) {
				e.printStackTrace();
			}
			PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_CAR);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				item.setId(resultSet.getInt("employee_id"));
				item.setName(resultSet.getString("first_name"));
				item.setLastname(resultSet.getString("last_name"));
				item.setPhone(resultSet.getInt("telephone"));
				item.setPost_id(resultSet.getInt("id_post"));
				item.setAutoId(resultSet.getInt("auto_id"));
				item.setLogin(resultSet.getString("login"));
				item.setPassword(resultSet.getString("pass"));
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
	}

	@Override
	public void delete(Empployees entity) {
	}

	@Override
	public boolean create(Empployees entity) {
		Connection connection = null;
        try {
            try {
				connection = DataSource.getInstance().getConnection();
			} catch (IOException | PropertyVetoException e) {
				e.printStackTrace();
			}
            PreparedStatement statement = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
            List<Empployees> list = this.findAll();
            statement.setInt(1,list.size()+1);
            statement.setString(2, entity.getName());
            statement.setString(3, entity.getLastname());
            statement.setInt(4, entity.getPhone());
            statement.setInt(6, entity.getAutoId()); 
            statement.setInt(5, entity.getPost_id());
            statement.setString(7, entity.getLogin());
            statement.setString(8, entity.getPassword());
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
	public void update(Empployees entity) {
	}

}
