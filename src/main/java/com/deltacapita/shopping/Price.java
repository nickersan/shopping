package com.deltacapita.shopping;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString()
public class Price
{
  private final PriceStrategy strategy;
  private final int value;

  public int calculateValueFor(int quantity)
  {
    return this.strategy.calculatePrice(quantity, this.value);
  }
}
