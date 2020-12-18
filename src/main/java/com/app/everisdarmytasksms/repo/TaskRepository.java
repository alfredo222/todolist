package com.app.everisdarmytasksms.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.everisdarmytasksms.model.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer>{
	
}
