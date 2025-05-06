package com.hotsse.hrmanager.domain.holiday.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.YearMonth;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class HolidaySyncDto {

    private YearMonth yearMonth;
}
