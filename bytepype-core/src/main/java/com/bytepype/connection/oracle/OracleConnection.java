package com.bytepype.connection.oracle;

import com.bytepype.connection.Connection;
import jakarta.persistence.Cacheable;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.sql.DataSource;

@Data
@Entity
@Cacheable
@ToString(callSuper = true)
@DiscriminatorValue("ORACLE")
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class OracleConnection extends Connection<DataSource> {

    @NotBlank
    @Size(min = 3, max = 255)
    private String url;

    @NotBlank
    @Size(min = 3, max = 255)
    private String username;

    @NotBlank
    @Size(min = 3, max = 255)
    private String password;

    @Override
    public DataSource get() {
        return null;
    }

}
