package edu.cnm.deepdive.cards;

import static org.junit.jupiter.api.Assertions.*;

import edu.cnm.deepdive.cards.Suit;

class SuitTest {

  private static final String[] uniSuit = {
      "\u2663",
      "\u2666",
      "\u2665",
      "\u2660"
  };

  @org.junit.jupiter.api.Test
  void testToString() {
    for (Suit suit : Suit.values()) {
      assertEquals(suit.toString(), uniSuit[suit.ordinal()]);
      System.out.println(suit);
    }
  }
}