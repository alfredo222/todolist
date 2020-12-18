package com.app.everisdarmytasksms.controller;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.ResponseEntity;

import com.app.everisdarmytasksms.model.Task;
import com.app.everisdarmytasksms.service.TaskService;

//import org.junit.platform.runner.JUnitPlatform;
import org.mockito.junit.MockitoJUnitRunner;

import com.app.everisdarmytasksms.controller.TaskController;


@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {
	
	@InjectMocks
	TaskController taskController;
	
	@Mock
	TaskService taskService;
	
	
	@Test
	public void testGetTasks() {
		
		//Creo tres tareas de prueba
		Task task1 = new Task("Descripcion tarea 1", "Completada");
		Task task2 = new Task("Descripcion tarea 2", "Completada");
		Task task3 = new Task("Descripcion tarea 3", "Completada");
		
		//Las añado en una lista de tareas
		List<Task> tasks = new ArrayList<Task>();
		tasks.add(task1);
		tasks.add(task2);
		tasks.add(task3);
		
		//Cuando se llame a la funcion getAllTask() se devuelve la lista de tasks
		when(taskService.getAllTask()).thenReturn(tasks);
		
		//Lista de tareas con el resultado de la petición
		List<Task> resultado = taskController.getAllTask(); //Usar taskController o taskService?
		
		//Compruebo que el tamaño de la lista devuelta es igual que el esperado
		assertThat(resultado.size()).isEqualTo(3);
	}
	
	@Test
	public void testSave() {
		//Creo una tarea que será guardada
		Task taskSave = new Task("Descripcion tarea 1 para Save", "Completada");
		
		//Obtengo el id de la tarea guardada
		int idTareaPrueba = taskSave.getId();
		
		//Cuando llame al metodo guardar, devuelvo el id de la tarea de prueba
		when(taskController.saveTask(taskSave)).thenReturn(idTareaPrueba);
		
		//Guardo el id devuelto por el metodo saveTask al guardar la tarea
		int idResultadoGuardar = taskController.saveTask(taskSave);
		
		//Comparo que el id devuelto tras guardar es igual al id de la tarea de prueba
		assertThat(idResultadoGuardar).isEqualTo(idTareaPrueba);
	}
	
	//Test para comprobar que al actualizar el estado o la descripción, se actualiza la tarea correctamente
	@Test
	public void testUpdate() {
		//Creo la tarea que sería la original
		Task taskOriginal = new Task("Descripcion Antes del Update", "Completada");
		taskController.saveTask(taskOriginal); //La guardo
		
		//Estos son los datos de la tarea que se esperan tras actualizar
		Task taskActualizada = new Task("Descripcion Despues del Update", "Completada");

		String descripcionActualizada  = taskActualizada.getDescripcion();
		String estadoActualizado = taskActualizada.getEstado();
		
		//when(taskController.updateTask(taskOriginal.getId(), taskOriginal)).thenReturn(taskActualizada);
		
		//Llamo a updateTask para actualizar la tarea con el id original y los nuevos datos
		taskController.updateTask(taskOriginal.getId(),taskActualizada); //Duda: Pasar solo la descripcion y el estado --- Pedir en el RequestBody esos datos y no la tarea
		//taskController.updateTaskPRUEBA(taskOriginal.getId(), descripcionActualizada, estadoActualizado);
		
		//Compruebo que la descripcion y estado de la tarea tras el update tienen el mismo valor que los datos pasados como parametros
		assertThat(taskActualizada.getDescripcion()).isEqualTo(descripcionActualizada);
		assertThat(taskActualizada.getEstado()).isEqualTo(estadoActualizado);
		
	}
	
	//Test para comprobar que se filtran las tareas correctamente con la función getFilterTask
	@Test
	public void testFilterTask() {
		
		//Tareas de prueba, con diferentes estados
		Task taskCompletada1 = new Task("Descripcion tarea completada 1", "Completada");
		Task taskCompletada2 = new Task("Descripcion tarea completada 2", "Completada");
		Task taskPendiente = new Task("Descripcion tarea pendiente", "Pendiente");
		Task taskInProgress = new Task("Descripcion tarea InProgress", "InProgress");
		
		//Se añaden al repositorio de tareas
		taskController.saveTask(taskCompletada1);
		taskController.saveTask(taskCompletada2);
		taskController.saveTask(taskPendiente);
		taskController.saveTask(taskInProgress);
		
		//Estado auxiliar para filtrar las tareas completadas
		String estadoPrueba = "Completada";
		
		//Creo la lista que debería devolver getFilterTask si se filtra bien
		List<Task> listaTasksCompletadas = new ArrayList<Task>(); 
		listaTasksCompletadas.add(taskCompletada1);
		listaTasksCompletadas.add(taskCompletada2);
		
		//Cuando se llame a getFilterTask, devolver la lista esperada de tareas completadas
		when(taskController.getFilterTask(estadoPrueba)).thenReturn(listaTasksCompletadas);
		
		//Finalmente, se llama al método getFilterTask con el estado de prueba y se guarda el resultado en una lista
		List <Task> resultadoTasksFiltradas = taskController.getFilterTask(estadoPrueba);
		
		//Compruebo que la primera tarea, por ejemplo, tiene el estado deseado
		assertThat(resultadoTasksFiltradas.get(0).getEstado()).isEqualTo(estadoPrueba);
		//Compruebo que el tamaño de ambas listas es el mismo
		assertThat(resultadoTasksFiltradas.size()).isEqualTo(listaTasksCompletadas.size());
	}
	
	@Test
	public void testDeleteTask() {
		//Tareas de prueba, con diferentes estados
		Task taskCompletada = new Task("Descripcion tarea completada ", "Completada");
		Task taskPendiente = new Task("Descripcion tarea pendiente", "Pendiente");
		Task taskInProgress = new Task("Descripcion tarea InProgress", "InProgress");
		
		//Se añaden al repositorio de tareas
		taskController.saveTask(taskCompletada);
		taskController.saveTask(taskPendiente);
		taskController.saveTask(taskInProgress);
		
		//Lista con las tareas creadas
		List <Task> tasksAntesDelete = new ArrayList<Task>();
		tasksAntesDelete.add(taskCompletada);
		tasksAntesDelete.add(taskPendiente);
		tasksAntesDelete.add(taskInProgress);
		
		//Comprobación tamaño de la lista antes del Delete
		int sizeAntesDelete = tasksAntesDelete.size();
		
		//Borramos la tarea con estado Completada, no uso when porque es void
		taskController.deleteTask(taskCompletada.getId());
		
		//Tamaño de la lista esperado
		int sizeDespuesDelete = sizeAntesDelete-1;
		
		//Se comprueba que el tamaño tras borrar la tarea es el esperado
		assertThat(sizeDespuesDelete).isEqualTo(2);
		
	}
	
	//Test para obtener una tarea por su id
	@Test
	public void testGetTaskById() {
		Task task = new Task("Descripcion tarea 1", "Completada"); //Tendrá id 1
		
		int idTask = taskController.saveTask(task);
		System.out.println("id antes "+idTask);
		
		when(taskController.saveTask(task)).thenReturn(idTask);
		System.out.println("id despues del when "+idTask);
		
		Task taskPorId = taskController.getTask(idTask);
	
		//System.out.println("id despues del getTask " + taskPorId.getId());
		
		assertThat(taskPorId.getId()).isEqualTo(idTask);
		
	}
	

	
	
}
