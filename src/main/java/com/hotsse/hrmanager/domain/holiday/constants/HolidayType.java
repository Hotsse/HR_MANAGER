package com.hotsse.hrmanager.domain.holiday.constants;

import lombok.Getter;

@Getter
public enum HolidayType {
    SATURDAY("SATURDAY", "토요일"),
    SUNDAY("SUNDAY", "일요일"),
    NATION_HOLIDAY("NATION_HOLIDAY", "공휴일"),
    ;

    private final String code;
    private final String name;

    HolidayType(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
