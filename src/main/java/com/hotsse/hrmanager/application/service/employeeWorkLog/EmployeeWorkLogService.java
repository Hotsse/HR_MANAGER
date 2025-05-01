package com.hotsse.hrmanager.application.service.employeeWorkLog;

import com.hotsse.hrmanager.domain.employee.repository.EmployeeRepository;
import com.hotsse.hrmanager.domain.employeeWorkLog.constants.WorkLogStatus;
import com.hotsse.hrmanager.domain.employeeWorkLog.constants.WorkStatus;
import com.hotsse.hrmanager.domain.employeeWorkLog.dto.EmployeeWorkLogDto;
import com.hotsse.hrmanager.domain.employeeWorkLog.dto.EmployeeWorkStatusDto;
import com.hotsse.hrmanager.domain.employeeWorkLog.entity.EmployeeWorkLog;
import com.hotsse.hrmanager.domain.employeeWorkLog.repository.EmployeeWorkLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmployeeWorkLogService {

    private final EmployeeWorkLogRepository workLogRepository;
    private final EmployeeRepository employeeRepository;

    private final LocalTime BELONG_IN_TIME = LocalTime.of(6, 0);
    private final LocalTime LATE_CLOCK_IN_TIME = LocalTime.of(11, 0);

    public LocalDate getWorkDate(LocalDateTime dateTime) {
        LocalDate workDate = dateTime.toLocalDate();
        if(dateTime.toLocalTime().isBefore(BELONG_IN_TIME)) {
            workDate = workDate.minusDays(1);
        }
        return workDate;
    }

    public LocalDate getWorkDate() {
        return getWorkDate(LocalDateTime.now());
    }

    public Page<EmployeeWorkLogDto> getEmployeeWorkLogs(String accountId, PageRequest pageRequest) {
        return workLogRepository.findAllByAccountIdOrderByIdDesc(accountId, pageRequest)
                .map(EmployeeWorkLogDto::convert);
    }

    public EmployeeWorkStatusDto getTodayWorkStatus(String accountId) {

        LocalDate workDate = getWorkDate();

        var workLogs = workLogRepository.findAllByAccountIdAndWorkDate(accountId, workDate)
                .stream()
                .map(EmployeeWorkLogDto::convert)
                .toList();

        WorkStatus workStatus = null;
        if (workLogs.isEmpty()) {
            workStatus = WorkStatus.BEFORE_WORK;
        } else if(workLogs.stream().anyMatch(n -> Objects.isNull(n.getClockOut()))) {
            workStatus = WorkStatus.WORKING;
        } else {
            workStatus = WorkStatus.AFTER_WORK;
        }

        return EmployeeWorkStatusDto.builder()
                .workDate(workDate)
                .workStatus(workStatus)
                .isLateClockIn(LocalTime.now().isAfter(LATE_CLOCK_IN_TIME))
                .workLogs(workLogs)
                .build();
    }

    public void recordClockInNow(String accountId, String reason) {
        recordClockIn(accountId, LocalDateTime.now(), reason);
    }

    public void recordClockIn(String accountId, LocalDateTime dateTime, String reason) {

        var emp = employeeRepository.findByAccountIdOrElseThrow(accountId);

        LocalDate workDate = getWorkDate(dateTime);
        boolean isReEntry = workLogRepository.existsByAccountIdAndWorkDate(accountId, workDate);
        boolean isLate = false;
        boolean isClockIn = workLogRepository.existsByAccountIdAndWorkDateAndClockOutIsNull(accountId, workDate);

        //validate
        if (isClockIn) {
            throw new IllegalStateException("출근 상태에서는 다시 출근할 수 없습니다.");
        } else if(dateTime.toLocalTime().isAfter(LATE_CLOCK_IN_TIME)) {
            isLate = true;
            if(!StringUtils.hasText(reason)) {
                throw new IllegalStateException("지각 사유를 입력해야 합니다.");
            }
        } else if (isReEntry) {
            if(!StringUtils.hasText(reason)) {
                throw new IllegalStateException("재출근 사유를 입력해야 합니다.");
            }
        }

        WorkLogStatus workStatus = WorkLogStatus.NORMAL;
        if(isReEntry) {
            workStatus = WorkLogStatus.RE_ENTRY;
        } else if(isLate) {
            workStatus = WorkLogStatus.LATE;
        }

        var entity = EmployeeWorkLog.builder()
                .accountId(accountId)
                .workDate(workDate)
                .clockIn(dateTime)
                .workStatus(workStatus)
                .reason(reason)
                .build();
        workLogRepository.save(entity);
    }

    public void recordClockOutNow(String accountId) {
        recordClockOut(accountId, LocalDateTime.now());
    }

    public void recordClockOut(String accountId, LocalDateTime dateTime) {
        var emp = employeeRepository.findByAccountIdOrElseThrow(accountId);
        LocalDate workDate = getWorkDate(dateTime);

        var workLog = workLogRepository.findByAccountIdAndWorkDateAndClockOutIsNull(accountId, workDate)
                .orElseThrow(() -> new IllegalStateException("출근 기록이 없습니다."));

        workLog.clockOut(dateTime);
        workLogRepository.save(workLog);
    }
}
