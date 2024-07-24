package com.grid.core.prices.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grid.core.prices.restapi.dto.PricesRequestDto;
import com.grid.core.prices.restapi.dto.PricesResponseDto;
import com.grid.core.prices.restapi.model.entity.Brand;
import com.grid.core.prices.restapi.model.entity.Prices;
import com.grid.core.prices.restapi.model.repository.BrandRepository;
import com.grid.core.prices.restapi.model.repository.PricesRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class PricesService {
	
	private static final String PRICE_NOT_FOUND = "Price not found";

	@Autowired
	BrandRepository brandRepository;
	
	@Autowired
	PricesRepository pricesRepository;

	/**
	 * Method in order to get prices info from data base
	 * 
	 * @param pricesRequestDto
	 * @return
	 * 		PricesResponseDto
	 */
	public PricesResponseDto getPricesInfo(final PricesRequestDto pricesRequestDto) {

		log.debug("Starting => PricesService.getPricesInfo");
		
		Brand brand = brandRepository.findAllById(Long.parseLong(pricesRequestDto.getBrandId()) );
		
		Prices price = pricesRepository.findTopPriorityByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
												brand,
												pricesRequestDto.getProductId(),
												pricesRequestDto.getDate(),
												pricesRequestDto.getDate()
											);
		
		return composeResponse(price);
	}

	private PricesResponseDto composeResponse(Prices price) {
		log.debug("Building response");
		
		// if price is empty an error message is set 
		if (price != null) {
			return createPriceResponseDto(price);

		} else {
			log.debug("Response with error message: " + PRICE_NOT_FOUND);
			return PricesResponseDto.builder()
					.errorMessage(PRICE_NOT_FOUND)
				.build();
		}
		
	}

	private PricesResponseDto createPriceResponseDto(Prices price) {
		
		return PricesResponseDto.builder()
							.productId(price.getProductId())
							.brandId(Long.toString(price.getBrandId().getId()))
							.startDate(price.getStartDate().toString())
							.endDate(price.getEndDate().toString())
							.price(price.getPrice())
						.build();
	}
	
}