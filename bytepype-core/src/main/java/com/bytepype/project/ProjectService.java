package com.bytepype.project;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public List<Project> findByUserId(Long userId){
        return projectRepository.findByUserId(userId);
    }

    public Project findById(@NonNull Long id) throws ProjectNotFoundException {
        return projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException(id));
    }


    public Project save(@NotNull @Valid Project project) {
        return projectRepository.save(project);
    }

    public void deleteById(@NotNull Long id) {
        projectRepository.deleteById(id);
    }
}
