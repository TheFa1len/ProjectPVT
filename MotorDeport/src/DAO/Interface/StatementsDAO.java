package DAO.Interface;

import java.util.List;

import Model.Statements;

public interface StatementsDAO extends DAO <Statements>{
	public List<Statements> findAll();
	
}
