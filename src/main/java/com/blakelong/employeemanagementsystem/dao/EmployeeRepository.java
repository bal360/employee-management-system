package com.blakelong.employeemanagementsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blakelong.employeemanagementsystem.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	// CRUD methods now available from Spring Data
		// findAll()
		// findById(int id)
		// save(Employee employee) - Create AND Update
		// deleteById(int id)
	
	// *** query/sort methods to come ***
}
