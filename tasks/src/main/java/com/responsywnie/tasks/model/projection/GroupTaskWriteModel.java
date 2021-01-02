package com.responsywnie.tasks.model.projection;

import com.responsywnie.tasks.model.Task;
import java.time.LocalDateTime;

public class GroupTaskWriteModel {
    private String description;
    private LocalDateTime deadline;

    private String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private LocalDateTime getDeadline() {
        return deadline;
    }

    private void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public Task toTask(){
         return new Task(description,deadline);
    }
}
