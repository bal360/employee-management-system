package com.blakelong.employeemanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.blakelong.employeemanagementsystem.dao.EmployeeRepository;
import com.blakelong.employeemanagementsystem.entity.Employee;

public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}
	
	@Override
	public Employee findById(int id) {
		Optional<Employee> result = employeeRepository.findById(id);
		
		Employee employee;
		
		if (result.isPresent()) {
			employee = result.get();
		} else {
			throw new RuntimeException("No employee found id - " + id);
		}
		
		return employee;
	}
	
	@Override 
	public void save(Employee employee) {
		employeeRepository.save(employee); 
	}
	
	@Override 
	public void deleteById(int id) {
		employeeRepository.deleteById(id);
	}
}