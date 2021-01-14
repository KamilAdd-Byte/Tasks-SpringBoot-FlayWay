package com.responsywnie.tasks.logic;

import com.responsywnie.tasks.config.TasksConfigurationProperties;
import com.responsywnie.tasks.model.Project;
import com.responsywnie.tasks.model.Task;
import com.responsywnie.tasks.model.TaskGroup;
import com.responsywnie.tasks.model.projection.GroupReadModel;
import com.responsywnie.tasks.repositories.ProjectRepository;
import com.responsywnie.tasks.repositories.TaskGroupRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectService {
    private ProjectRepository repository;
    private TaskGroupRepository taskGroupRepository;
    private TasksConfigurationProperties config;

    public ProjectService(ProjectRepository repository, TaskGroupRepository taskGroupRepository,
                           TasksConfigurationProperties config) {
        this.repository = repository;
        this.taskGroupRepository = taskGroupRepository;
        this.config = config;
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
        TaskGroup result = repository.findById(projectId)
                .map(project -> {
                    var targetGroup = new TaskGroup();
                    targetGroup.setDescription(project.getDescription());
                    targetGroup.setTasks(
                            project.getSteps().stream()
                            .map(projectStep -> new Task(
                                    projectStep.getDescription(),
                                    deadline.plusDays(projectStep.getDaysToDeadline())
                            )).collect(Collectors.toSet())
                    );
                    targetGroup.setProject(project);
                    return taskGroupRepository.save(targetGroup);
                }).orElseThrow(()-> new IllegalArgumentException("Project with given id not found"));
        return new GroupReadModel(result);
    }
}
