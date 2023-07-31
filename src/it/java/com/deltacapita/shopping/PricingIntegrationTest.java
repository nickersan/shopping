package com.deltacapita.shopping;

import static com.deltacapita.shopping.PriceStrategies.BUY_ONE_GET_ONE_FREE;
import static com.deltacapita.shopping.PriceStrategies.FLAT;
import static com.deltacapita.shopping.PriceStrategies.THREE_FOR_TWO;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PricingIntegrationTest
{
  private static final String ITEM_CODE_APPLE = "Apple";
  private static final String ITEM_CODE_BANANA = "Banana";
  private static final String ITEM_CODE_LIME = "Lime";
  private static final String ITEM_CODE_MELON = "Melon";

  private static final Price PRICE_APPLE = new Price(FLAT, 35);
  private static final Price PRICE_BANANA = new Price(FLAT, 20);
  private static final Price PRICE_LIME = new Price(THREE_FOR_TWO, 15);
  private static final Price PRICE_MELON = new Price(BUY_ONE_GET_ONE_FREE, 50);

  private Pricer pricer;

  @BeforeEach
  void InitializePricer()
  {
    this.pricer = new Pricer(
      itemCode ->
      {
        if (ITEM_CODE_APPLE.equalsIgnoreCase(itemCode)) return PRICE_APPLE;
        if (ITEM_CODE_BANANA.equalsIgnoreCase(itemCode)) return PRICE_BANANA;
        if (ITEM_CODE_LIME.equalsIgnoreCase(itemCode)) return PRICE_LIME;
        if (ITEM_CODE_MELON.equalsIgnoreCase(itemCode)) return PRICE_MELON;

        throw new PriceNotFoundException("Price not found for: " + itemCode);
      }
    );
  }

  @Test
  void shouldPriceSingleApple()
  {
    assertEquals(35, this.pricer.price(ITEM_CODE_APPLE));
  }

  @Test
  void shouldPriceMultipleApples()
  {
    assertEquals(70, this.pricer.price(ITEM_CODE_APPLE, ITEM_CODE_APPLE));
  }

  @Test
  void shouldPriceSingleBanana()
  {
    assertEquals(20, this.pricer.price(ITEM_CODE_BANANA));
  }

  @Test
  void shouldPriceMultipleBananas()
  {
    assertEquals(40, this.pricer.price(ITEM_CODE_BANANA, ITEM_CODE_BANANA));
  }

  @Test
  void shouldPriceSingleLime()
  {
    assertEquals(15, this.pricer.price(ITEM_CODE_LIME));
  }

  @Test
  void shouldPriceMultipleLimes()
  {
    assertEquals(30, this.pricer.price(ITEM_CODE_LIME, ITEM_CODE_LIME));
    assertEquals(30, this.pricer.price(ITEM_CODE_LIME, ITEM_CODE_LIME, ITEM_CODE_LIME));
    assertEquals(45, this.pricer.price(ITEM_CODE_LIME, ITEM_CODE_LIME, ITEM_CODE_LIME, ITEM_CODE_LIME));
  }

  @Test
  void shouldPriceSingleMelon()
  {
    assertEquals(50, this.pricer.price(ITEM_CODE_MELON));
  }

  @Test
  void shouldPriceMultipleMelons()
  {
    assertEquals(50, this.pricer.price(ITEM_CODE_MELON, ITEM_CODE_MELON));
    assertEquals(100, this.pricer.price(ITEM_CODE_MELON, ITEM_CODE_MELON, ITEM_CODE_MELON));
  }

  @Test
  void shouldPriceSingleMixed()
  {
    assertEquals(120, this.pricer.price(ITEM_CODE_APPLE, ITEM_CODE_BANANA, ITEM_CODE_LIME, ITEM_CODE_MELON));
  }

  @Test
  void shouldPriceMultipleMixed()
  {
    assertEquals(
      190,
      this.pricer.price(
        ITEM_CODE_APPLE,
        ITEM_CODE_BANANA,
        ITEM_CODE_BANANA,
        ITEM_CODE_APPLE,
        ITEM_CODE_LIME,
        ITEM_CODE_MELON,
        ITEM_CODE_LIME,
        ITEM_CODE_LIME,
        ITEM_CODE_MELON
      )
    );
  }
}
