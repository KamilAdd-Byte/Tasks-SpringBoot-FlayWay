package com.responsywnie.tasks.config.adapter;

import com.responsywnie.tasks.model.Project;
import com.responsywnie.tasks.model.TaskGroup;
import com.responsywnie.tasks.repositories.ProjectRepository;
import com.responsywnie.tasks.repositories.TaskGroupRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SqlProjectRepository extends ProjectRepository,JpaRepository<Project,Integer> {
    @Override
    @Query("select distinct p from Project p join fetch p.steps")
    List<Project> findAll();


}
