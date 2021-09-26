package com.ef.pma.controllers;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ef.pma.dao.EmployeeRepository;
import com.ef.pma.dao.ProjectRepository;
import com.ef.pma.entities.Employee;
import com.ef.pma.entities.Project;


@Component
public class SeedDatabase implements CommandLineRunner {
	 
	/* 
	 data.sql file in resources folder will be used
	 to store data.
	 
	  seedDatabase.java was used to seed test database
	 */
	
	/*add application properties:
	spring.jpa.hibernate.ddl-auto=none

	with schema.sql file in resources folder 
	to create SQL database schema the application will use 
	*/	

	@Autowired
	EmployeeRepository empRepo;
		
	@Autowired
	ProjectRepository proRepo;
	
	@Override
	public void run(String... args) throws Exception {

			Employee emp1 = new Employee("John", "Warton", "warton@gmail.com");
			Employee emp2 = new Employee("Mike", "Lanister", "lanister@gmail.com");
			Employee emp3 = new Employee("Steve", "Reeves", "Reeves@gmail.com");

			Employee emp4 = new Employee("Ronald", "Connor", "connor@gmail.com");
			Employee emp5 = new Employee("George", "Jefferson", "miles@gmail.com");
			
			
			Project pro1 = new Project("Large Production Deploy", "NOTSTARTED", "This requires all hands on deck for"
					+ "the final deployment of the software into production");
			Project pro2 = new Project("New Employee Budget",  "COMPLETED", "Decide on a new employee bonus budget"
					+ "for the year and figureout who will be promoted");
			Project pro3 = new Project("Office Reconstruction", "INPROGRESS", "The office building in Monroe has "
					+ "been damaged due to hurricane in the region. This needs to be reconstructed");
			Project pro4 = new Project("Improve Intranet Security", "INPROGRESS", "With the recent data hack, the office"
					+ "security needs to be improved and proper security team needs to be hired for "
					+ "implementation");
			
			
			
			// need to set both sides of the relationship manually

			pro1.addEmployees(emp1);
			pro1.addEmployees(emp2);
			pro2.addEmployees(emp3);
			pro3.addEmployees(emp1);
			pro4.addEmployees(emp1);
			pro4.addEmployees(emp3);
			
			pro4.addEmployees(emp5);//test
			
			// need to set both sides of the relationship manually

			emp1.setProjects(Arrays.asList(pro1, pro3, pro4));
			emp2.setProjects(Arrays.asList(pro1));
			emp3.setProjects(Arrays.asList(pro2, pro4));
			emp5.setProjects(Arrays.asList(pro4));//test
			// save employees in database

			empRepo.save(emp1);
			empRepo.save(emp2); 
			empRepo.save(emp3); 
			empRepo.save(emp4);
			empRepo.save(emp5); 
			
			
			// save projects in database

			proRepo.save(pro1);
			proRepo.save(pro2); 
			proRepo.save(pro3); 
			proRepo.save(pro4);
		
		
		}
}


