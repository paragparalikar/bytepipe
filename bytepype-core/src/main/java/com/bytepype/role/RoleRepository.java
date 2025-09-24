package com.bytepype.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    boolean existsByNameIgnoreCaseAndProjectId(String name, Long projectId);

}
