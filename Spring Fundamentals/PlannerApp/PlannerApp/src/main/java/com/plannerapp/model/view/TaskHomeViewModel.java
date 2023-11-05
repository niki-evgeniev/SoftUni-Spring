package com.plannerapp.model.view;

import com.plannerapp.model.entity.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskHomeViewModel {

    private List<TaskDTO> assignedTaskToMe;
    private List<TaskDTO> availableTask;

    private int availableSize;


    public TaskHomeViewModel() {
        this(new ArrayList<>(), new ArrayList<>());
    }

    public TaskHomeViewModel(List<TaskDTO> assignedTaskToMe, List<TaskDTO> availableTask) {
        this.assignedTaskToMe = assignedTaskToMe;
        this.availableTask = availableTask;
        this.availableSize = availableTask.size();
    }

    public int getAvailableSize() {
        return availableSize;
    }

    public void setAvailableSize(int availableSize) {
        this.availableSize = availableSize;
    }

    public List<TaskDTO> getAssignedTaskToMe() {
        return assignedTaskToMe;
    }

    public void setAssignedTaskToMe(List<TaskDTO> assignedTaskToMe) {
        this.assignedTaskToMe = assignedTaskToMe;
    }

    public List<TaskDTO> getAvailableTask() {
        return availableTask;
    }

    public void setAvailableTask(List<TaskDTO> availableTask) {
        this.availableTask = availableTask;
    }
}
