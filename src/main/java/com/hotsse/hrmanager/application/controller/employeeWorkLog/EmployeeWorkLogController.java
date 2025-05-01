package com.hotsse.hrmanager.application.controller.employeeWorkLog;

import com.hotsse.hrmanager.application.service.employeeWorkLog.EmployeeWorkLogService;
import com.hotsse.hrmanager.domain.employeeWorkLog.dto.EmployeeWorkLogDto;
import com.hotsse.hrmanager.domain.employeeWorkLog.dto.EmployeeWorkLogSaveDto;
import com.hotsse.hrmanager.domain.employeeWorkLog.dto.EmployeeWorkStatusDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @GetMapping
    public ResponseEntity<Page<EmployeeWorkLogDto>> getWorkLogs(@RequestParam(defaultValue = "") String accountId,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(workLogService.getEmployeeWorkLogs(accountId, pageRequest));
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
