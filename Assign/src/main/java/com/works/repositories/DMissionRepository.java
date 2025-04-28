package com.works.repositories;

import com.works.entities.DMission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DMissionRepository extends JpaRepository<DMission, Long> {
    List<DMission> findAllByCid(Long cid);
}