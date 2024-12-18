package com.bytepipe.project;

import com.bytepipe.common.audit.AbstractAuditable;
import com.bytepipe.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
@Entity
@Cacheable
@EqualsAndHashCode(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
public class Project extends AbstractAuditable {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Size(min = 3, max = 255)
    @Column(nullable = false)
    private String name;

    @Size(max = 255)
    private String description;

    @ManyToMany
    private Set<User> users = new HashSet<>();

    @Size(max = 255)
    @ElementCollection
    private Map<@NotBlank @Size(max = 255) String, @NotBlank @Size(max = 255) String> attributes = new HashMap<>();

}
