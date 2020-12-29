package com.responsywnie.tasks.repositories;

import com.responsywnie.tasks.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlRepository extends TaskRepository,JpaRepository<Task,Integer> {
}
