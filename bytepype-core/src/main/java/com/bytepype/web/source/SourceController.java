package com.bytepype.web.source;

import com.bytepype.source.Source;
import com.bytepype.source.SourceService;
import com.bytepype.web.source.dto.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sources")
public class SourceController {

    private final SourceMapper sourceMapper;
    private final SourceService sourceService;

    @GetMapping
    public List<SourceDTO> findAll(){
        return sourceMapper.toDTOs(sourceService.findAll());
    }

    @GetMapping("/{id}")
    public SourceDetailsDTO findById(@PathVariable @Positive Long id){
        return sourceMapper.toDetailsDTO(sourceService.findById(id));
    }

    @PostMapping
    public CreateSourceResponseDTO create(@Valid @NotNull final CreateSourceRequestDTO requestDTO){
        final Source source = sourceMapper.toSource(requestDTO);
        final Source managedSource = sourceService.create(source);
        return sourceMapper.toResponseDTO(managedSource);
    }

    @PutMapping("/{id}")
    public SourceDetailsDTO update(
            @PathVariable @Positive Long id,
            @Valid @NotNull final UpdateSourceRequestDTO requestDTO){
        final Source source = sourceMapper.toSource(id, requestDTO);
        source.setId(id);
        final Source managedSource = sourceService.update(source);
        return sourceMapper.toDetailsDTO(managedSource);
    }

    @DeleteMapping("/{id}")
    public SourceDetailsDTO delete(@PathVariable @Positive Long id){
        return sourceMapper.toDetailsDTO(sourceService.deleteById(id));
    }

}
