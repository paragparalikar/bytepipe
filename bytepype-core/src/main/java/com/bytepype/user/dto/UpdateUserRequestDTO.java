package com.bytepype.user.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public class UpdateUserRequestDTO {

    private String name;

    private String email;

    private String picture;

    @JsonAlias("given_name")
    private String givenName;

    @JsonAlias("family_name")
    private String familyName;

}
