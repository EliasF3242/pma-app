package com.ef.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ef.pma.entities.Employee;
import com.ef.pma.services.EmployeeService;


@Controller
@RequestMapping("/employees")
public class EmployeeController {
  
	//Interface for saving data to database
	@Autowired
	EmployeeService empService;
	
	@GetMapping
	public String displayEmployees(Model model) {
		List<Employee>employees = empService.findAll();
		model.addAttribute("employees", employees);
		return "employees/list-employees";}
	
	@GetMapping("/new")//display new employee form
	public String displayEmployeeForm(Model model) {
		Employee anEmployee =  new Employee();
		model.addAttribute("employee", anEmployee);
		return "employees/new-employee"; 
		
	}
	
	@PostMapping("/save")//save form to database 
	public String createEmployee(Employee anEmployee, Model model) {
		//this handles saving to database
		empService.save(anEmployee);
		//use a redirect when saving to prevent duplicate submissions 
		return "redirect:/employees";	
	}
	
}