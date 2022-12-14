package com.its.memberboardproject.repository;

import com.its.memberboardproject.entity.BoardFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardFileRepository extends JpaRepository<BoardFileEntity,Long> {
}
