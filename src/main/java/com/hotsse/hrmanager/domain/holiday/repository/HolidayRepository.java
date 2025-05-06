package com.hotsse.hrmanager.domain.holiday.repository;

import com.hotsse.hrmanager.domain.holiday.entity.Holiday;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {

    Page<Holiday> findAllByYearMonth(YearMonth yearMonth, Pageable pageable);

    void deleteAllByYearMonth(YearMonth yearMonth);
}
