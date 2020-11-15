package com.blakelong.employeemanagementsystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.blakelong.employeemanagementsystem.dao.EmployeeRepository;
import com.blakelong.employeemanagementsystem.entity.Employee;

@Repository
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	@Override
	public Page<Employee> findAll(int pageNumber, String sortField, String sortDirection) {
		Sort sort = Sort.by(sortField);
		sort = sortDirection.equals("asc") ? sort.ascending() : sort.descending();
				
		Pageable pageable = PageRequest.of(pageNumber - 1, 5, sort);
		
		return employeeRepository.findAll(pageable);
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
	
//	@Override
//	public Page<Employee> findByLastNameOrFirstName(String name, Pageable pageable) {
//		return employeeRepository.findByLastNameOrFirstName(name, pageable);
//	};
	
	@Override
	public Page<Employee> findByName(Optional<String> name, Pageable pageable) {
		return employeeRepository.findByName(name, pageable);
	}
	
}
