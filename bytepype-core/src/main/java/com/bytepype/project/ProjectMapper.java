package com.bytepype.project;

import com.bytepype.project.dto.CreateProjectRequestDTO;
import com.bytepype.project.dto.ProjectResponseDTO;
import com.bytepype.project.dto.UpdateProjectRequestDTO;
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
