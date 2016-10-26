package DAO.Interface;

import java.util.List;

import Model.Carpark;

public interface CarparkDAO extends DAO<Carpark>{
	public List<Carpark> findAll();
	
}
