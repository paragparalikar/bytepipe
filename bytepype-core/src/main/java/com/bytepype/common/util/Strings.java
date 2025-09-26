package com.bytepype.common.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Strings {

    public String sanitize(String text){
        return null == text ? null : text
                .replace('\r', ' ')
                .replace('\n', ' ');
    }

}
