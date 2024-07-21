package com.grid.core.prices.restapi.service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

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
	
	private static final String PRICES_NOT_FOUND = "Prices not found";

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
		
		List<Prices> pricesList = pricesRepository.findBydBrandIdAndProductIdAndDate(
															brand,
															pricesRequestDto.getProductId(),
															pricesRequestDto.getDate()
														);
		
		return composeResponse(pricesList);
	}

	private PricesResponseDto composeResponse(List<Prices> pricesList) {
		log.debug("Building response");

		// if prices list is empty an error message is set 
		if (!pricesList.isEmpty()) {
			return createPricesResponseDto(pricesList);

		} else {
			log.debug("Response with error message: " + PRICES_NOT_FOUND);
			return PricesResponseDto.builder()
					.errorMessage(PRICES_NOT_FOUND)
				.build();
		}
		
	}

	private PricesResponseDto createPricesResponseDto(List<Prices> pricesList) {
		// filter prices list by max priority
		Prices price = pricesList.stream()
			      .max(Comparator.comparing(Prices::getPriority))
			      .orElseThrow(NoSuchElementException::new);
	
		return PricesResponseDto.builder()
							.productId(price.getProductId())
							.brandId(Long.toString(price.getBrandId().getId()))
							.priceList(Integer.toString(price.getPriceList()))
							.startDate(price.getStartDate().toString())
							.endDate(price.getEndDate().toString())
							.price(price.getPrice())
						.build();
	}
	
}