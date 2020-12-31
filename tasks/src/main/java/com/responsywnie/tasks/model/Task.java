package com.responsywnie.tasks.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


@Entity
@Table(name = "tasks")
public class Task{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Task description must be not null and not empty")
    private String description;
    private boolean done;
    private LocalDateTime deadline;
    //new Audit() wywołanie
    @Embedded
    private Audit audit = new Audit();
    @ManyToOne
    @JoinColumn(name = "task_group_id")
    private TaskGroup group;

    public Task() {
    }
    private LocalDateTime getDeadline() {
        return deadline;
    }
    private void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
    public int getId() {
        return id;
    }
    private void setId(int id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isDone() {
        return done;
    }
    public void setDone(boolean done) {
        this.done = done;
    }
    private TaskGroup getGroups() {
        return group;
    }
    private void setGroups(TaskGroup groups) {
        this.group = groups;
    }
    public void updateForm(final Task source){
        description = source.description;
        done = source.done;
        deadline = source.deadline;
        group = source.group;
    }

}
