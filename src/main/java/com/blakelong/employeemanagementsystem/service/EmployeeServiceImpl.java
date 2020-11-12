package com.blakelong.employeemanagementsystem.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.blakelong.employeemanagementsystem.dao.EmployeeRepository;
import com.blakelong.employeemanagementsystem.entity.Employee;

@Repository
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeRepository employeeRepository;

	private EntityManager entityManager;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository, EntityManager entityManager) {
		this.employeeRepository = employeeRepository;
		this.entityManager = entityManager;
	}
	
	@Override
	public Page<Employee> findAll(Pageable pageable) {
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
	public Page<Employee> findByName(String name, Pageable pageable) {
		return employeeRepository.findByName(name, pageable);
		
//		
//		Query<Employee> theQuery = null;
//		
//		if (theSearchName != null && theSearchName.trim().length() > 0) {
//			// search for firstName or lastName - case insensitive
//			theQuery = (Query<Employee>) entityManager.createQuery("from Employee WHERE lower(firstName) LIKE :theName OR lower(lastName) LIKE :theName", Employee.class);
//			theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
//		} else {
//			// theSearchName is empty so just get all customers
//			theQuery = (Query<Employee>) entityManager.createQuery("from Employee ORDER BY lastName", Employee.class);
//		}
//		
//		// execute query and get result list
//		Page<Employee> employees = (Page<Employee>)(List<Employee>) theQuery.getResultList();
		
		//return results
//		return employees;
		
	}
	
}
