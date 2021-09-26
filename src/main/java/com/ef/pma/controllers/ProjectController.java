package com.ef.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ef.pma.dao.EmployeeRepository;
import com.ef.pma.dao.ProjectRepository;
import com.ef.pma.entities.Employee;
import com.ef.pma.entities.Project;



@Controller
@RequestMapping("/projects")
public class ProjectController {
  
	//Interface for saving data to database
	@Autowired
	ProjectRepository proRepo;
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping
	public String displayProjects(Model model) {
		List<Project>projects = proRepo.findAll();
		model.addAttribute("projects", projects);
		return "projects/list-projects";}
	
	@GetMapping("/new")//get
	public String displayProjectForm(Model model) {
		Project aProject =  new Project();
		List<Employee> employees = empRepo.findAll();
		model.addAttribute("project", aProject);
		model.addAttribute("allEmployees", employees);
		return "projects/new-project"; 
		
	}
	
	@PostMapping("/save")//save form to database 
	public String createProject(Project project, Model model) {
		//this handles saving to database
		proRepo.save(project);
		/*Iterable<Employee> chosenEmployee = empRepo.findAllById(employees);
		for(Employee emp: chosenEmployee) {
			emp.setProjects(project);
			empRepo.save(emp);
		} need for oneto many @RequestParam List<Long> employees,*/
		
		//use a redirect when saving to prevent duplicate submissions 
		return "redirect:/projects"; 
		
	}
	
}