package com.hotsse.hrmanager.domain.position.entity;

import com.hotsse.hrmanager.domain.position.dto.PositionSaveDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Position {

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "level")
    private int level;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Builder
    public Position(String code, String name, int level, LocalDate startDate, LocalDate endDate) {
        this.code = code;
        this.name = name;
        this.level = level;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Position of(PositionSaveDto dto) {
        return Position.builder()
                .code(dto.getCode())
                .name(dto.getName())
                .level(dto.getLevel())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .build();
    }
}
