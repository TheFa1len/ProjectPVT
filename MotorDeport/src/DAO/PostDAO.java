package DAO;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Connection.DataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Post;

public class PostDAO extends AbstractDAO<Integer, Post>{
	public static final String SQL_SELECT_ALL_POSTS = "SELECT * FROM post";
	public static final String SQL_FIND_BY_ID = "SELECT * FROM post WHERE id_post = ?";
	@Override
	public List<Post> findAll() {
		List<Post> posts = new ArrayList<Post>();
		Connection cn = null;
		Statement st = null;
		try {
			try {
				cn = DataSource.getInstance().getConnection();
			} catch (IOException | PropertyVetoException e) {
				e.printStackTrace();
			}
			st=cn.createStatement();
			ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL_POSTS);
			while(resultSet.next()){
				Post post = new Post();
				post.setId(resultSet.getInt("id_post"));
				post.setPost(resultSet.getString("post"));
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
	public Post findEntityById(Integer id) {
		Post  item = new Post ();
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
				item.setId(resultSet.getInt("id_post"));
				item.setPost(resultSet.getString("post"));
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
	public void delete(Post entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean create(Post entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void update(Post entity) {
		throw new UnsupportedOperationException();
	}

}
