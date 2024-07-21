package com.grid.core.prices.restapi.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.grid.core.prices.restapi.dto.PricesRequestDto;
import com.grid.core.prices.restapi.exception.ConvertParamException;

import lombok.extern.log4j.Log4j2;

@Log4j2
public abstract class Utils {
	
	private static final String ERROR_PARSING_INPUT_DATE = "Error parsing input Date format. Correct format => yyyy-MM-dd HH.mm.ss ";
	
	// Utility class with private No arguments constructor
	private Utils() {}

	public static void logDebugPathParams(String brandId, String productId, String date) {
		log.debug("\t param: brandId = \"" + brandId + "\"");
		log.debug("\t param: productId = \"" + productId + "\"");
		log.debug("\t param: date = \"" + date + "\"");
	}

	public static PricesRequestDto createPricesRequestDtoFromPathParams(String brandId, String productId, String date) {
		return PricesRequestDto.builder()
									.brandId(brandId)
									.productId(productId)
									.date(convertFromPathParamDateToDate(date))
								.build();
	}

	public static Date convertFromPathParamDateToDate(String date) {
		SimpleDateFormat requestDateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
		try {
			return requestDateFormat.parse(date);
		} catch (ParseException pEx) {
			log.error(ERROR_PARSING_INPUT_DATE + pEx);
			throw new ConvertParamException(ERROR_PARSING_INPUT_DATE);
		}
	}
}
