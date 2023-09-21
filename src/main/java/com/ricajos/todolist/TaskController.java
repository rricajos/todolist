package com.ricajos.todolist;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    /**
     * Muestra la lista de tareas.
     *
     * @param model El modelo que se utilizará para pasar datos a la vista.
     * @return El nombre de la vista Thymeleaf que muestra la lista de tareas.
     */
    @GetMapping("/")
    public String getAllTasks(Model model) {
        // Recupera todas las tareas de la base de datos
        List<Task> tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        return "task-list"; // Nombre de la vista Thymeleaf (task-list.html)
    }

    /**
     * Muestra el formulario para crear una nueva tarea.
     *
     * @param model El modelo que se utilizará para pasar datos a la vista.
     * @return El nombre de la vista Thymeleaf que muestra el formulario de
     *         creación.
     */
    @GetMapping("/new")
    public String showCreateTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "task-form"; // Nombre de la vista Thymeleaf (task-form.html)
    }

    /**
     * Muestra el formulario para editar una tarea existente.
     *
     * @param taskId El ID de la tarea que se va a editar.
     * @param model  El modelo que se utilizará para pasar datos a la vista.
     * @return El nombre de la vista Thymeleaf que muestra el formulario de edición.
     */
    @GetMapping("/{taskId}/edit")
    public String showEditTaskForm(@PathVariable Long taskId, Model model) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isPresent()) {
            model.addAttribute("task", task.get());
            return "task-form"; // Nombre de la vista Thymeleaf (task-form.html)
        } else {
            // Manejar el caso en el que no se encuentra la tarea
            return "redirect:/tasks/"; // Redirigir a la lista de tareas o manejar el error según tus necesidades
        }
    }

    /**
     * Procesa el formulario de creación o edición de una tarea.
     *
     * @param task El objeto Task que se vincula automáticamente a los datos del
     *             formulario.
     * @return La URL de redirección después de guardar la tarea.
     */
    @PostMapping("/save")
    public String saveTask(@ModelAttribute Task task) {
        taskRepository.save(task);
        return "redirect:/tasks/";
    }   

    /**
     * Elimina una tarea por su ID.
     *
     * @param taskId El ID de la tarea que se va a eliminar.
     * @return La URL de redirección después de eliminar la tarea.
     */
    @GetMapping("/{taskId}/delete")
    public String deleteTask(@PathVariable Long taskId) {
        // Verificar si la tarea existe en la base de datos
        Optional<Task> task = taskRepository.findById(taskId);

        if (task.isPresent()) {
            // Si la tarea existe, eliminarla
            taskRepository.delete(task.get());
        } 

        return "redirect:/tasks/"; // Redirigir a la lista de tareas después de eliminar
    }
}
