package com.hotsse.hrmanager.application.controller.admin;

import com.hotsse.hrmanager.application.service.admin.HolidayManagementService;
import com.hotsse.hrmanager.domain.holiday.dto.HolidayDto;
import com.hotsse.hrmanager.domain.holiday.dto.HolidaySaveDto;
import com.hotsse.hrmanager.domain.holiday.dto.HolidaySyncDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;

@RestController
@RequestMapping("/api/admin/holiday")
@RequiredArgsConstructor
public class HolidayManagementController {

    private final HolidayManagementService holidayManagementService;

    @PostMapping("/sync")
    public ResponseEntity<Void> sync(@RequestBody HolidaySyncDto dto) {
        holidayManagementService.syncHolidays(dto.getYearMonth());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<HolidayDto>> get(@RequestParam YearMonth yearMonth,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<HolidayDto> holidays = holidayManagementService.getHolidays(yearMonth, pageRequest);
        return ResponseEntity.ok(holidays);
    }

    @PostMapping
    public ResponseEntity<Long> createHoliday(@RequestBody HolidaySaveDto dto) {
        Long id = holidayManagementService.createHoliday(dto);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHoliday(@PathVariable("id") Long id) {
        holidayManagementService.deleteHoliday(id);
        return ResponseEntity.noContent().build();
    }
}
