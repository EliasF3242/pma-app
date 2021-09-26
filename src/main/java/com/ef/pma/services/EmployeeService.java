package com.ef.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ef.pma.dao.EmployeeRepository;
import com.ef.pma.dto.EmployeeProject;
import com.ef.pma.entities.Employee;


@Service //dependency injection will not work without @service
public class EmployeeService {
	//Field injection 
	@Autowired
	EmployeeRepository empRepo;
	 
	
	public Employee save(Employee employee) {
		return empRepo.save(employee);
	}
	

	public List<EmployeeProject> employeeProjects() {
		return empRepo.employeeProjects();
	}


	public List<Employee> findAll() {
		return empRepo.findAll();
	}
	
}
