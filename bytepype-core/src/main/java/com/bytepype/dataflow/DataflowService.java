package com.bytepype.dataflow;

import com.bytepype.dataflow.exception.DataflowNotFoundException;
import com.bytepype.dataflow.validator.UniqueDataflow;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataflowService {

    private final DataflowRepository dataflowRepository;

    public List<Dataflow> findAll(){
        return dataflowRepository.findAll();
    }

    public Dataflow findById(@NotNull final Long id){
        return dataflowRepository.findById(id)
                .orElseThrow(() -> new DataflowNotFoundException(id));
    }

    public Dataflow create(@NotNull @Valid @UniqueDataflow final Dataflow dataflow){
        dataflow.setId(null);
        return dataflowRepository.save(dataflow);
    }

    public Dataflow update(@NotNull @Valid @UniqueDataflow final Dataflow dataflow){
        if(!dataflowRepository.existsById(dataflow.getId())){
            throw new DataflowNotFoundException(dataflow.getId());
        }
        return dataflowRepository.save(dataflow);
    }

    public Dataflow delete(@NotNull final Long id){
        final Dataflow dataflow = dataflowRepository.findById(id)
                .orElseThrow(() -> new DataflowNotFoundException(id));
        dataflowRepository.delete(dataflow);
        return dataflow;
    }

}
