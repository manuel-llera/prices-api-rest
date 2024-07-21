package com.grid.core.prices.restapi.model.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "PRICES")
@Data
public class Prices {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
    @JoinColumn(name="brand_id", nullable=false)
	private Brand brandId;

	@Column(name = "start_date")
	private Timestamp startDate;
	
	@Column(name = "end_date")
	private Timestamp endDate;

	@Column(name = "price_list")
	private int priceList;
	
	@Column(name = "product_id")
	private String productId;
	
	@Column(name = "priority")
	private int priority;
	
	@Column(name = "price")
	private Double price;
	
	@Column(name = "curr")
	private String curr;
}
