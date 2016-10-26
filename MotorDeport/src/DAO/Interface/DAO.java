package DAO.Interface;

public interface DAO<T> {
	void add(T t);
	void update(T t);
	T get (int id);
	void delete (T t);
}
