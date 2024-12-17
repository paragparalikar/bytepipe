package com.bytepipe.role;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    final RoleRepository roleRepository;

    public Role findById(@NotNull Long id) throws RoleNotFoundException {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException(id));
    }

    public Role save(@NotNull @Valid Role role) {
        return roleRepository.save(role);
    }

    public void deleteById(@NotNull Long id) {
        roleRepository.deleteById(id);
    }

    public boolean existsByNameIgnoreCaseAndProjectId(String name, Long projectId){
        return roleRepository.existsByNameIgnoreCaseAndProjectId(name, projectId);
    }
}
