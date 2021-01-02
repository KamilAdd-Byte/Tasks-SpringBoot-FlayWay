package com.responsywnie.tasks.repositories;

import com.responsywnie.tasks.model.Project;
import com.responsywnie.tasks.model.TaskGroup;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {
    List<Project>findAll();
    Optional<Project> findById(Integer id);
    Project save (Project entity);

}
