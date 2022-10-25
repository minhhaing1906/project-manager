package com.example.pma.dao;

import com.example.pma.dto.CountStage;
import com.example.pma.dto.ProjectDate;
import com.example.pma.entities.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {
    @Override
    List<Project> findAll();

    @Query(nativeQuery = true,
            value = "SELECT p.stage, COUNT(p.stage) as stageCount " +
                    "FROM project AS p " +
                    "GROUP BY p.stage")
    List<CountStage> getProjectStatus();

    // Query begin date, end date and project name
    @Query(nativeQuery = true,
    value = "SELECT p.name AS projectName, p.start_date AS startDate, p.end_date AS endDate FROM project AS p WHERE p.start_date IS NOT NULL")
    List<ProjectDate> getProjectDate();
}

