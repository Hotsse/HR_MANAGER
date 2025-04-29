package com.hotsse.hrmanager.application.controller.admin;

import com.hotsse.hrmanager.application.service.admin.PositionManagementService;
import com.hotsse.hrmanager.domain.position.dto.PositionDto;
import com.hotsse.hrmanager.domain.position.dto.PositionSaveDto;
import com.hotsse.hrmanager.domain.position.entity.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/position")
@RequiredArgsConstructor
public class PositionManagementController {

    private final PositionManagementService positionManagementService;

    @GetMapping("/{positionCode}")
    public ResponseEntity<Position> getOne(@PathVariable("positionCode") String positionCode) {
        Position position = positionManagementService.getPosition(positionCode);
        return ResponseEntity.ok(position);
    }

    @GetMapping
    public ResponseEntity<Page<PositionDto>> get(@RequestParam(defaultValue = "") String keyword,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<PositionDto> positions = positionManagementService.getPositions(keyword, pageRequest);
        return ResponseEntity.ok(positions);
    }

    @PostMapping
    public ResponseEntity<String> createPosition(@RequestBody PositionSaveDto dto) {
        String code = positionManagementService.createPosition(dto);
        return ResponseEntity.ok(code);
    }

    @DeleteMapping("/{positionCode}")
    public ResponseEntity<Void> deletePosition(@PathVariable("positionCode") String positionCode) {
        positionManagementService.deletePosition(positionCode);
        return ResponseEntity.noContent().build();
    }
}
