package com.grid.core.prices.restapi;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.grid.core.prices.restapi.dto.PricesRequestDto;
import com.grid.core.prices.restapi.dto.PricesResponseDto;
import com.grid.core.prices.restapi.model.entity.Brand;
import com.grid.core.prices.restapi.model.entity.Prices;
import com.grid.core.prices.restapi.model.repository.BrandRepository;
import com.grid.core.prices.restapi.model.repository.PricesRepository;
import com.grid.core.prices.restapi.service.PricesService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class PricesServiceTests {

	private static final String ZARA_BRAND_ID = "1";
	private static final String PRODUCT_ID_35455 = "35455";
	private static final SimpleDateFormat requestDateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
	private static final String _2020_06_14_10_00_00 = "2020-06-14 10.00.00";
	private static final String _2020_06_14_16_00_00 = "2020-06-14 16.00.00";
	private static final String _2020_06_14_21_00_00 = "2020-06-14 21.00.00";
	private static final String _2020_06_15_10_00_00 = "2020-06-15 10.00.00";
	private static final String _2020_06_16_21_00_00 = "2020-06-16 21.00.00";
	private static final double PRICE_35_50 = 35.50D;
	private static final double PRICE_25_45 = 25.45D;
	private static final double PRICE_30_50 = 30.50D;
	private static final double PRICE_38_95 = 38.95D;

	@Autowired
	DataSource dataSource;

	@Autowired
	private PricesService pricesService;

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private PricesRepository pricesRepository;

	@Test
	void whenFindAllBrandsHasExpectedNumberOfElements() {
		List<Brand> brandsList = brandRepository.findAll();
		assertThat(brandsList).hasSize(4);
	}
	
	@Test
	void whenFindAllPricesHasExpectedNumberOfElements() {
		List<Prices> pricesList = pricesRepository.findAll();
		assertThat(pricesList).hasSize(4);
	}
	
	@Test
	void whenGetPricesInfoForZaraAndProduct35455AndDate14_Hour10_00PriceIs_35_50() throws ParseException {
		
		// when
		PricesRequestDto requestDto = PricesRequestDto.builder()
				.brandId(ZARA_BRAND_ID)
				.productId(PRODUCT_ID_35455)
				.date(mockDate(_2020_06_14_10_00_00))
			.build();
		
		//then
		PricesResponseDto pricesResponseDto = pricesService.getPricesInfo(requestDto);
		assertThat(pricesResponseDto).isNotNull();
		assertThat(pricesResponseDto.getPrice()).isEqualTo(PRICE_35_50);
	}
	
	@Test
	void whenGetPricesInfoForZaraAndProduct35455AndDate14_Hour16_00PriceIs_25_45() throws ParseException {
		
		// when
		PricesRequestDto requestDto = PricesRequestDto.builder()
				.brandId(ZARA_BRAND_ID)
				.productId(PRODUCT_ID_35455)
				.date(mockDate(_2020_06_14_16_00_00))
			.build();
		
		//then
		PricesResponseDto pricesResponseDto = pricesService.getPricesInfo(requestDto);
		assertThat(pricesResponseDto).isNotNull();
		assertThat(pricesResponseDto.getPrice()).isEqualTo(PRICE_25_45);
	}
	
	@Test
	void whenGetPricesInfoForZaraAndProduct35455AndDate14_Hour21_00PriceIs_35_50() throws ParseException {
		
		// when
		PricesRequestDto requestDto = PricesRequestDto.builder()
				.brandId(ZARA_BRAND_ID)
				.productId(PRODUCT_ID_35455)
				.date(mockDate(_2020_06_14_21_00_00))
			.build();
		
		//then
		PricesResponseDto pricesResponseDto = pricesService.getPricesInfo(requestDto);
		assertThat(pricesResponseDto).isNotNull();
		assertThat(pricesResponseDto.getPrice()).isEqualTo(PRICE_35_50);
	}
	
	@Test
	void whenGetPricesInfoForZaraAndProduct35455AndDate15_Hour10_00PriceIs_30_50() throws ParseException {
		
		// when
		PricesRequestDto requestDto = PricesRequestDto.builder()
				.brandId(ZARA_BRAND_ID)
				.productId(PRODUCT_ID_35455)
				.date(mockDate(_2020_06_15_10_00_00))
			.build();
		
		//then
		PricesResponseDto pricesResponseDto = pricesService.getPricesInfo(requestDto);
		assertThat(pricesResponseDto).isNotNull();
		assertThat(pricesResponseDto.getPrice()).isEqualTo(PRICE_30_50);
	}
	
	@Test
	void whenGetPricesInfoForZaraAndProduct35455AndDate16_Hour21_00PriceIs_38_95() throws ParseException {
		
		// when
		PricesRequestDto requestDto = PricesRequestDto.builder()
				.brandId(ZARA_BRAND_ID)
				.productId(PRODUCT_ID_35455)
				.date(mockDate(_2020_06_16_21_00_00))
			.build();
		
		//then
		PricesResponseDto pricesResponseDto = pricesService.getPricesInfo(requestDto);
		assertThat(pricesResponseDto).isNotNull();
		assertThat(pricesResponseDto.getPrice()).isEqualTo(PRICE_38_95);
	}

	private Date mockDate(String stringDate) throws ParseException {
		try {
			return requestDateFormat.parse(stringDate);
		} catch (ParseException pEx) {
			throw pEx;
		}
	}

}