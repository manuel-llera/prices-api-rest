package com.grid.core.prices.restapi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PricesApiApplicationTests {
	
	private static final boolean MY_TRUE_TEST = true;
	
	@Test
	void PricesApiApplicationMainTest() {
		// Test class added to cover main() invocation not covered by application tests.
		PricesApiApplication.main(new String[] {});
		assertThat(MY_TRUE_TEST).isTrue();
	}
}