package com.responsywnie.tasks.controller;

import com.responsywnie.tasks.logic.TaskGroupService;
import com.responsywnie.tasks.model.Task;
import com.responsywnie.tasks.model.projection.GroupReadModel;
import com.responsywnie.tasks.model.projection.GroupWriteModel;
import com.responsywnie.tasks.repositories.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/groups")
public class TaskGroupController {

    private static final Logger logger = LoggerFactory.getLogger(TaskGroupController.class);

    private final TaskRepository repository;
    private final TaskGroupService service;

    public TaskGroupController(final TaskRepository repository, final TaskGroupService service) {
        this.repository = repository;
        this.service = service;
    }
    @PostMapping
    ResponseEntity<GroupReadModel> createGroup(@RequestBody @Valid GroupWriteModel toCreate){
        return ResponseEntity.created(URI.create("/")).body(service.createGroup(toCreate));
    }
    @GetMapping
    ResponseEntity<List<GroupReadModel>> readAllGroups(Pageable page) {
        return ResponseEntity.ok(service.readAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<List<Task>> readAllTasksFromGroup(@PathVariable int id){
        return ResponseEntity.ok(repository.findAllByGroup_Id(id));
    }
    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<?> toggleTask(@PathVariable int id){
        service.toggleGroup(id);
        return ResponseEntity.noContent().build();
    }
  }
