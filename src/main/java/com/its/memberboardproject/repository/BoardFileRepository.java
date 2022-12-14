package com.its.memberboardproject.repository;

import com.its.memberboardproject.entity.BoardFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BoardFileRepository extends JpaRepository<BoardFileEntity,Long> {


}
