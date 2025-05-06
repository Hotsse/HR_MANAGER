package com.hotsse.hrmanager.domain.holiday.domainService;

import com.hotsse.hrmanager.core.api.ApiCaller;
import com.hotsse.hrmanager.core.api.ApiType;
import com.hotsse.hrmanager.core.api.NationHolidaysApiRequest;
import com.hotsse.hrmanager.core.api.NationHolidaysApiResponse;
import com.hotsse.hrmanager.domain.holiday.constants.HolidayType;
import com.hotsse.hrmanager.domain.holiday.dto.HolidaySaveDto;
import lombok.RequiredArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
public class HolidaysDomainService {

    private final ApiCaller apiCaller;

    public List<HolidaySaveDto> getAllHolidays(YearMonth yearMonth) {

        List<HolidaySaveDto> holidays = new ArrayList<>();

        //주말
        for (int day = 1; day <= yearMonth.lengthOfMonth(); day++) {
            LocalDate date = yearMonth.atDay(day);
            DayOfWeek dow = date.getDayOfWeek();

            if (dow == DayOfWeek.SATURDAY || dow == DayOfWeek.SUNDAY) {
                HolidaySaveDto dto = HolidaySaveDto.builder()
                        .holidayDate(date)
                        .description(dow.getDisplayName(TextStyle.SHORT, Locale.KOREAN))
                        .type(dow == DayOfWeek.SATURDAY ? HolidayType.SATURDAY : HolidayType.SUNDAY)
                        .build();
                holidays.add(dto);
            }
        }

        //공휴일
        var nationHolidays = convertToSaveDto(getNationHolidays(yearMonth));

        //병합 및 정렬
        holidays.addAll(nationHolidays);
        holidays.sort(Comparator.comparing(HolidaySaveDto::getHolidayDate));

        return holidays;
    }

    public NationHolidaysApiResponse getNationHolidays(YearMonth yearMonth) {
        String serviceKey = apiCaller.getKey(ApiType.PUBLIC_DATA_NATIONAL_HOLIDAYS);
        NationHolidaysApiRequest request = NationHolidaysApiRequest.builder()
                .year(String.valueOf(yearMonth.getYear()))
                .month(String.format("%02d", yearMonth.getMonthValue()))
                .serviceKey(serviceKey)
                .build();

        //TODO: 공휴일 없는 경우 에러처리 필요
        return apiCaller.call(ApiType.PUBLIC_DATA_NATIONAL_HOLIDAYS, request, NationHolidaysApiResponse.class);
    }

    public List<HolidaySaveDto> convertToSaveDto(NationHolidaysApiResponse input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return input.getResponse().getBody().getItems().getItem().stream()
                .map(n -> {
                    LocalDate date = LocalDate.parse(n.getLocDate(), formatter);
                    return HolidaySaveDto.builder()
                            .holidayDate(date)
                            .description(n.getDateName())
                            .type(HolidayType.NATION_HOLIDAY)
                            .build();
                })
                .toList();
    }
}
