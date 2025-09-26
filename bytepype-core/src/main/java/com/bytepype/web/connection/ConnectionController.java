package com.bytepype.web.connection;

import com.bytepype.connection.Connection;
import com.bytepype.connection.ConnectionService;
import com.bytepype.web.connection.dto.ConnectionDTO;
import com.bytepype.web.connection.dto.CreateConnectionRequestDTO;
import com.bytepype.web.connection.dto.UpdateConnectionRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/connections")
public class ConnectionController {

    private final ConnectionMapper connectionMapper;
    private final ConnectionService connectionService;

    @GetMapping
    public List<ConnectionDTO> findAll(){
        return connectionMapper.toConnectionDTOs(connectionService.findAll());
    }

    @GetMapping("/{id}")
    public ConnectionDTO findById(@PathVariable("id") @NotNull final Long id){
        final Connection<?> connection = connectionService.findById(id);
        return connectionMapper.toConnectionDTO(connection);
    }

    @PostMapping
    public ConnectionDTO create(@NotNull @Valid @RequestBody final CreateConnectionRequestDTO dto){
        final Connection<?> connection = connectionMapper.toConnection(dto);
        final Connection<?> managedConnection = connectionService.create(connection);
        return connectionMapper.toConnectionDTO(managedConnection);
    }

    @PutMapping("/{id}")
    public ConnectionDTO update(
            @PathVariable("id") @NotNull final Long id,
            @NotNull @Valid @RequestBody final UpdateConnectionRequestDTO dto){
        final Connection<?> connection = connectionMapper.toConnection(dto);
        connection.setId(id);
        final Connection<?> managedConnection = connectionService.update(connection);
        return connectionMapper.toConnectionDTO(managedConnection);
    }

    @DeleteMapping("/{id}")
    public ConnectionDTO delete(@PathVariable("id") @NotNull final Long id){
        final Connection<?> connection = connectionService.delete(id);
        return connectionMapper.toConnectionDTO(connection);
    }

}
