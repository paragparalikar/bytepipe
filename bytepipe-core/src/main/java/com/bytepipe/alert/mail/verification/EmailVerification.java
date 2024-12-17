package com.bytepipe.alert.mail.verification;

import com.bytepipe.audit.AbstractAuditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
public class EmailVerification extends AbstractAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Email
    @NotBlank
    private String email;

    @Enumerated(EnumType.STRING)
    private EmailVerificationStatus status;

    public EmailVerification(String email){
        this.email = email;
        this.status = EmailVerificationStatus.INITIATED;
    }

}
