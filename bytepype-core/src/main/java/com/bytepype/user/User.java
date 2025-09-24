package com.bytepype.user;

import com.bytepype.common.audit.AbstractAuditable;
import com.bytepype.role.Role;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Cacheable
@Table(name = "USER_")
@EqualsAndHashCode(callSuper = false)
public class User extends AbstractAuditable implements OAuth2AuthenticatedPrincipal {

    @Id
    @GeneratedValue
    private Long id;

    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String givenName;

    @Size(max = 255)
    private String familyName;

    @Size(max = 255)
    private String picture;

    @Email
    @NotBlank
    @Column(unique = true, nullable = false)
    private String email;

    @Transient private Map<String, Object> attributes;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<@Valid @NotNull Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(Role::getAuthorities)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

}
