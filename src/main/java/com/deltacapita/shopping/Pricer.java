package com.deltacapita.shopping;

import static java.util.Arrays.asList;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Pricer
{
  private static final Logger LOGGER = LoggerFactory.getLogger(Pricer.class);

  private final PriceSource priceSource;

  public Pricer(PriceSource priceSource)
  {
    this.priceSource = priceSource;
  }

  public int price(String... itemCodes) throws PriceNotFoundException
  {
    return price(asList(itemCodes));
  }

  public int price(Collection<String> itemCodes) throws PriceNotFoundException
  {
    LOGGER.debug("Pricing items: {}", itemCodes);

    long price = itemCodes
      .stream()
      .collect(groupingBy(identity(), counting()))
      .entrySet()
      .stream()
      .mapToLong(entry -> priceItem(entry.getKey(), entry.getValue().intValue()))
      .sum();

    if ((int)price != price)  throw new ArithmeticException("integer overflow");

    LOGGER.info("Priced items: {}, result: {}", itemCodes, price);

    return (int)price;
  }

  private int priceItem(String itemCode, int quantity)
  {
    Price price = priceSource.priceFor(itemCode);
    LOGGER.debug("Got price: {}, for item: {}", price, itemCode);

    int value = price.calculateValueFor(quantity);
    LOGGER.info("Priced item: {}, with: {}, result: {}", itemCode, price, value);

    return value;
  }
}
