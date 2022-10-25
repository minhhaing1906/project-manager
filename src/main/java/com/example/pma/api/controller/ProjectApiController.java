package com.example.pma.api.controller;

import com.example.pma.entities.Project;
import com.example.pma.services.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/app-api/projects")
public class ProjectApiController {
    private final ProjectService projectService;
    @Autowired
    public ProjectApiController(ProjectService projectService) {
        this.projectService = projectService;
    }
    @GetMapping
    public List<Project> getProjects() {
        return projectService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Project> getProject(@PathVariable Long id) {
        return projectService.findById(id);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Project createProject(@RequestBody @Valid Project project) {
        return projectService.save(project);
    }

    @PutMapping("/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody @Valid Project project) {
        return projectService.updateProject(id, project);
    }

    @PatchMapping("/{id}")
    public Project partialUpdateProject(@PathVariable Long id, @RequestBody @Valid Project patchProject) {
        Project project = projectService.findById(id).get();

        if (patchProject.getName() != null)
            project.setName(patchProject.getName());

        if (patchProject.getStage() != null)
            project.setStage(patchProject.getStage());

        if (patchProject.getDescription() != null)
            project.setDescription(patchProject.getDescription());

        return projectService.save(project);
    }

    @DeleteMapping("{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteById(id);
    }
}
