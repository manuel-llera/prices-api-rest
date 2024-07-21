package com.grid.core.prices.restapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grid.core.prices.restapi.model.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
	
	Brand findAllById(long id);
	
}