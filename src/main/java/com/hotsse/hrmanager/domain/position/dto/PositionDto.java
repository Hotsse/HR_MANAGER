package com.hotsse.hrmanager.domain.position.dto;

import com.hotsse.hrmanager.domain.position.entity.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PositionDto {

    private String code;
    private String name;
    private int level;
    private LocalDate startDate;
    private LocalDate endDate;

    public static PositionDto convert(Position position) {
        return PositionDto.builder()
                .code(position.getCode())
                .name(position.getName())
                .level(position.getLevel())
                .startDate(position.getStartDate())
                .endDate(position.getEndDate())
                .build();
    }
}
