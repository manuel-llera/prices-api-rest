package com.grid.core.prices.restapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class PricesResponseDto {
	private String productId;
	private String brandId;
	private String startDate;
	private String endDate;
	private Double price;
	

    private String errorMessage;
}
