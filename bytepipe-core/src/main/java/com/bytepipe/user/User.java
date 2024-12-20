package com.bytepipe.user;

import com.bytepipe.common.audit.AbstractAuditable;
import com.bytepipe.role.Role;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.*;
import java.util.stream.Collectors;

@Data
@Entity
@Cacheable
@Table(name = "USER_")
@EqualsAndHashCode(callSuper = false)
public class User extends AbstractAuditable implements UserDetails, OidcUser {
    public static final int DEFAULT_PASSWORD_LENGTH = 10;
    public static final String PATTERN_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{8,255}$";

    @Id
    @GeneratedValue
    private Long id;

    @Email
    @NotBlank
    @Column(unique = true, nullable = false)
    private String email;

    @Size(min = 8, max = 255)
    private String password;

    @Size(max = 255)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<@Valid @NotNull Role> roles = new HashSet<>();

    private boolean enabled;

    @Size(max = 255)
    @ElementCollection
    private Map<@NotBlank @Size(max = 255) String, @NotBlank @Size(max = 255) String> properties = new HashMap<>();

    @Transient private OidcIdToken idToken;
    @Transient private OidcUserInfo userInfo;
    @Transient private Map<String, Object> claims;
    @Transient private Map<String, Object> attributes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(Role::getAuthorities)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    @Override
    public String getUsername() {
        return email;
    }

}
