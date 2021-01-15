package com.responsywnie.tasks.logic;

import com.responsywnie.tasks.config.TasksConfigurationProperties;
import com.responsywnie.tasks.repositories.ProjectRepository;
import com.responsywnie.tasks.repositories.TaskGroupRepository;
import com.responsywnie.tasks.repositories.TaskRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
public class LogicConfiguration {

    @Bean
    ProjectService projectService(
            final ProjectRepository projectRepository,
            final TaskGroupRepository taskGroupRepository,
            final TasksConfigurationProperties config
    ){
        return new ProjectService(projectRepository,taskGroupRepository,config);
    }
    @Bean
    TaskGroupService taskGroupService(
            final TaskGroupRepository taskGroupRepository,
            final TaskRepository taskRepository
            ){
        return new TaskGroupService(taskGroupRepository,taskRepository);
    }
}
