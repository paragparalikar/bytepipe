package com.bytepype.role.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RoleNotFoundException extends Exception {

    @NonNull private final Long id;

}
