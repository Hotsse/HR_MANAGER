package com.hotsse.hrmanager.domain.holiday.dto;

import com.hotsse.hrmanager.domain.holiday.constants.HolidayType;
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
public class HolidaySaveDto {

    private LocalDate holidayDate;
    private HolidayType type;
    private String description;
    private YearMonth yearMonth;
}
