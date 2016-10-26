package DAO.Interface;

import java.util.List;

import Model.Post;

public interface PostDAO extends DAO<Post>{
	public List<Post> findAll();
	
}
