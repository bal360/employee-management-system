package com.blakelong.employeemanagementsystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
	EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	
	// @GetMapping - list employees
	@GetMapping("/index")
	public String findAll(@PageableDefault(size = 6) Pageable pageable, Model model) {

		
//		if (name.isEmpty()) {
			Page<Employee> page = employeeService.findAll(pageable);
			model.addAttribute("page", page);
//			System.out.println("================>>>>>>> pageObject: " + page.getContent());
//		} else {
//			Page<Employee> page = employeeService.findByName(name, pageable);
//			model.addAttribute("page", page);
//		}
		

//			@RequestParam("theSearchName") Optional<String> name
		
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
	@GetMapping("/deleteById")
	public String deleteById(@RequestParam("employeeId") int id) {

		employeeService.deleteById(id);
		
		return "redirect:/employees/index";
	}
	
	// @PostMapping - search
	@GetMapping("/search")
	public String search(@RequestParam("theSearchName") Optional<String> string, Model model, @PageableDefault(size = 6) Pageable pageable) {
		
		Page<Employee> employees = employeeService.findByName(string, pageable);

		model.addAttribute("page", employees);

		System.out.println("==========>>>>>>>>=======>>>>> Running fine here");
		
		return "employees/employees-list";
	}
	
	
	
}
