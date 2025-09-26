package com.bytepype.role.web;

import com.bytepype.role.Role;
import com.bytepype.role.exception.RoleNotFoundException;
import com.bytepype.role.RoleService;
import com.bytepype.role.web.dto.CreateRoleRequestDTO;
import com.bytepype.role.web.dto.RoleResponseDTO;
import com.bytepype.role.web.dto.UpdateRoleRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleMapper roleMapper;
    private final RoleService roleService;

    @GetMapping(params = {"name", "projectId"})
    public void existsByNameAndProjectId(@RequestParam @NotBlank @Size(max = 255) String name) throws RoleNotFoundException {
        if(!roleService.existsByNameIgnoreCase(name)){
            throw new RoleNotFoundException(0L);
        }
    }

    @PostMapping
    public RoleResponseDTO create(@RequestBody @NotNull @Valid CreateRoleRequestDTO dto){
        final Role role = roleMapper.toEntity(dto);
        final Role managedRole = roleService.save(role);
        return roleMapper.toDTO(role);
    }

    @PutMapping("/{id}")
    public RoleResponseDTO update(@PathVariable @NotNull Long id, @RequestBody @NotNull @Valid UpdateRoleRequestDTO dto) throws RoleNotFoundException {
        final Role role = roleService.findById(id);
        roleMapper.update(role, dto);
        final Role managedRole = roleService.save(role);
        return roleMapper.toDTO(role);
    }

    @DeleteMapping("/{id}")
    public RoleResponseDTO delete(@PathVariable @NotNull Long id) throws RoleNotFoundException {
        final Role role = roleService.findById(id);
        final RoleResponseDTO dto = roleMapper.toDTO(role);
        roleService.deleteById(id);
        return dto;
    }

}
