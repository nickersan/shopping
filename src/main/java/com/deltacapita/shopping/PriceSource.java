package com.deltacapita.shopping;

public interface PriceSource
{
  Price priceFor(String itemCode) throws PriceNotFoundException;
}
