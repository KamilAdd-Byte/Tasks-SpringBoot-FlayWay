package com.responsywnie.tasks.repositories;

import com.responsywnie.tasks.model.Tasks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    List<Tasks> findAll();
    Page<Tasks> findAll(Pageable pageable);
    List<Tasks> findByDone(@Param("state") boolean done);
    boolean existsById(Integer id);
    Optional<Tasks>findById(Integer id);
    Tasks save(Tasks entity);
}
