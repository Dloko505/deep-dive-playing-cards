package edu.cnm.deepdive.cards;

import edu.cnm.deepdive.cards.Deck.InsufficientCardsException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BlackjackHand {

  private static final int[] VALUES = {
      1,
      2,
      3,
      4,
      5,
      6,
      7,
      8,
      9,
      10,
      10,
      10,
      10,
  };

  private List<Card> hand;
  private Deck deck;

  public BlackjackHand(Deck deck) throws InsufficientCardsException {
    hand = new LinkedList<>();
    hand.addAll(Arrays.asList(deck.deal(2)));
    this.deck = deck;
  }

  private int value() {
    boolean aceInHand = false;
    int value = 0;
    for (Card card : hand) {
      if (card.getRank() == Rank.Ace) {
        aceInHand = true;
      }
      value += VALUES[card.getRank().ordinal()];
    }
    if (value > 21) {
      value = 0;
    } else if (value <= 11 && aceInHand) {
      value += 10;
    }
    return value;
  }

}
