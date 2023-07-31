package com.deltacapita.shopping;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class PriceStrategiesTest
{
  @Test
  void shouldReturnPriceForFlatStrategy()
  {
    assertEquals(10, PriceStrategies.FLAT.calculatePrice(1, 10));
    assertEquals(20, PriceStrategies.FLAT.calculatePrice(2, 10));
  }

  @Test
  void shouldThrowOnIntegerOverflowForFlatStrategy()
  {
    assertThrows(ArithmeticException.class, () -> PriceStrategies.FLAT.calculatePrice(2, Integer.MAX_VALUE));
  }

  @Test
  void shouldReturnPriceForBuyOneGetOneFreeStrategyWithNoQuantityOverflow()
  {
    assertEquals(10, PriceStrategies.BUY_ONE_GET_ONE_FREE.calculatePrice(2, 10));
    assertEquals(20, PriceStrategies.BUY_ONE_GET_ONE_FREE.calculatePrice(4, 10));
  }

  @Test
  void shouldReturnPriceForBuyOneGetOneFreeStrategyWithQuantityOverflow()
  {
    assertEquals(10, PriceStrategies.BUY_ONE_GET_ONE_FREE.calculatePrice(1, 10));
    assertEquals(20, PriceStrategies.BUY_ONE_GET_ONE_FREE.calculatePrice(3, 10));
  }

  @Test
  void shouldThrowOnIntegerOverflowForBuyOneGetOneFreeStrategy()
  {
    assertThrows(ArithmeticException.class, () -> PriceStrategies.BUY_ONE_GET_ONE_FREE.calculatePrice(3, Integer.MAX_VALUE));
    assertThrows(ArithmeticException.class, () -> PriceStrategies.BUY_ONE_GET_ONE_FREE.calculatePrice(4, Integer.MAX_VALUE));
  }

  @Test
  void shouldReturnPriceForThreeForTwoStrategyNoQuantityOverflow()
  {
    assertEquals(20, PriceStrategies.THREE_FOR_TWO.calculatePrice(3, 10));
    assertEquals(40, PriceStrategies.THREE_FOR_TWO.calculatePrice(6, 10));
  }

  @Test
  void shouldReturnPriceForThreeForTwoStrategyQuantityOverflow()
  {
    assertEquals(30, PriceStrategies.THREE_FOR_TWO.calculatePrice(4, 10));
    assertEquals(40, PriceStrategies.THREE_FOR_TWO.calculatePrice(5, 10));
  }

  @Test
  void shouldThrowOnIntegerOverflowForThreeForTwoStrategy()
  {
    assertThrows(ArithmeticException.class, () -> PriceStrategies.THREE_FOR_TWO.calculatePrice(2, Integer.MAX_VALUE));
    assertThrows(ArithmeticException.class, () -> PriceStrategies.THREE_FOR_TWO.calculatePrice(3, Integer.MAX_VALUE));
    assertThrows(ArithmeticException.class, () -> PriceStrategies.THREE_FOR_TWO.calculatePrice(4, Integer.MAX_VALUE));
  }
}
