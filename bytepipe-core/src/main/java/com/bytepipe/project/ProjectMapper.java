package com.bytepipe.project;

import com.bytepipe.project.dto.CreateProjectRequestDTO;
import com.bytepipe.project.dto.ProjectResponseDTO;
import com.bytepipe.project.dto.UpdateProjectRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    Project toEntity(CreateProjectRequestDTO dto);

    void update(@MappingTarget Project project, UpdateProjectRequestDTO dto);

    ProjectResponseDTO toDTO(Project entity);

    List<ProjectResponseDTO> toDTOs(Collection<Project> projects);

}
