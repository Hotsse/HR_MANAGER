package com.hotsse.hrmanager.application.service.admin;

import com.hotsse.hrmanager.core.api.ApiCaller;
import com.hotsse.hrmanager.domain.holiday.domainService.HolidaysDomainService;
import com.hotsse.hrmanager.domain.holiday.dto.HolidayDto;
import com.hotsse.hrmanager.domain.holiday.dto.HolidaySaveDto;
import com.hotsse.hrmanager.domain.holiday.entity.Holiday;
import com.hotsse.hrmanager.domain.holiday.repository.HolidayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;

@Service
@RequiredArgsConstructor
public class HolidayManagementService {

    private final HolidayRepository holidayRepository;
    private final ApiCaller apiCaller;

    @Transactional
    public void syncHolidays(YearMonth yearMonth) {
        var domainService = new HolidaysDomainService(apiCaller);
        var p = domainService.getAllHolidays(yearMonth);
        var newEntities = p.stream().map(Holiday::of).toList();
        holidayRepository.deleteAllByYearMonth(yearMonth);
        holidayRepository.saveAll(newEntities);
    }

    @Transactional
    public Page<HolidayDto> getHolidays(YearMonth yearMonth, PageRequest pageRequest) {
        return holidayRepository.findAllByYearMonth(yearMonth, pageRequest).map(HolidayDto::convert);
    }

    @Transactional
    public Long createHoliday(HolidaySaveDto dto) {
        Holiday result = holidayRepository.save(Holiday.of(dto));
        return result.getId();
    }

    @Transactional
    public void deleteHoliday(Long id) {
        Holiday holiday = holidayRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Holiday not found"));
        holidayRepository.delete(holiday);
    }
}
