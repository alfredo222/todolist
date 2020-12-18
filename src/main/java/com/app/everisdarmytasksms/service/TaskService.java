package com.app.everisdarmytasksms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.everisdarmytasksms.model.Task;
import com.app.everisdarmytasksms.repo.TaskRepository;


@Service
public class TaskService {
	
	@Autowired
	TaskRepository taskRepository;
	
	//Listar todas las Tasks
	public List<Task> getAllTask(){
		List<Task> tasks = new ArrayList<Task>();
		taskRepository.findAll().forEach(task -> tasks.add(task));
		return tasks;
	}
	
	//Para obtener una tarea, según su id
	public Task getTaskById(int id) {
		return taskRepository.findById(id).get();
	}
	
	//-------PROBANDO--------
	//Filtrar Task por estado
	public List<Task>getTaskByEstado(String estado) {
		
		//Estado de la tarea actual en la iteración
		String estadoTareaActual ="";
		
		List<Task> tasksPorEstado = new ArrayList<Task>();   //Lista para todas las tareas
		List<Task> tasksFiltradas = new ArrayList<Task>();   //Lista para todas las tareas filtradas
		//List<Task> tasksCompletadas = new ArrayList<Task>(); //Lista para las tareas completadas
		//List<Task> tasksPendientes = new ArrayList<Task>();  //Lista para las tareas pendientes
		//List<Task> tasksInProgress = new ArrayList<Task>();  //Lista para las tareas pendientes
		
		//Creo un listado con todas las tareas
		taskRepository.findAll().forEach(task -> tasksPorEstado.add(task));

		//Recorro la lista de todas las tareas, obtengo el estado de cada una de ellas y si coincide con el parámetro la almaceno en la lista tasksFiltradas
		for(int i=0; i< tasksPorEstado.size(); i++) {
			
			estadoTareaActual = tasksPorEstado.get(i).getEstado();
			
			if(estadoTareaActual.contentEquals(estado)) {
				//Añadir tarea a la lista de completadas
				tasksFiltradas.add(tasksPorEstado.get(i));
			}
		}
		
		return tasksFiltradas;
	}
	//-------FIN PROBANDO--------
	
	//Guardar la tarea en BBDD h2
	public int saveTask(Task task) {
		taskRepository.save(task);
		return task.getId();
	}
	
	//Actualizar tarea, identificandola por el  id
	public void updateTask(int id, Task task) {
		
		//Obtengo la tarea según el id
		Task auxTask = taskRepository.findById(id).get();
		
		//Obtengo los nuevos valores para la descripcion y el estado
		String descripcionAux = task.getDescripcion();
		String estadoAux = task.getEstado();
		
		//Set de los nuevos valores
		auxTask.setDescripcion(descripcionAux);
		auxTask.setEstado(estadoAux);
		
		//Actualizar tarea en el repositorio con los nuevos valores
		taskRepository.save(auxTask);
	}
	
	//Actualizar tarea, identificandola por el  id
	public void updateTaskPRUEBA(int id, String descripcion, String estado) {
			
		//Obtengo la tarea según el id
		Task auxTask = taskRepository.findById(id).get();

		//Set de los nuevos valores
		auxTask.setDescripcion(descripcion);
		auxTask.setEstado(estado);
		
		//Actualizar tarea en el repositorio con los nuevos valores
		taskRepository.save(auxTask);
	}
	
	//Borrar una tarea por id
	public void delete(int id) {
		taskRepository.deleteById(id);
	}
	
}
