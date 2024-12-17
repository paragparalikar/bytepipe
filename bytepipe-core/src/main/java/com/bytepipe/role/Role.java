package com.bytepipe.role;

import com.bytepipe.audit.AbstractAuditable;
import com.bytepipe.project.Project;
import com.bytepipe.user.Authority;
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

    @ManyToOne(optional = false)
    private Project project;

}
