package com.ef.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ef.pma.dao.EmployeeRepository;
import com.ef.pma.dao.ProjectRepository;
import com.ef.pma.dto.ChartData;
import com.ef.pma.dto.EmployeeProject;
import com.ef.pma.entities.Project;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
@RequestMapping
public class HomeController {
	
	@Value("${version}")
	private String ver; //version from application.properties
	@Autowired
	ProjectRepository proRepo;
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		//Map<String, Object> map = new HashMap<>();
		model.addAttribute("envVersionNum", ver);
		//querying the database for projects
		List<Project> projects = proRepo.findAll();
		//"projects" is ThymeLeaf variable 
		model.addAttribute("projects", projects);
		
		List<ChartData> projectData = proRepo.getProjectStatus();
		// let's convert projectData object into JSON for use in JavaScript 
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectData);
		// [["NOTSTARTED", 1], ["INPROGRESS", 2], ["COMPLETED", 1]]
		model.addAttribute("projectStatusCnt", jsonString);
		
		
		//querying the database for employees
		List<EmployeeProject> employeesProjectCnt = empRepo.employeeProjects();
		
		model.addAttribute("employeesListProjectsCnt", employeesProjectCnt);
		
		return "main/home";
	}
	
	
	
}
