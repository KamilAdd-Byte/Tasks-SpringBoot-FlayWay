package com.responsywnie.tasks.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Tasks description must be not null and not empty")
    private String description;
    private boolean done;

    public Tasks() {
    }


    private int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    private String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private boolean isDone() {
        return done;
    }

    private void setDone(boolean done) {
        this.done = done;
    }
//    public void updateForm(final Tasks source){
//        description = source.description;
//        done = source.done;
//        deadline = source.deadline;
//    }
}
