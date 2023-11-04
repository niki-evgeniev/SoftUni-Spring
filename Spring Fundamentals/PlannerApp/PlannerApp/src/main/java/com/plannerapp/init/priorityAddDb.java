package com.plannerapp.init;

import com.plannerapp.repo.PriorityRepository;
import com.plannerapp.service.PriorityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class priorityAddDb implements CommandLineRunner {

    private final PriorityService priorityService;


    public priorityAddDb(PriorityService priorityService) {

        this.priorityService = priorityService;
    }


    @Override
    public void run(String... args) {

        priorityService.addDescriptionToDb();

    }
}
