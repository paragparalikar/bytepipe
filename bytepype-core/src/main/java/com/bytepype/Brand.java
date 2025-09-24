package com.bytepype;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Embeddable
public class Brand {

    @Data
    @Embeddable
    public static class SocialLinks {
        @Size(max = 255) private String github;
        @Size(max = 255) private String twitter;
        @Size(max = 255) private String facebook;
        @Size(max = 255) private String linkedin;
    }

    @Size(max = 255) private String logoUrl;
    @Size(max = 255) private String companyName;
    @Size(max = 255) private String companyAddress;
    @Embedded private SocialLinks socialLinks;

}
