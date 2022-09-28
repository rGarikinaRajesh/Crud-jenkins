package com.cognizant.crud.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cognizant.crud.Entity.Employee;

@Repository
public class EmployeeImpl implements EmployeeDAO {

	@Autowired
	JdbcTemplate jdbc;

	@Override
	public List<Employee> findAll() {
		return jdbc.query("select * from employee", new BeanPropertyRowMapper<Employee>(Employee.class));
	}

	@Override
	public Employee findById(int id) {
		return jdbc.queryForObject("select * from employee where id=?",
				new BeanPropertyRowMapper<Employee>(Employee.class), id);
	}

	@Override
	public int save(Employee e) {
		return jdbc.update("INSERT INTO employee (name, skill) VALUES (?, ?)",
				new Object[] { e.getName(), e.getSkill() });
	}

	@Override
	public int deleteById(int id) {
		return jdbc.update("DELETE from employee where id=?", id);
	}

	@Override
	public int update(Employee e, int id) {
		return jdbc.update("UPDATE employee SET name=? ,skill=? where id=?",
				new Object[] { e.getName(), e.getSkill(), id });
	}

}
