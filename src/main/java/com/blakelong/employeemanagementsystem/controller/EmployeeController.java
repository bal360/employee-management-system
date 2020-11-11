package com.blakelong.employeemanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blakelong.employeemanagementsystem.entity.Employee;
import com.blakelong.employeemanagementsystem.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	// inject EmployeeService
	@Autowired
	EmployeeService employeeService;
	
	// @GetMapping - list employees
	@GetMapping("/index")
	public String findAll(Model model) {
		List<Employee> employees = employeeService.findAll();
		
		model.addAttribute("employees", employees);
		
		return "employees/employees-list";
	}
	// @GetMapping - findById
	
	// @PostMapping - save - Create AND Update
	
	// @GetMapping - deleteById
	
	
	
}
