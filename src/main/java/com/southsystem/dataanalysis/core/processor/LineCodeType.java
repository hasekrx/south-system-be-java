package com.southsystem.dataanalysis.core.processor;

import com.southsystem.dataanalysis.exception.InvalidLineCodeException;
import lombok.Getter;

@Getter
public enum LineCodeType {
    SALESMAN("001"),
    CLIENT("002"),
    SALES("003");

    public final String code;

    LineCodeType(String code) {
        this.code = code;
    }

    public static LineCodeType fromString(String code) {
        for (LineCodeType lineCode : LineCodeType.values()) {
            if (lineCode.code.equalsIgnoreCase(code)) {
                return lineCode;
            }
        }
        throw new InvalidLineCodeException(String.format("invalid code %s", code));
    }
}
