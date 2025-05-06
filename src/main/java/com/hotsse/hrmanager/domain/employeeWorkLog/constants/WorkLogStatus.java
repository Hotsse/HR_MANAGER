package com.hotsse.hrmanager.domain.employeeWorkLog.constants;

import lombok.Getter;

@Getter
public enum WorkLogStatus {
    NORMAL("NORMAL", "정상출근"),
    LATE("LATE", "지각"),
    RE_ENTRY("RE_ENTRY", "재출근"),
    ;

    private final String code;
    private final String name;

    WorkLogStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
