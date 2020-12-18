package com.app.everisdarmytasksms.controller;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//import org.springframework.validation.support.BindingAwareModelMap;
//import org.springframework.ui.Model;

import com.app.everisdarmytasksms.model.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class TaskControllerUnitTest {
	
	//Para comprobar cuento que tengo todas las tareas esperadas
	@Test
	public void getAllTask(){
		TaskController controller = new TaskController();
		//Model model = new BindingAwareModelMap();
		
		List <Task> Tasks = controller.getAllTask();
		int size = Tasks.size();
		
		System.out.println("Tamaño de la lista TEST " + size);
		
		//Probar con lista de dos tareas
		assertEquals(size, 2);
	}
	
	@Test
	public void saveTask() {
		TaskController controller = new TaskController();
		Task task = new Task("descripcion completa para test", "Completada");
		
		int id = controller.saveTask(task);
		
		System.out.println("Valor Id en el test: "+ id);
		System.out.println("Valor descripcion en el test: "+ task.getDescripcion());
		
		//Al probar, se crea la tarea mediante el contructor con id 1
		assertEquals(1, id);
	}
	
}
