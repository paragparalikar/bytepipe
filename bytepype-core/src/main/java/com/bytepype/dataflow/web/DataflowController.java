package com.bytepype.dataflow.web;

import com.bytepype.dataflow.Dataflow;
import com.bytepype.dataflow.DataflowService;
import com.bytepype.dataflow.web.dto.CreateDataflowRequestDTO;
import com.bytepype.dataflow.web.dto.DataflowDTO;
import com.bytepype.dataflow.web.dto.UpdateDataflowRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dataflows")
public class DataflowController {

    private final DataflowMapper dataflowMapper;
    private final DataflowService dataflowService;

    @GetMapping
    public List<DataflowDTO> findAll(){
        return dataflowMapper.toDataflowDTOs(dataflowService.findAll());
    }

    @GetMapping("/{id}")
    public DataflowDTO findById(@PathVariable("id") @NotNull final Long id){
        final Dataflow dataflow = dataflowService.findById(id);
        return dataflowMapper.toDataflowDTO(dataflow);
    }

    @PostMapping
    public DataflowDTO create(@NotNull @Valid @RequestBody final CreateDataflowRequestDTO dto){
        final Dataflow dataflow = dataflowMapper.toDataflow(dto);
        final Dataflow managedDataflow = dataflowService.create(dataflow);
        return dataflowMapper.toDataflowDTO(managedDataflow);
    }

    @PutMapping("/{id}")
    public DataflowDTO update(
            @PathVariable("id") @NotNull final Long id,
            @NotNull @Valid @RequestBody final UpdateDataflowRequestDTO dto){
        final Dataflow dataflow = dataflowMapper.toDataflow(dto);
        dataflow.setId(id);
        final Dataflow managedDataflow = dataflowService.update(dataflow);
        return dataflowMapper.toDataflowDTO(managedDataflow);
    }

    @DeleteMapping("/{id}")
    public DataflowDTO delete(@PathVariable("id") @NotNull final Long id){
        final Dataflow dataflow = dataflowService.delete(id);
        return dataflowMapper.toDataflowDTO(dataflow);
    }

}