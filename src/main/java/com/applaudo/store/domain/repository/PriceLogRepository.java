package com.applaudo.store.domain.repository;

import com.applaudo.store.domain.model.PriceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceLogRepository extends JpaRepository<PriceLog, Long> {
}
