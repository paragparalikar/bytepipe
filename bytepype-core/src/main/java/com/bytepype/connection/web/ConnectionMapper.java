package com.bytepype.connection.web;

import com.bytepype.connection.Connection;
import com.bytepype.connection.ConnectionService;
import com.bytepype.connection.oracle.OracleConnection;
import com.bytepype.connection.web.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.SubclassMapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.mapstruct.SubclassExhaustiveStrategy.RUNTIME_EXCEPTION;

@Mapper(componentModel = "spring", subclassExhaustiveStrategy = RUNTIME_EXCEPTION)
public abstract class ConnectionMapper {

    @Autowired private ConnectionService connectionService;

    @SubclassMapping(target = OracleConnectionDTO.class, source = OracleConnection.class)
    public abstract ConnectionDTO toConnectionDTO(Connection<?> connection);

    public abstract OracleConnectionDTO toOracleConnectionDTO(OracleConnection oracleConnection);

    @SubclassMapping(target = OracleConnection.class, source = CreateOracleConnectionRequestDTO.class)
    public abstract Connection<?> toConnection(CreateConnectionRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target="createdBy", ignore = true)
    @Mapping(target="createdDate", ignore = true)
    @Mapping(target="lastModifiedBy", ignore = true)
    @Mapping(target="lastModifiedDate", ignore = true)
    public abstract OracleConnection toOracleConnection(CreateOracleConnectionRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target="createdBy", ignore = true)
    @Mapping(target="createdDate", ignore = true)
    @Mapping(target="lastModifiedBy", ignore = true)
    @Mapping(target="lastModifiedDate", ignore = true)
    @SubclassMapping(target = OracleConnection.class, source = UpdateOracleConnectionRequestDTO.class)
    public abstract Connection<?> toConnection(UpdateConnectionRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target="createdBy", ignore = true)
    @Mapping(target="createdDate", ignore = true)
    @Mapping(target="lastModifiedBy", ignore = true)
    @Mapping(target="lastModifiedDate", ignore = true)
    public abstract OracleConnection toOracleConnection(UpdateOracleConnectionRequestDTO dto);

    public abstract List<ConnectionDTO> toConnectionDTOs(List<Connection<?>> connections);

    public Connection<?> toConnection(Long id){
        return connectionService.findById(id);
    }
}
