package com.responsywnie.tasks.logic;

import com.responsywnie.tasks.config.TasksConfigurationProperties;
import com.responsywnie.tasks.model.Project;
import com.responsywnie.tasks.model.projection.GroupReadModel;
import com.responsywnie.tasks.model.projection.GroupTaskWriteModel;
import com.responsywnie.tasks.model.projection.GroupWriteModel;
import com.responsywnie.tasks.repositories.ProjectRepository;
import com.responsywnie.tasks.repositories.TaskGroupRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectService {
    private ProjectRepository repository;
    private TaskGroupRepository taskGroupRepository;
    private TaskGroupService taskGroupService;
    private TasksConfigurationProperties config;


    public ProjectService(ProjectRepository repository, TaskGroupRepository taskGroupRepository,
                          TaskGroupService service, TasksConfigurationProperties config) {
        this.repository = repository;
        this.taskGroupRepository = taskGroupRepository;
        this.config = config;
        this.taskGroupService = service;
    }

    public List<Project> readAll(){
        return repository.findAll();
    }
    public Project save(final Project toSave){
        return repository.save(toSave);
    }

    public GroupReadModel createGroup(LocalDateTime deadline,int projectId){
        if (!config.getTemplate().isAllowMultipleTasks() && taskGroupRepository.existsByDoneIsFalseAndProject_Id(projectId)){
            throw new IllegalStateException("Only one undone group from project is allowed");
        }
        GroupReadModel result = repository.findById(projectId)
                .map(project -> {
                    var targetGroup = new GroupWriteModel();
                    targetGroup.setDescription(project.getDescription());
                    targetGroup.setTasks(
                            project.getSteps().stream()
                                   .map(projectStep -> {
                                               var task = new GroupTaskWriteModel();
                                               task.setDescription(projectStep.getDescription());
                                               task.setDeadline(deadline.plusDays(projectStep.getDaysToDeadline()));
                                               return  task;
                                         }
                                   ).collect(Collectors.toSet())
                    );
                    return taskGroupService.createGroup(targetGroup);
                }).orElseThrow(()-> new IllegalArgumentException("Project with given id not found"));
        return result;
    }
}
