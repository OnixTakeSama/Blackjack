import java.util.Collections;
import java.util.Scanner;
import java.util.*;

public class Blackjack {

    public static void main(String[] args) {

        System.out.println("Welcome on the table !");

        // Creating the deck of the bank and shuffles it
        Deck bankDeck = new Deck();
        bankDeck.fillDeck();
        bankDeck.Shuffle();

        // Creating player's hand
        Deck playerHand = new Deck();
        double playerMoney = 1000.00;

        // Creating dealer's hand
        Deck dealerHand = new Deck();

        // Game loop
        Scanner userInput = new Scanner(System.in);
        while (playerMoney > 0){
            System.out.println("You have " + playerMoney + " $");
            System.out.println("How much would you like to bet ?");
            // Bet system
            double playerBet = userInput.nextDouble();
            while (playerBet > playerMoney){
                System.out.println("You can't bet more money than you have !");
                System.out.println("You have " + playerMoney + " $");
                System.out.println("How much would you like to bet ?");
                playerBet = userInput.nextDouble();
            }

            String playerStop = "stand" ;
            String playerChoice = "";
            boolean doubled = false;
            boolean insurance = false;
            double insuranceValue = playerBet / 2.00;
            Scanner playerChoiceIn = new Scanner(System.in);

            playerMoney -= playerBet;

            // Dealing
            System.out.println("Dealing cards...");
            playerHand.drawCard(bankDeck);
            System.out.println(playerHand.deckSize());
            dealerHand.drawCard(bankDeck);
            playerHand.drawCard(bankDeck);
            if (dealerHand.deckValue() == 11){
                System.out.println("Dealer's has 11, do you want to take an insurance ? Yes / No (Cost half of your bet)");
                if (playerChoice.toLowerCase().equals("yes")){
                    insurance = true;
                    playerMoney -= insuranceValue;
                    System.out.println("You paid: " + insuranceValue + " for the insurance !");
                }
            }
            System.out.println(playerHand.deckSize());
            dealerHand.drawCard(bankDeck);
            while(!playerChoice.toLowerCase().equals(playerStop) && playerHand.deckValue() < 21 && !doubled){
                System.out.println("Your hand is : " + playerHand.showDeck() + " Total Value : " + playerHand.deckValue());
                System.out.println("Dealer's hand is : " + dealerHand.getCard(0) + " + HIDDEN CARD");
                System.out.println("Do you want to Stand, Hit or Double ?");
                playerChoice = playerChoiceIn.next();
                if (playerChoice.toLowerCase().equals("hit")){
                    playerHand.drawCard(bankDeck);
                    System.out.println("Your hand is : " + playerHand.showDeck() + " Total Value : " + playerHand.deckValue());
                    System.out.println("Dealer's hand is : " + dealerHand.getCard(0) + " + HIDDEN CARD");
                } else if (playerChoice.toLowerCase().equals("double")){
                    playerHand.drawCard(bankDeck);
                    System.out.println("Your hand is : " + playerHand.showDeck() + " Total Value : " + playerHand.deckValue());
                    System.out.println("Dealer's hand is : " + dealerHand.getCard(0) + " + HIDDEN CARD");
                    playerBet = playerBet * 2;
                    doubled = true;
                }
            }
            while (dealerHand.deckValue() < 17){
                dealerHand.drawCard(bankDeck);
                System.out.println("Dealer's hand is : " + dealerHand.showDeck() + " Total Value : " + dealerHand.deckValue());
            }
            System.out.println("Final results : ");
            System.out.println("Your hand is : " + playerHand.showDeck() + " Total Value : " + playerHand.deckValue());
            System.out.println("Dealer's hand is : " + dealerHand.showDeck() + " Total Value : " + dealerHand.deckValue());
            if (playerHand.deckValue() == 21 && playerHand.deckSize() == 2 && !(dealerHand.deckValue() == 21 && dealerHand.deckSize() != 2)){
                System.out.println("You won with a blackjack ! Winner winner, chicken dinner !");
                playerMoney += playerBet * 2.5;
            }
            else if (playerHand.deckValue() == 21 && playerHand.deckSize() == 2 && bankDeck.deckValue() == 21 && bankDeck.deckSize() == 2){
                System.out.println("Both player and dealer has a blackjack ! It's a tie... ");
                playerMoney += playerBet;
            } else if (playerHand.deckValue() < 21 && dealerHand.deckValue() == 21 && dealerHand.deckSize() == 2 && insurance) {
                System.out.println("Dealer's has a blackjack ! You lost, but you get twice the insurance !");
            } else if (playerHand.deckValue() > 21){
                System.out.println("You busted ! Better luck next time !");
            } else if (dealerHand.deckValue() > 21){
                System.out.println("Dealer has bust ! You won !");
                playerMoney += playerBet * 2;
            } else if(playerHand.deckValue() < dealerHand.deckValue()){
                System.out.println("Dealer's hand is better than yours ! You lost !");
            } else if(playerHand.deckValue() > dealerHand.deckValue()){
                System.out.println("Your hand is better than dealer's one ! You won !");
                playerMoney += playerBet * 2;
            } else if (playerHand.deckValue() == dealerHand.deckValue()){
                System.out.println("Push !");
                playerMoney += playerBet;
            }

            // Empty dealer's hand
            dealerHand.clear();

            // Empty player's hand
            playerHand.clear();

            // Reset bank deck
            bankDeck.clear();

            bankDeck.fillDeck();
            bankDeck.Shuffle();
        }
        System.out.println("Game over!");

    }
}