package com.grid.core.prices.restapi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.grid.core.prices.restapi.controller.PricesApiController;
import com.grid.core.prices.restapi.dto.PricesRequestDto;
import com.grid.core.prices.restapi.dto.PricesResponseDto;
import com.grid.core.prices.restapi.service.PricesService;

@WebMvcTest(PricesApiController.class)
class PricesApiControllerTests {
	
    private static final String END_POINT_1 = "/prices/brand/1/product/35455/date/2020-06-14 10.00.00";
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private PricesService pricesService;
    
    @Test
    void whenRequestWithCorrectParamsReturn200IsOk() throws Exception {
    	
    	PricesRequestDto mockRequest = Mockito.mock(PricesRequestDto.class);
    	Mockito.when(pricesService.getPricesInfo(mockRequest)).thenReturn(mockPricesResponseDto());
    	
        mockMvc.perform(get(END_POINT_1).contentType("application/json"))
                .andExpect(status().isOk())
                .andDo(print());
    }
    
    private PricesResponseDto mockPricesResponseDto() {
    	return PricesResponseDto.builder().build();
    }
    
}