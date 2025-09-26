package com.bytepype.connection;

import com.bytepype.connection.exception.ConnectionNotFoundException;
import com.bytepype.connection.validator.UniqueConnection;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConnectionService {

    private final ConnectionRepository connectionRepository;

    public List<Connection<?>> findAll(){
        return connectionRepository.findAll();
    }

    public Connection<?> findById(@NotNull Long id){
        return connectionRepository.findById(id)
                .orElseThrow(() -> new ConnectionNotFoundException(id));
    }

    public Connection<?> create(@NotNull @Valid @UniqueConnection final Connection<?> connection){
        connection.setId(null);
        return connectionRepository.save(connection);
    }

    public Connection<?> update(@NotNull @Valid @UniqueConnection final Connection<?> connection){
        if(!connectionRepository.existsById(connection.getId())){
            throw new ConnectionNotFoundException(connection.getId());
        }
        return connectionRepository.save(connection);
    }

    public Connection<?> delete(@NotNull Long id){
        final Connection<?> connection = connectionRepository.findById(id)
                .orElseThrow(() -> new ConnectionNotFoundException(id));
        connectionRepository.delete(connection);
        return connection;
    }


}
