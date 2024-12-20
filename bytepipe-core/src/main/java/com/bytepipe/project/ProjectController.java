package com.bytepipe.project;

import com.bytepipe.project.dto.CreateProjectRequestDTO;
import com.bytepipe.project.dto.ProjectResponseDTO;
import com.bytepipe.project.dto.UpdateProjectRequestDTO;
import com.bytepipe.user.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectMapper projectMapper;
    private final ProjectService projectService;

    @GetMapping
    public List<ProjectResponseDTO> findAll(@AuthenticationPrincipal(errorOnInvalidType = true) UserDetails user){
        System.out.println(user);
        System.out.println(user.getClass().getCanonicalName());
        final List<Project> projects = projectService.findByUserId(0L);
        return projectMapper.toDTOs(projects);
    }

    @PostMapping
    public ProjectResponseDTO create(@RequestBody @NotNull @Valid CreateProjectRequestDTO dto){
        final Project project = projectMapper.toEntity(dto);
        final Project managedProject = projectService.save(project);
        return projectMapper.toDTO(managedProject);
    }

    @PutMapping("/{id}")
    public ProjectResponseDTO update(@PathVariable @NotNull Long id, @RequestBody @NotNull @Valid UpdateProjectRequestDTO dto) throws ProjectNotFoundException {
        final Project project = projectService.findById(id);
        projectMapper.update(project, dto);
        projectService.save(project);
        return projectMapper.toDTO(project);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @NotNull Long id) {
        projectService.deleteById(id);
    }

}
