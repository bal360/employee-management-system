package com.blakelong.employeemanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blakelong.employeemanagementsystem.dao.EmployeeRepository;
import com.blakelong.employeemanagementsystem.entity.Employee;

@Repository
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAllByOrderByLastNameAsc();
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
