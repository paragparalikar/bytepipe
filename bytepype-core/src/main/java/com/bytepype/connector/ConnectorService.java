package com.bytepype.connector;

import com.bytepype.connector.exception.ConnectorNotFoundException;
import com.bytepype.connector.validator.UniqueConnector;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConnectorService {

    private final ConnectorRepository connectorRepository;

    public List<Connector<?>> findAll(){
        return connectorRepository.findAll();
    }

    public Connector<?> findById(@NotNull Long id){
        return connectorRepository.findById(id)
                .orElseThrow(() -> new ConnectorNotFoundException(id));
    }

    public Connector<?> create(@NotNull @Valid @UniqueConnector final Connector<?> connector){
        connector.setId(null);
        return connectorRepository.save(connector);
    }

    public Connector<?> update(@NotNull @Valid @UniqueConnector final Connector<?> connector){
        if(!connectorRepository.existsById(connector.getId())){
            throw new ConnectorNotFoundException(connector.getId());
        }
        return connectorRepository.save(connector);
    }

    public Connector<?> delete(@NotNull Long id){
        final Connector<?> connector = connectorRepository.findById(id)
                .orElseThrow(() -> new ConnectorNotFoundException(id));
        connectorRepository.delete(connector);
        return connector;
    }


}
