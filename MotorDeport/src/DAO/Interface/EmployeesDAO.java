package DAO.Interface;

import java.util.List;

import Model.Empployees;

public interface EmployeesDAO extends DAO<Empployees>{
	public List<Empployees> findAll();
	public List<Empployees> findAllDrivers();
	public Empployees findEntityByCarId(Integer id);
	public Empployees findByLogin(String login);
}
