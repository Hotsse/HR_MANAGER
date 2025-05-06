package com.hotsse.hrmanager.domain.holiday.entity;

import com.hotsse.hrmanager.core.database.converter.YearMonthAttributeConverter;
import com.hotsse.hrmanager.domain.holiday.constants.HolidayType;
import com.hotsse.hrmanager.domain.holiday.dto.HolidaySaveDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.YearMonth;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Holiday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "holiday_date")
    private LocalDate holidayDate;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private HolidayType type;

    @Column(name = "description")
    private String description;

    @Column(name = "year_month")
    @Convert(converter = YearMonthAttributeConverter.class)
    private YearMonth yearMonth;

    @Builder
    public Holiday(LocalDate holidayDate, HolidayType type, String description, YearMonth yearMonth) {
        this.holidayDate = holidayDate;
        this.type = type;
        this.description = description;
        this.yearMonth = yearMonth;
    }

    public static Holiday of(HolidaySaveDto dto) {
        return Holiday.builder()
                .holidayDate(dto.getHolidayDate())
                .type(dto.getType())
                .description(dto.getDescription())
                .yearMonth(dto.getYearMonth())
                .build();
    }
}
