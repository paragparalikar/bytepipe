package com.bytepype.common;

import lombok.experimental.UtilityClass;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class Constants {

    public DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneOffset.UTC);

}
