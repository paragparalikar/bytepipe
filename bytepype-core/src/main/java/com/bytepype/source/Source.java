package com.bytepype.source;

import com.bytepype.common.audit.AbstractAuditable;
import com.bytepype.connection.Connection;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Cacheable
@EqualsAndHashCode(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
public class Source extends AbstractAuditable {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Size(min = 3, max = 255)
    @Column(nullable = false)
    private String name;

    @Size(max = 255)
    private String description;

    @Valid
    @ManyToOne
    private Connection<?> connection;

}
