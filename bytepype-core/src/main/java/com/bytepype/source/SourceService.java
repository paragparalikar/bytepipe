package com.bytepype.source;

import com.bytepype.source.exception.SourceExistsException;
import com.bytepype.source.exception.SourceNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SourceService {

    private final SourceRepository sourceRepository;

    public List<Source> findAll(){
         return sourceRepository.findAll();
    }

    public Source findById(@NotNull @Positive Long id){
        return sourceRepository.findById(id)
                .orElseThrow(() -> new SourceNotFoundException(id));
    }

    public Source create(@Valid @NotNull Source source) {
        if(sourceRepository.existsByNameIgnoreCase(source.getName())){
            throw new SourceExistsException(source.getName());
        }
        return sourceRepository.save(source);
    }

    public Source update(@Valid @NotNull Source source){
        if(sourceRepository.existsById(source.getId())){
            throw new SourceNotFoundException(source.getId());
        }
        return sourceRepository.save(source);
    }

    public Source deleteById(@NotNull @Positive Long id){
        final Source source = sourceRepository.findById(id)
                .orElseThrow(() -> new SourceNotFoundException(id));
        sourceRepository.deleteById(id);
        return source;
    }

}
