package com.plannerapp.service.impl;

import com.plannerapp.model.LoggedUser;
import com.plannerapp.model.bindingModel.TaskAddBindingModel;
import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;
import com.plannerapp.model.view.TaskDTO;
import com.plannerapp.model.view.TaskHomeViewModel;
import com.plannerapp.repo.PriorityRepository;
import com.plannerapp.repo.TaskRepository;
import com.plannerapp.repo.UserRepository;
import com.plannerapp.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final PriorityRepository priorityRepository;
    private final LoggedUser loggedUser;
    private final ModelMapper modelMapper;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository, PriorityRepository priorityRepository, LoggedUser loggedUser, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.priorityRepository = priorityRepository;
        this.loggedUser = loggedUser;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addTask(TaskAddBindingModel taskAddBindingModel) {

        Priority priority = priorityRepository.findByPriorityName(taskAddBindingModel.getPriority());

        Task task = modelMapper.map(taskAddBindingModel, Task.class);
        task.setPriority(priority);

        taskRepository.save(task);
    }

    @Override
    public TaskHomeViewModel getHomeViewInfo() {

        User user = userRepository.findByUsername(loggedUser.getUsername());

        List<TaskDTO> availableTask = taskRepository.findAllAvailable()
                .stream()
                .map(TaskDTO::createFromTask)
                .toList();
        List<TaskDTO> assignedTaskToMe = taskRepository.findByUser(user)
                .stream()
                .map(TaskDTO::createFromTask)
                .toList();
        return new TaskHomeViewModel(assignedTaskToMe, availableTask);
    }

    @Override
    public void assign(Long id, String username) {
        Optional<Task> findTask = taskRepository.findById(id);

        if (findTask.isPresent()) {
            Task task = findTask.get();

            if (username == null) {
                task.setUser(null);
            } else {
                User user = userRepository.findByUsername(username);
                task.setUser(user);
            }

            taskRepository.save(task);
        }

    }

    @Override
    public void remove(Long id) {
        taskRepository.deleteById(id);
    }


}