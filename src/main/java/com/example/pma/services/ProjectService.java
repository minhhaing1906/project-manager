package com.example.pma.services;

import com.example.pma.dao.ProjectRepository;
import com.example.pma.dto.CountStage;
import com.example.pma.dto.ProjectDate;
import com.example.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectRepository proRepo;

    @Autowired
    public ProjectService(ProjectRepository proRepo) {
        this.proRepo = proRepo;
    }

    public Project save(Project project) {
        return proRepo.save(project);
    }

    public Project updateProject(Long id, Project project) {
        project.setProjectId(id);
        return proRepo.save(project);
    }
    public List<Project> getAll() {
        return proRepo.findAll();
    }

    public List<CountStage> getProjectStatus() {
        return proRepo.getProjectStatus();
    }

    public Optional<Project> findById(Long id) {
        return proRepo.findById(id);
    }

    public void deleteById(Long id) {
        try {
            proRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
    }

    public void delete(Project project) {
        proRepo.delete(project);
    }

    public List<ProjectDate> projectDates() {
        return proRepo.getProjectDate();
    }
}
