package com.deltacapita.shopping;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

class PriceTest
{
  @Test
  void shouldCalculatePrice()
  {
    int value = 10;
    int quantity = 2;
    int price = value * quantity;

    PriceStrategy priceStrategy = mock(PriceStrategy.class);
    when(priceStrategy.calculatePrice(quantity, value)).thenReturn(price);

    assertEquals(price, new Price(priceStrategy, value).calculateValueFor(quantity));
  }
}
