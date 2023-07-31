package com.deltacapita.shopping;

import static java.lang.Math.addExact;
import static java.lang.Math.multiplyExact;

public enum PriceStrategies implements PriceStrategy
{
  FLAT
    {
      @Override
      public int calculatePrice(int quantity, int price)
      {
        return multiplyExact(quantity, price);
      }
    },

  BUY_ONE_GET_ONE_FREE
    {
      @Override
      public int calculatePrice(int quantity, int price)
      {
        return calculateDiscountPrice(quantity, price, 2, 1);
      }
    },

  THREE_FOR_TWO
    {
      @Override
      public int calculatePrice(int quantity, int price)
      {
        return calculateDiscountPrice(quantity, price, 3, 2);
      }
    };

  @Override
  public abstract int calculatePrice(int quantity, int price);

  protected static int calculateDiscountPrice(int quantity, int price, int offerQuantity, int discountQuantity)
  {
    return addExact(
      multiplyExact(quantity / offerQuantity, multiplyExact(price, discountQuantity)),
      multiplyExact(quantity % offerQuantity, price)
    );
  }
}
