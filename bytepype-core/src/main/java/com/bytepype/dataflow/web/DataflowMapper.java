package com.bytepype.dataflow.web;

import com.bytepype.connector.web.ConnectorMapper;
import com.bytepype.dataflow.Dataflow;
import com.bytepype.dataflow.web.dto.CreateDataflowRequestDTO;
import com.bytepype.dataflow.web.dto.DataflowDTO;
import com.bytepype.dataflow.web.dto.UpdateDataflowRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = ConnectorMapper.class)
public interface DataflowMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "source", source = "sourceId")
    @Mapping(target = "destination", source = "destinationId")
    Dataflow toDataflow(CreateDataflowRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "source", source = "sourceId")
    @Mapping(target = "destination", source = "destinationId")
    Dataflow toDataflow(UpdateDataflowRequestDTO dto);

    @Mapping(target = "sourceId", source = "source.id")
    @Mapping(target = "destinationId", source = "destination.id")
    DataflowDTO toDataflowDTO(Dataflow dataflow);

    List<DataflowDTO> toDataflowDTOs(List<Dataflow> dataflows);

}
