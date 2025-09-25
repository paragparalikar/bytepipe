package com.bytepype.role;

import com.bytepype.common.audit.AbstractAuditable;
import com.bytepype.user.Authority;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Set;

@Data
@Entity
@Cacheable
@EqualsAndHashCode(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
public class Role extends AbstractAuditable {

    @Id
    private Long id;

    @NotBlank
    @Size(min = 3, max = 255)
    @Column(nullable = false)
    private String name;

    @Size(max = 255)
    private String description;

    @ElementCollection(targetClass = Authority.class)
    private Set<Authority> authorities;

}
