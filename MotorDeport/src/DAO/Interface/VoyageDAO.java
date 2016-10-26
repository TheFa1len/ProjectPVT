package DAO.Interface;

import java.util.List;

import Model.Voyage;

public interface VoyageDAO extends DAO <Voyage>{
	public List<Voyage> findAll();
	public List<Voyage> findAllByEmp(Integer id);
	public List<Voyage> findAllNonByEmp(Integer id);
	public List<Voyage> findAllDrivers();
	public void update_driver(Voyage entity);
}
