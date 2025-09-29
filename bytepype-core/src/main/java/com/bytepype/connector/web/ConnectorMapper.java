package com.bytepype.connector.web;

import com.bytepype.connector.Connector;
import com.bytepype.connector.ConnectorService;
import com.bytepype.connector.oracle.OracleConnector;
import com.bytepype.connector.web.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.SubclassMapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.mapstruct.SubclassExhaustiveStrategy.RUNTIME_EXCEPTION;

@Mapper(componentModel = "spring", subclassExhaustiveStrategy = RUNTIME_EXCEPTION)
public abstract class ConnectorMapper {

    @Autowired private ConnectorService connectorService;

    @Mapping(target = "createdDate", expression = "java(com.bytepype.common.util.Dates.toString(connector.getCreatedDate()))")
    @Mapping(target = "lastModifiedDate", expression = "java(com.bytepype.common.util.Dates.toString(connector.getLastModifiedDate()))")
    @SubclassMapping(target = OracleConnectorDTO.class, source = OracleConnector.class)
    public abstract ConnectorDTO toConnectorDTO(Connector<?> connector);

    @Mapping(target = "type", constant = "ORACLE")
    public abstract OracleConnectorDTO toOracleConnectorDTO(OracleConnector oracleConnection);

    @SubclassMapping(target = OracleConnector.class, source = CreateOracleConnectorRequestDTO.class)
    public abstract Connector<?> toConnector(CreateConnectorRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target="createdBy", ignore = true)
    @Mapping(target="createdDate", ignore = true)
    @Mapping(target="lastModifiedBy", ignore = true)
    @Mapping(target="lastModifiedDate", ignore = true)
    public abstract OracleConnector toOracleConnector(CreateOracleConnectorRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target="createdBy", ignore = true)
    @Mapping(target="createdDate", ignore = true)
    @Mapping(target="lastModifiedBy", ignore = true)
    @Mapping(target="lastModifiedDate", ignore = true)
    @SubclassMapping(target = OracleConnector.class, source = UpdateOracleConnectorRequestDTO.class)
    public abstract Connector<?> toConnector(UpdateConnectorRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target="createdBy", ignore = true)
    @Mapping(target="createdDate", ignore = true)
    @Mapping(target="lastModifiedBy", ignore = true)
    @Mapping(target="lastModifiedDate", ignore = true)
    public abstract OracleConnector toOracleConnector(UpdateOracleConnectorRequestDTO dto);

    public abstract List<ConnectorDTO> toConnectorDTOs(List<Connector<?>> connectors);

    public Connector<?> toConnector(Long id){
        return connectorService.findById(id);
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    public abstract void copy(@MappingTarget Connector<?> connector, Connector<?> source);
}
