package com.cognizant.crud.DAO;

import java.util.List;

import com.cognizant.crud.Entity.Employee;

public interface EmployeeDAO {
	
	public List<Employee> findAll();
	
	public Employee findById(int id);
	
	public int save(Employee employee);
	
	public int deleteById(int id);
	
	public int update(Employee employee,int id);
	

}
