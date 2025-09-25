package com.bytepype.web.source;

import com.bytepype.source.Source;
import com.bytepype.web.source.dto.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SourceMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target="createdBy", ignore = true)
    @Mapping(target="createdDate", ignore = true)
    @Mapping(target="lastModifiedBy", ignore = true)
    @Mapping(target="lastModifiedDate", ignore = true)
    Source toSource(CreateSourceRequestDTO requestDTO);

    @Mapping(target="createdBy", ignore = true)
    @Mapping(target="createdDate", ignore = true)
    @Mapping(target="lastModifiedBy", ignore = true)
    @Mapping(target="lastModifiedDate", ignore = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "enabled", source = "dto.enabled")
    @Mapping(target = "description", source = "dto.description")
    Source toSource(Long id, UpdateSourceRequestDTO dto);

    CreateSourceResponseDTO toResponseDTO(Source source);

    SourceDetailsDTO toDetailsDTO(Source source);

    SourceDTO toDTO(Source source);

    List<SourceDTO> toDTOs(List<Source> sources);

}
