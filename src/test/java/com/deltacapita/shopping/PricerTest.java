package com.deltacapita.shopping;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

class PricerTest
{
  @Test
  void shouldPriceSingleItem()
  {
    String itemCode = "Test";
    int expectedPrice = 10;

    Price price = mock(Price.class);
    when(price.calculateValueFor(1)).thenReturn(expectedPrice);

    PriceSource priceSource = mock(PriceSource.class);
    when(priceSource.priceFor(itemCode)).thenReturn(price);

    assertEquals(expectedPrice, new Pricer(priceSource).price(itemCode));
  }

  @Test
  void shouldPriceMultipleDifferentItems()
  {
    String itemCode1 = "Test1";
    String itemCode2 = "Test2";
    int expectedPrice1 = 10;
    int expectedPrice2 = 20;

    Price price1 = mock(Price.class);
    when(price1.calculateValueFor(1)).thenReturn(expectedPrice1);

    Price price2 = mock(Price.class);
    when(price2.calculateValueFor(1)).thenReturn(expectedPrice2);

    PriceSource priceSource = mock(PriceSource.class);
    when(priceSource.priceFor(itemCode1)).thenReturn(price1);
    when(priceSource.priceFor(itemCode2)).thenReturn(price2);

    assertEquals(expectedPrice1 + expectedPrice2, new Pricer(priceSource).price(itemCode1, itemCode2));
  }

  @Test
  void shouldPriceMultipleIdenticalItems()
  {
    String itemCode = "Test";
    int expectedPrice = 20;

    Price price = mock(Price.class);
    when(price.calculateValueFor(2)).thenReturn(expectedPrice);

    PriceSource priceSource = mock(PriceSource.class);
    when(priceSource.priceFor(itemCode)).thenReturn(price);

    assertEquals(expectedPrice, new Pricer(priceSource).price(itemCode, itemCode));
  }

  @Test
  void shouldThrowWhenPriceNotFound()
  {
    String itemCode = "Test";

    PriceSource priceSource = mock(PriceSource.class);
    when(priceSource.priceFor(itemCode)).thenThrow(new PriceNotFoundException("Testing"));

    assertThrows(PriceNotFoundException.class, () -> new Pricer(priceSource).price(itemCode));
  }

  @Test
  void shouldThrowOnIntegerOverflow()
  {
    String itemCode1 = "Test1";
    String itemCode2 = "Test2";

    Price price = mock(Price.class);
    when(price.calculateValueFor(anyInt())).thenReturn(Integer.MAX_VALUE);

    PriceSource priceSource = mock(PriceSource.class);
    when(priceSource.priceFor(itemCode1)).thenReturn(price);
    when(priceSource.priceFor(itemCode2)).thenReturn(price);

    assertThrows(ArithmeticException.class, () -> new Pricer(priceSource).price(itemCode1, itemCode2));
  }
}
