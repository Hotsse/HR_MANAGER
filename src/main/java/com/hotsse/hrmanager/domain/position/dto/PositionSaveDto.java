package com.hotsse.hrmanager.domain.position.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PositionSaveDto {

    private String code;
    private String name;
    private int level;
    private LocalDate startDate;
    private LocalDate endDate;
}
