package com.app.everisdarmytasksms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.everisdarmytasksms.model.Task;
import com.app.everisdarmytasksms.service.TaskService;

@RestController
public class TaskController {
	
	@Autowired
	TaskService taskService;
	
	//Se define la ruta tasks donde se mostraran todas las tareas
	@GetMapping("/tasks")
	public List<Task> getAllTask(){
		return taskService.getAllTask();
	}
	
	//---------PROBANDO---------
	//Para obtener una lista de las tareas, filtradas según su estado
	@GetMapping("/tasks/{estado}")
	public List<Task> getFilterTask(@PathVariable("estado")String estado){
		return taskService.getTaskByEstado(estado);
	}
	//---------FIN PROBANDO---------
	
	//Obtener tarea por id
	@GetMapping("/task/{id}")
	public Task getTask(@PathVariable("id")int id) {
		return taskService.getTaskById(id);
	}
	
	//Guardar una tarea, tras el guardado se devuelve el id de la tarea guardada
	@PostMapping("/task")
	public int saveTask(@RequestBody Task task) {
		int id = taskService.saveTask(task);
		return id;
	}
	
	@PutMapping("/task/update/{id}")
	public void updateTask(@PathVariable("id")int id, @RequestBody Task task) {
		taskService.updateTask(id, task);
	}
	
	@PutMapping("/task/update/prueba/{id}")
	public void updateTaskPRUEBA(@PathVariable("id")int id, @RequestBody String descripcion, String estado) {
		taskService.updateTaskPRUEBA(id, descripcion, estado);
	}
	
	//Borrar una tarea concreta
	@DeleteMapping("task/{id}")
	public void deleteTask(@PathVariable("id")int id) {
		taskService.delete(id);
	}
}
	
	
	
	

