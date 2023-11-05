package com.plannerapp.model.view;

import com.plannerapp.model.entity.PriorityNameEnum;
import com.plannerapp.model.entity.Task;

import java.time.LocalDate;

public class TaskDTO {

    private Long id;

    private String description;

    private LocalDate dueDate;

    private PriorityNameEnum priority;

    public TaskDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public PriorityNameEnum getPriority() {
        return priority;
    }

    public void setPriority(PriorityNameEnum priority) {
        this.priority = priority;
    }

    public static TaskDTO createFromTask (Task task){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setDescription(task.getDescription());
        taskDTO.setPriority(task.getPriority().getPriorityName());
        taskDTO.setDueDate(task.getDueDate());
        taskDTO.setId(task.getId());

        return taskDTO;
    }
}
