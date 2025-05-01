package com.hotsse.hrmanager.core.api;

public enum ApiType {

    PUBLIC_DATA_NATIONAL_HOLIDAYS("PUBLIC_DATA_NATIONAL_HOLIDAYS"),
    ;

    private final String code;

    ApiType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
