package com.hotsse.hrmanager.domain.holiday.dto;

import com.hotsse.hrmanager.domain.holiday.constants.HolidayType;
import com.hotsse.hrmanager.domain.holiday.entity.Holiday;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.YearMonth;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class HolidayDto {

    private Long id;
    private LocalDate holidayDate;
    private HolidayType type;
    private String description;
    private YearMonth yearMonth;

    public String getTypeName() {
        return type.getName();
    }

    public static HolidayDto convert(Holiday holiday) {
        return HolidayDto.builder()
                .id(holiday.getId())
                .holidayDate(holiday.getHolidayDate())
                .type(holiday.getType())
                .description(holiday.getDescription())
                .yearMonth(holiday.getYearMonth())
                .build();
    }
}
