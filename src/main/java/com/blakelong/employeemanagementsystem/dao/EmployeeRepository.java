package com.blakelong.employeemanagementsystem.dao;

import java.util.Optional;

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
//	public List<Employee> findAllByOrderByLastNameAsc();
	
	public Page<Employee> findAll(Pageable pageable);
	
	@Query("SELECT e FROM Employee e WHERE lower(firstName) LIKE %?1% OR lower(lastName) LIKE %?1%")
	public Page<Employee> findByName(Optional<String> name, Pageable pageable);
	
//	public Page<Employee> findByLastNameOrFirstName(String string, Pageable pageable);
}
