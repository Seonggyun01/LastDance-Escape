package com.lastdance.escape.playRecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayRecordRepository extends JpaRepository<PlayRecord, Long> {
}
