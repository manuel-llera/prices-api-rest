package com.grid.core.prices.restapi.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PricesRequestDto {
	
	private String brandId;
	private String productId;
	private Date date;
    
}
