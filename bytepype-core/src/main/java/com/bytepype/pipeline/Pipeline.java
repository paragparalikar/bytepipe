package com.bytepype.pipeline;

import com.bytepype.common.audit.AbstractAuditable;
import com.bytepype.connection.Connection;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Cacheable
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class Pipeline extends AbstractAuditable {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Size(min = 3, max = 255)
    @Column(nullable = false)
    private String name;

    private boolean enabled;

    @Size(max = 255)
    private String description;

    @Valid
    @NotNull
    @ManyToOne
    private Connection<?> source;

    @Valid
    @NotNull
    @ManyToOne
    private Connection<?> destination;


}
