package com.grid.core.prices.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grid.core.prices.restapi.dto.PricesRequestDto;
import com.grid.core.prices.restapi.dto.PricesResponseDto;
import com.grid.core.prices.restapi.service.PricesService;
import com.grid.core.prices.restapi.utils.Utils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Validated
@RestController
@Tag(name = "API REST exercise implementation", description = "API REST to search Brand Product Prices info")
@RequestMapping("/prices")
public class PricesApiController {

	@Autowired
	private PricesService pricesService;
	
	@Operation(summary = "Search prices info", description = "Get Api Method in order to get info about prices")
	@GetMapping("/brand/{brand}/product/{product}/date/{date}")
	public ResponseEntity<PricesResponseDto> getBrandProductPricesInfo(
			@NotBlank(message = "BrandId can not be empty") @PathVariable(name = "brand", required = true) String brandId,
			@NotBlank(message = "ProductId can not be empty") @PathVariable(name = "product", required = true) String productId,
			@NotBlank(message = "Date can not be empty") @PathVariable(name = "date", required = true) String date) {

		log.info("\n\n");
		log.info("Starting => PricesApiController.getProductPricesInfo");
		Utils.logDebugPathParams(brandId, productId, date);

		PricesRequestDto pricesRequestDto = Utils.createPricesRequestDtoFromPathParams(brandId, productId, date);
		PricesResponseDto responseDto = pricesService.getPricesInfo(pricesRequestDto);
		
		log.info("Returning responseDto");
		
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
	
}
