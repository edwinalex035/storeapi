package com.applaudo.store.domain.repository;

import com.applaudo.store.domain.model.BuyProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyProductRepository extends JpaRepository<BuyProduct, Long> {
}
