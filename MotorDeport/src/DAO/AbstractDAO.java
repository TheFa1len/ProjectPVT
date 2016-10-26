package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import Model.Entity;

public abstract class AbstractDAO<K, T extends Entity> {
	public abstract List<T> findAll();

	public abstract T findEntityById(K id);

	public abstract void delete(K id);

	public abstract void delete(T entity);

	public abstract boolean create(T entity);

	public abstract void update(T entity);

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
}