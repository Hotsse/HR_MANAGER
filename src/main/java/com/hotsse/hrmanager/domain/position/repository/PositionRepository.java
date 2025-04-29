package com.hotsse.hrmanager.domain.position.repository;

import com.hotsse.hrmanager.domain.position.entity.Position;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, String> {

    Page<Position> findAllByNameContaining(String keyword, Pageable pageable);
}
