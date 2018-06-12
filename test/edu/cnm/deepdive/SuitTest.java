package edu.cnm.deepdive;

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
  void testtoString() {
    for (Suit suit : Suit.values()) {
      suit.equals(uniSuit);
    }
  }
}