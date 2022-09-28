package com.cognizant.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.crud.DAO.EmployeeDAO;
import com.cognizant.crud.Entity.Employee;
import com.cognizant.crud.pojo.AuthenticationRequest;
import com.cognizant.crud.pojo.AuthenticationResponse;
import com.cognizant.crud.service.MyUserDetailsService;
import com.cognizant.crud.tokenutil.JwtTokenUtil;

@RestController
public class CrudController {

	@Autowired
	EmployeeDAO dao;

	@Autowired
	AuthenticationManager authManager;

	@Autowired
	MyUserDetailsService service;

	@Autowired
	JwtTokenUtil jwtUtil;

	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest req) throws Exception {
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
		} catch (Exception e) {
			throw new Exception("Incorrect Username and Password", e);
		}
		final UserDetails userDetails = service.loadUserByUsername(req.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

	@GetMapping("/employees")
	public List<Employee> getEmployee() {
		return dao.findAll();
	}

	@GetMapping("/employees/{id}")
	public Employee getEmployeeByID(@PathVariable int id) {
		return dao.findById(id);
	}

	@PostMapping("/addemployee")
	public int addEmployee(@RequestBody Employee e) {
		return dao.save(e);
	}

	@PutMapping("/updateemployee/{id}")
	public int updateEmployee(@RequestBody Employee e, @PathVariable int id) {
		return dao.update(e, id);
	}

	@DeleteMapping("/delete/{id}")
	public int deleteById(@PathVariable int id) {
		return dao.deleteById(id);
	}

}
