package com.hallak.SentinelAI.repositories;

import com.hallak.SentinelAI.entities.Scan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScanRepository extends JpaRepository<Scan,String> {
}
