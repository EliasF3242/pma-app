package com.ef.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ef.pma.dto.ChartData;
import com.ef.pma.entities.Project;


//Interface for saving data to database 
public interface ProjectRepository extends CrudRepository<Project, Long> {
	@Override
	public List<Project> findAll();
	
	@Query(nativeQuery=true, value="SELECT stage as label, COUNT(*) as value " + 
			"FROM project " + 
			"GROUP BY stage")
	public List<ChartData> getProjectStatus();
}
