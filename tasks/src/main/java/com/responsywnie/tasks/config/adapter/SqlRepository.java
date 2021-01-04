package com.responsywnie.tasks.config.adapter;

import com.responsywnie.tasks.model.Task;
import com.responsywnie.tasks.repositories.TaskRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlRepository extends TaskRepository,JpaRepository<Task,Integer> {
    @Override
    @Query(nativeQuery = true,value = "select count(*) > 0 from tasks where id=:id")
    boolean existsById(@Param("id") Integer id);

}
