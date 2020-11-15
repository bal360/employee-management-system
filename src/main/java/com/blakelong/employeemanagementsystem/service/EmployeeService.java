package com.blakelong.employeemanagementsystem.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.blakelong.employeemanagementsystem.entity.Employee;

public interface EmployeeService {
	
	public Page<Employee> findAll(int pageNumber, String sortField, String sortDirection, String searchTerm);
	
	public Employee findById(int id);
	
	public void save(Employee employee);
	
	public void deleteById(int id);

}
