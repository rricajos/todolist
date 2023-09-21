package com.ricajos.todolist;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Representa una tarea en una lista de tareas.
 * Cada tarea tiene un título, una descripción y un estado de completado.
 */
@Entity // Anotación de JPA que indica que esta clase es una entidad persistente.
public class Task {

    /** Identificador único de la tarea. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** El título de la tarea. */
    private String title;

    /** La descripción detallada de la tarea. */
    private String description;

    /** Indica si la tarea está marcada como completada o no. */
    private boolean completed;

    /**
     * Crea una nueva instancia de tarea con título y descripción especificados.
     *
     * @param title       El título de la tarea.
     * @param description La descripción detallada de la tarea.
     */
    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.completed = false; // Por defecto, una tarea se crea como no completada.
    }

    /**
     * Crea una nueva instancia de tarea sin título y descripción especificados.
     */
    public Task() {
    }

    /**
     * Obtiene el identificador único de la tarea.
     *
     * @return El identificador único de la tarea.
     */
    public Long getId() {
        return id;
    }

    /**
     * Obtiene el título de la tarea.
     *
     * @return El título de la tarea.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Establece el título de la tarea.
     *
     * @param title El título de la tarea.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Obtiene la descripción detallada de la tarea.
     *
     * @return La descripción de la tarea.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece la descripción detallada de la tarea.
     *
     * @param description La descripción de la tarea.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Verifica si la tarea está completada.
     *
     * @return true si la tarea está completada, false de lo contrario.
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Establece el estado de completado de la tarea.
     *
     * @param completed true si la tarea está completada, false de lo contrario.
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
