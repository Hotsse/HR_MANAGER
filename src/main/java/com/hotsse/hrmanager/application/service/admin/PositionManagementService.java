package com.hotsse.hrmanager.application.service.admin;

import com.hotsse.hrmanager.domain.position.dto.PositionDto;
import com.hotsse.hrmanager.domain.position.dto.PositionSaveDto;
import com.hotsse.hrmanager.domain.position.entity.Position;
import com.hotsse.hrmanager.domain.position.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PositionManagementService {

    private final PositionRepository positionRepository;

    @Transactional
    public Position getPosition(String code) {
        return positionRepository.findById(code)
                .orElseThrow(() -> new IllegalArgumentException("Position not found with code: " + code));
    }

    @Transactional
    public Page<PositionDto> getPositions(String keyword, PageRequest pageRequest) {
        return positionRepository.findAllByNameContaining(keyword, pageRequest).map(PositionDto::convert);
    }

    @Transactional
    public String createPosition(PositionSaveDto dto) {
        Position result = positionRepository.save(Position.of(dto));
        return result.getCode();
    }

    @Transactional
    public void deletePosition(String code) {
        Position position = positionRepository.findById(code)
                .orElseThrow(() -> new IllegalArgumentException("Position not found with code: " + code));
        positionRepository.delete(position);
    }
}
