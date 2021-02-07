package com.responsywnie.tasks.logic;

import com.responsywnie.tasks.model.TaskGroup;
import com.responsywnie.tasks.model.projection.GroupReadModel;
import com.responsywnie.tasks.model.projection.GroupWriteModel;
import com.responsywnie.tasks.repositories.TaskGroupRepository;
import com.responsywnie.tasks.repositories.TaskRepository;
import java.util.List;
import java.util.stream.Collectors;

public class TaskGroupService {
    private TaskGroupRepository repository;
    private TaskRepository taskRepository;

    public TaskGroupService(final TaskGroupRepository repository,final TaskRepository taskRepository) {
        this.repository = repository;
        this.taskRepository = taskRepository;
    }
    public GroupReadModel createGroup(final GroupWriteModel source){
        TaskGroup result = repository.save(source.toGroup());
        return new GroupReadModel(result);
    }
    public List<GroupReadModel>readAll(){
        return repository.findAll().stream()
                .map(GroupReadModel::new)
                .collect(Collectors.toList());
    }
    public void toggleGroup(int groupId){
        if (taskRepository.existsByDoneIsFalseAndGroup_Id(groupId)){
            throw new IllegalStateException("Group has undone tasks. Done all the tasks first");
        }
        TaskGroup result = repository.findById(groupId)
                .orElseThrow(()-> new IllegalArgumentException("TaskGroup with given id not found"));
        result.setDone(!result.isDone());
        repository.save(result);
    }
}
