package com.plannerapp.service;

import com.plannerapp.model.bindingModel.TaskAddBindingModel;
import com.plannerapp.model.view.TaskHomeViewModel;

public interface TaskService {

    void addTask(TaskAddBindingModel taskAddBindingModel);

    TaskHomeViewModel getHomeViewInfo();

    void assign(Long id, String username);

    void remove(Long id);
}
