package com.blakelong.employeemanagementsystem.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blakelong.employeemanagementsystem.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	// CRUD methods now available from Spring Data
		// findAll()
		// findById(int id)
		// save(Employee employee) - Create AND Update
		// deleteById(int id)
	
	// *** query/sort methods to come ***
	
	@Query("SELECT e FROM Employee e WHERE "
			+ "CONCAT(e.firstName, ' ', e.lastName, ' ', e.email) "
			+ "LIKE %?1%")
	public Page<Employee> findAll(String searchTerm, Pageable pageable);
	
}
