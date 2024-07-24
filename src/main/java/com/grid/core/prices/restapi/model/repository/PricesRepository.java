package com.grid.core.prices.restapi.model.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grid.core.prices.restapi.model.entity.Brand;
import com.grid.core.prices.restapi.model.entity.Prices;

@Repository
public interface PricesRepository extends JpaRepository<Prices, Long> {
	
	Prices findTopPriorityByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(Brand brandId, String productId, Date startDate, Date endDate);
}