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
import Model.Statements;

public class StatementsDAO extends AbstractDAO<Integer, Statements>{
	public static final String SQL_SELECT_ALL_STATES = "SELECT * FROM statements";
	public static final String SQL_FIND_BY_ID = "SELECT * FROM statements WHERE state_id = ?";

	@Override
	public List<Statements> findAll() {
		List<Statements> states = new ArrayList<>();
		Connection cn = null;
		Statement st = null;
		try {
			try {
				cn = DataSource.getInstance().getConnection();
			} catch (IOException | PropertyVetoException e) {
				e.printStackTrace();
			}
			st = cn.createStatement();
			ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL_STATES);
			while (resultSet.next()) {
				Statements state = new Statements();
				state.setId(resultSet.getInt("state_id"));
				state.setStatement(resultSet.getString("state"));
				states.add(state);
			}
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			close(st);
		}
		return states;

	}

	@Override
	public Statements findEntityById(Integer id) {
		Statements item = new Statements();
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
				item.setId(resultSet.getInt("state_id"));
				item.setStatement(resultSet.getString("state"));
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
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(Statements entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean create(Statements entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void update(Statements entity) {
		throw new UnsupportedOperationException();
		
	}
	

}
