package com.grid.core.prices.restapi.model.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.grid.core.prices.restapi.model.entity.Brand;
import com.grid.core.prices.restapi.model.entity.Prices;

@Repository
public interface PricesRepository extends JpaRepository<Prices, Long> {
	
	@Query("select prices "
			+ "from Prices prices "
			+ "where "
			+ "prices.brandId = :brandId "
			+ "and prices.productId = :productId "
			+ "and prices.startDate <= :paramDate and prices.endDate >= :paramDate "
			+ "order by prices.priority DESC ")
	List<Prices> findBydBrandIdAndProductIdAndDate(Brand brandId, String productId, Date paramDate);
	
}