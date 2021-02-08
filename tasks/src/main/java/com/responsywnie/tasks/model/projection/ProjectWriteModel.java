package com.responsywnie.tasks.model.projection;

import com.responsywnie.tasks.model.Project;
import com.responsywnie.tasks.model.ProjectStep;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;

public class ProjectWriteModel {
    @NotBlank(message = "Project step's description must not be empty")
    private String description;

    private List<ProjectStep> steps;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private List<ProjectStep> getSteps() {
        return steps;
    }

    private void setSteps(List<ProjectStep> steps) {
        this.steps = steps;
    }

    public Project toProject(){
        var result = new Project();
        result.setDescription(description);
        steps.forEach(step -> step.setProject(result));
        result.setSteps(new HashSet<>(steps));
        return result;
    }
}