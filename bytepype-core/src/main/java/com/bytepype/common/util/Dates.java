package com.bytepype.common.util;

import com.bytepype.common.Constants;

import java.time.Instant;

public class Dates {

    public static String toString(Instant instant){
        return Constants.FORMATTER.format(instant);
    }

}
