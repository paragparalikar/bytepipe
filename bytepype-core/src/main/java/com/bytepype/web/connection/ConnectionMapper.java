package com.bytepype.web.connection;

import com.bytepype.connection.Connection;
import com.bytepype.connection.oracle.OracleConnection;
import com.bytepype.web.connection.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.SubclassMapping;

import java.util.List;

import static org.mapstruct.SubclassExhaustiveStrategy.RUNTIME_EXCEPTION;

@Mapper(componentModel = "spring", subclassExhaustiveStrategy = RUNTIME_EXCEPTION)
public interface ConnectionMapper {

    @SubclassMapping(target = OracleConnectionDTO.class, source = OracleConnection.class)
    ConnectionDTO toConnectionDTO(Connection<?> connection);

    OracleConnectionDTO toOracleConnectionDTO(OracleConnection oracleConnection);

    @SubclassMapping(target = OracleConnection.class, source = CreateOracleConnectionRequestDTO.class)
    Connection<?> toConnection(CreateConnectionRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target="createdBy", ignore = true)
    @Mapping(target="createdDate", ignore = true)
    @Mapping(target="lastModifiedBy", ignore = true)
    @Mapping(target="lastModifiedDate", ignore = true)
    OracleConnection toOracleConnection(CreateOracleConnectionRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target="createdBy", ignore = true)
    @Mapping(target="createdDate", ignore = true)
    @Mapping(target="lastModifiedBy", ignore = true)
    @Mapping(target="lastModifiedDate", ignore = true)
    @SubclassMapping(target = OracleConnection.class, source = UpdateOracleConnectionRequestDTO.class)
    Connection<?> toConnection(UpdateConnectionRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target="createdBy", ignore = true)
    @Mapping(target="createdDate", ignore = true)
    @Mapping(target="lastModifiedBy", ignore = true)
    @Mapping(target="lastModifiedDate", ignore = true)
    OracleConnection toOracleConnection(UpdateOracleConnectionRequestDTO dto);

    List<ConnectionDTO> toConnectionDTOs(List<Connection<?>> all);
}
