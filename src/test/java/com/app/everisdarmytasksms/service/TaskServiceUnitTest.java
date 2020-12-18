package com.app.everisdarmytasksms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.everisdarmytasksms.model.Task;

@SpringBootTest
public class TaskServiceUnitTest {
	@Test
	public void getAllTask(){
		TaskService controller = new TaskService();
		//Model model = new BindingAwareModelMap();
		
		List <Task> Tasks = controller.getAllTask();
		int size = Tasks.size();
		
		System.out.println("Tamaño de la lista TEST " + size);
		
		//Probar con lista de dos tareas
		assertEquals(size, 2);
	}
	
	@Test
	public void saveTask() {
		TaskService controller = new TaskService();
		Task task = new Task("descripcion completa para test", "Completada");
		
		int sizeBefore = controller.getAllTask().size();
		controller.saveTask(task);
		int sizeAfter = controller.getAllTask().size();
		
		System.out.println("Valor descripcion en el test: "+ task.getDescripcion());
		
		//Al probar, se crea la tarea mediante el contructor con id 1
		assertEquals(sizeAfter, sizeBefore+1);
	}

}
