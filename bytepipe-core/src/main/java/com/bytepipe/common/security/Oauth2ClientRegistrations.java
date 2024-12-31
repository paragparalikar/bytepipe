package com.bytepipe.common.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("spring.security.oauth2.client.registration")
public class Oauth2ClientRegistrations {

    private OpaqueTokenConfig google = new OpaqueTokenConfig();
    private OpaqueTokenConfig microsoft = new OpaqueTokenConfig();
    private OpaqueTokenConfig linkedin = new OpaqueTokenConfig();
    private OpaqueTokenConfig github = new OpaqueTokenConfig();

}
