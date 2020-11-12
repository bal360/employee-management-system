package com.blakelong.employeemanagementsystem.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blakelong.employeemanagementsystem.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	// CRUD methods now available from Spring Data
		// findAll()
		// findById(int id)
		// save(Employee employee) - Create AND Update
		// deleteById(int id)
	
	// *** query/sort methods to come ***
//	public List<Employee> findAllByOrderByLastNameAsc();
	
	public Page<Employee> findAll(Pageable pageable);
}
