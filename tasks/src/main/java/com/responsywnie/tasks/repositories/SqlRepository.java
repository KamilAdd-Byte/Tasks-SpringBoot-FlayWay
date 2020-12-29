package com.responsywnie.tasks.repositories;

import com.responsywnie.tasks.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlRepository extends TaskRepository,JpaRepository<Tasks,Integer> {
}
