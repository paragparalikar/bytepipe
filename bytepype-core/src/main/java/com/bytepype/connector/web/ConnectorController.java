package com.bytepype.connector.web;

import com.bytepype.connector.Connector;
import com.bytepype.connector.ConnectorService;
import com.bytepype.connector.web.dto.ConnectorDTO;
import com.bytepype.connector.web.dto.CreateConnectorRequestDTO;
import com.bytepype.connector.web.dto.UpdateConnectorRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/connectors")
public class ConnectorController {

    private final ConnectorMapper connectorMapper;
    private final ConnectorService connectorService;

    @GetMapping
    public List<ConnectorDTO> findAll(){
        return connectorMapper.toConnectorDTOs(connectorService.findAll());
    }

    @GetMapping("/{id}")
    public ConnectorDTO findById(@PathVariable("id") @NotNull final Long id){
        final Connector<?> connector = connectorService.findById(id);
        return connectorMapper.toConnectorDTO(connector);
    }

    @PostMapping
    public ConnectorDTO create(@NotNull @Valid @RequestBody final CreateConnectorRequestDTO dto){
        final Connector<?> connector = connectorMapper.toConnector(dto);
        final Connector<?> managedConnector = connectorService.create(connector);
        return connectorMapper.toConnectorDTO(managedConnector);
    }

    @PutMapping("/{id}")
    public ConnectorDTO update(
            @PathVariable("id") @NotNull final Long id,
            @NotNull @Valid @RequestBody final UpdateConnectorRequestDTO dto){
        final Connector<?> connector = connectorMapper.toConnector(dto);
        connector.setId(id);
        final Connector<?> managedConnector = connectorService.update(connector);
        return connectorMapper.toConnectorDTO(managedConnector);
    }

    @DeleteMapping("/{id}")
    public ConnectorDTO delete(@PathVariable("id") @NotNull final Long id){
        final Connector<?> connector = connectorService.delete(id);
        return connectorMapper.toConnectorDTO(connector);
    }

}
