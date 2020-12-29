package com.responsywnie.tasks.controller;

import com.responsywnie.tasks.model.Tasks;
import com.responsywnie.tasks.repositories.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    private final TaskRepository repository;

    public TaskController(final TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/tasks", params = {"!sort", "!page", "!size"})
    ResponseEntity<List<Tasks>> readAllTasks() {
        logger.warn("Exposing all the task!");
        return ResponseEntity.ok(repository.findAll());
    }
    @GetMapping("/tasks")
    ResponseEntity<Tasks> readTask(@PathVariable int id){
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/tasks")
    ResponseEntity<Tasks> createTask(@RequestBody @Valid Tasks toCreate){
        Tasks result = repository.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.toString())).body(result);
    }

    @PutMapping("/tasks/{id}")
    ResponseEntity<?> updateTask(@PathVariable int id, @RequestBody @Valid Tasks toUpdate){
        if (!repository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        repository.findById(id)
                .ifPresent(repository::save);

        return ResponseEntity.noContent().build();
    }
//    @Transactional
//    @PatchMapping("/tasks/{id}")
//    public ResponseEntity<?> toggleTasks(@PathVariable int id){
//        if (!repository.existsById(id)){
//            return ResponseEntity.notFound().build();
//        }
//        repository.findById(id)
//                .ifPresent(task -> tasks.setDone(!tasks.isDone()));
//        return ResponseEntity.noContent().build();
    }
