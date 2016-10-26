package DAO.Interface;

import java.util.List;

import Model.Aplication;

public interface AplicationDAO extends DAO<Aplication>{
	void add(Aplication t);
	void update(Aplication t);
	Aplication get (int id);
	void delete (Aplication t);
	public List<Aplication> findAll();
	public List<Aplication> findFree();
	
}
