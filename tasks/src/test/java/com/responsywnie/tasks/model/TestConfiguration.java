package com.responsywnie.tasks.model;

import com.responsywnie.tasks.repositories.TaskRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.*;

@Configuration
public class TestConfiguration {

    @Bean
    @Profile("integration")
    TaskRepository testRepo() {
        return new TaskRepository() {
            private Map<Integer, Task> taskMap = new HashMap<>();

            @Override
            public List<Task> findAll() {
                return new ArrayList<>(taskMap.values());
            }

            @Override
            public Page<Task> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public List<Task> findByDone(boolean done) {
                return null;
            }

            @Override
            public boolean existsById(Integer id) {
                return false;
            }

            @Override
            public boolean existsByDoneIsFalseAndGroup_Id(Integer groupId) {
                return false;
            }

            @Override
            public Optional<Task> findById(Integer id) {
                return Optional.ofNullable(taskMap.get(id));
            }

            @Override
            public Task save(Task entity) {
                return taskMap.put(taskMap.size() + 1, entity);
            }
        };
    }
}
