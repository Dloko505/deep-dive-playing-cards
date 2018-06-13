package edu.cnm.deepdive.cards;

import edu.cnm.deepdive.cards.Deck.InsufficientCardsException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;

public class ConsoleGame {

  public static void main(String... args) throws InsufficientCardsException {
    try (Scanner scanner = new Scanner(System.in)) {
      Random rng = new SecureRandom();
      Deck deck = new Deck();
      double pot = 100;
      double bet = -1;
      while (pot > 0 && bet != 0) {
        System.out.printf("You have $%.2f in chips.%n", pot);
        while (bet < 0) {
          System.out.printf("What is your bet? [$0-$%.2f] ", Math.min(10, pot));
          while (!scanner.hasNext()) {
          }
          if (scanner.hasNextInt()) {
            int input = scanner.nextInt();
            if (input >= 0 && input <= Math.min(10, pot)) {
              bet = input;
            }
          }
          scanner.nextLine();
        }
        if (bet > 0) {
          deck.shuffle(rng);
          BlackjackHand dealer = new BlackjackDealerHand(deck);
          BlackjackHand player = new InteractiveBlackjackHand(deck, scanner);
          System.out.printf("Dealer's top card: %s.%n", dealer.getHand()[1]);
          System.out.println("Your play: ");
          if (dealer.getHand()[1].equals(Rank.Ace) && player.isBlackjack()) {
            String input = scanner.nextLine().trim().toLowerCase();
            while (input.isEmpty()) {
              System.out.print("Would you like to purchase insurance? [Y/n] ");
            }
            if (input.charAt(0) == 'y') {
              bet = -1;
            }
          }
          player.play();
          if (player.isBlackjack() && !dealer.isBlackjack()) {
            System.out.printf("You won $%.2f!%n", bet);
            pot += (bet * 1.5);
          }
          if (player.isBusted()) {
            System.out.printf("You lost $%.2f!%n", bet);
            pot -= bet;
          } else {
            dealer.play();
            System.out.printf("Dealer's hand: %s.%n", dealer);
            int comparison = player.compareTo(dealer);
            if (comparison < 0) {
              System.out.printf("You lost $%.2f!%n", bet);
              pot -= bet;
            } else if (comparison > 0) {
              System.out.printf("You won $%.2f!%n", bet);
              pot += bet;
            } else {
              System.out.printf("Push!%n");
            }
          }
          bet = -1;
        }
      }
      System.out.printf("You leave the table with $%.2f in chips.%n", pot);
    }
  }
}



