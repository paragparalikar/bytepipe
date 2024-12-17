package com.bytepipe.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("from Project p join p.users as u where u.id = :userId")
    List<Project> findByUserId(Long userId);

}
