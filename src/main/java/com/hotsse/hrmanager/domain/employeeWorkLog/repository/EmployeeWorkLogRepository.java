package com.hotsse.hrmanager.domain.employeeWorkLog.repository;

import com.hotsse.hrmanager.domain.employeeWorkLog.entity.EmployeeWorkLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeWorkLogRepository extends JpaRepository<EmployeeWorkLog, Long> {

    List<EmployeeWorkLog> findAllByAccountIdAndWorkDate(String accountId, LocalDate workDate);

    boolean existsByAccountIdAndWorkDate(String accountId, LocalDate workDate);

    boolean existsByAccountIdAndWorkDateAndClockOutIsNull(String accountId, LocalDate workDate);

    Optional<EmployeeWorkLog> findByAccountIdAndWorkDateAndClockOutIsNull(String accountId, LocalDate workDate);
}
