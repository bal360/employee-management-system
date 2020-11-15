package com.blakelong.employeemanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String findAll(Model model) {
		return listByPage(model, 1, "lastName", "asc");
	}
	
	@GetMapping("index/page/{pageNumber}")
	public String listByPage(Model model, 
							@PathVariable("pageNumber") int currentPage,
							@Param("sortField") String sortField,
							@Param("sortDirection") String sortDirection){
		// get page object
		Page<Employee> page = employeeService.findAll(currentPage, sortField, sortDirection);
		// get total # of employees
		long totalEmployees = page.getTotalElements();
		// get total # of pages
		int totalPages = page.getTotalPages();
	
		
		// List<Employee> listEmployees = page.getContent();
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalEmployees", totalEmployees);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("page", page);
		
		String reverseSortDirection = sortDirection.equals("asc") ? "desc" : "asc";
		model.addAttribute("reverseSortDirection", reverseSortDirection);
		
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
	
//	// @PostMapping - search
//	@GetMapping("/search")
//	public String search(@RequestParam("theSearchName") Optional<String> string, Model model, @PageableDefault(size = 6) Pageable pageable) {
//		
//		Page<Employee> employees = employeeService.findByName(string, pageable);
//
//		model.addAttribute("page", employees);
//
//		System.out.println("==========>>>>>>>>=======>>>>> Running fine here");
//		
//		return "employees/employees-list";
//	}
	
	
	
}
