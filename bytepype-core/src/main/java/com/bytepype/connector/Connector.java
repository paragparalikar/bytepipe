package com.bytepype.connector;

import com.bytepype.common.audit.AbstractAuditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.function.Supplier;

import static jakarta.persistence.InheritanceType.JOINED;

@Data
@Entity
@Cacheable
@DiscriminatorValue("")
@ToString(callSuper = true)
@Inheritance(strategy = JOINED)
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@DiscriminatorColumn(name = "TYPE", length = 255)
public abstract class Connector<T> extends AbstractAuditable implements Supplier<T> {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Size(min = 3, max = 255)
    @Column(nullable = false)
    private String name;

    @Size(max = 255)
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(insertable=false, updatable=false)
    private ConnectorType type;

}
