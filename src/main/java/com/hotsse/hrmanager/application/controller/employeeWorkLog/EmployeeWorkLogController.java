package com.hotsse.hrmanager.application.controller.employeeWorkLog;

import com.hotsse.hrmanager.application.service.employeeWorkLog.EmployeeWorkLogService;
import com.hotsse.hrmanager.domain.employeeWorkLog.dto.EmployeeWorkLogSaveDto;
import com.hotsse.hrmanager.domain.employeeWorkLog.dto.EmployeeWorkStatusDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/work-log")
public class EmployeeWorkLogController {

    private final EmployeeWorkLogService workLogService;

    @GetMapping("/today")
    public ResponseEntity<EmployeeWorkStatusDto> getTodayWorkLog(String accountId) {
        return ResponseEntity.ok(workLogService.getTodayWorkStatus(accountId));
    }

    @PostMapping("/clock-in")
    public ResponseEntity<Void> recordClockIn(@RequestBody EmployeeWorkLogSaveDto dto) {
        workLogService.recordClockInNow(dto.getAccountId(), dto.getReason());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/clock-out")
    public ResponseEntity<Void> recordClockOut(@RequestBody EmployeeWorkLogSaveDto dto) {
        workLogService.recordClockOutNow(dto.getAccountId());
        return ResponseEntity.noContent().build();
    }
}
