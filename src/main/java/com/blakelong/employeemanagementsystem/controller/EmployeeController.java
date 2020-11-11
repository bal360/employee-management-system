package com.blakelong.employeemanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	// @GetMapping - showFormForAdd
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		model.addAttribute("employee", new Employee());
		
		return "employees/employee-form";
	}
	
	// @PostMapping - save - Create AND Update
	@PostMapping("/save")
	public String save(@ModelAttribute("employee") Employee employee) {
		employeeService.save(employee);
		
		return "redirect:/employees/index";
	}
	
	// @GetMapping - showFormForUpdate
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int id, Model model) {
		Employee employee = employeeService.findById(id);
		
		model.addAttribute("employee", employee);
		
		return "employees/employee-form";
	}

	// @GetMapping - deleteById
	
	
	
}
