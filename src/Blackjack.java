import java.util.Scanner;

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

            playerMoney -= playerBet;

            // Dealing
            System.out.println("Dealing cards...");
            playerHand.drawCard(bankDeck);
            dealerHand.drawCard(bankDeck);
            playerHand.drawCard(bankDeck);

            String playerStop = "Stand";
            String playerChoice = "";
            boolean doubled = false;
            double playerInsurance = 0;
            boolean insurance = false;
            Scanner playerChoiceIn = new Scanner(System.in);
            while(!playerChoice.equals(playerStop) && playerHand.deckValue() < 21 && !doubled){
                System.out.println("Your hand is : " + playerHand.showDeck() + " Total Value : " + playerHand.deckValue());
                System.out.println("Dealer's hand is : " + dealerHand.getCard(0) + " + HIDDEN CARD");
                if (dealerHand.deckValue() == 11){
                    System.out.println("Dealer's draw an ACE, do you want to take insurance ? Yes / No");
                    playerChoice = playerChoiceIn.next();
                    if (playerChoice.equals("Yes")){
                        playerInsurance = playerBet / 2;
                        playerMoney -= playerInsurance;
                        insurance = true;
                    }
                }
                System.out.println("Do you want to Stand, Hit or Double ?");
                playerChoice = playerChoiceIn.next();
                dealerHand.drawCard(bankDeck);
                if (playerChoice.equals("Hit")){
                    playerHand.drawCard(bankDeck);
                    System.out.println("Your hand is : " + playerHand.showDeck() + " Total Value : " + playerHand.deckValue());
                    System.out.println("Dealer's hand is : " + dealerHand.getCard(0) + " + HIDDEN CARD");
                } else if (playerChoice.equals("Double")){
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

            // Check busts
            if (playerHand.deckValue() > 21){
                System.out.println("You busted ! Better luck next time !");
            } else if (dealerHand.deckValue() > 21){
                System.out.println("Dealer has bust ! You won !");
                playerMoney += playerBet * 2;
            } else if (playerHand.deckValue() == 21 && playerHand.deckSize() == 2 && dealerHand.deckValue() == 21 && dealerHand.deckSize() != 2){
                System.out.println("You won with a blackjack !");
                playerMoney += playerBet * 2.5;
            } else if(dealerHand.deckValue() == 21 && dealerHand.deckSize() == 2 && playerHand.deckValue() == 21 && playerHand.deckSize() != 2){
                System.out.println("Dealer's draw a blackjack ! You lost !");
                if (insurance == true){
                    playerMoney += playerInsurance * 2;
                }
            } else if(playerHand.deckValue() < dealerHand.deckValue()){
                System.out.println("Dealer's hand is better than yours ! You lost !");
            } else if(playerHand.deckValue() > dealerHand.deckValue()){
                System.out.println("Your hand is better than dealer's one ! You won !");
                playerMoney += playerBet * 2;
            } else if (playerHand.deckValue() == dealerHand.deckValue()){
                System.out.println("Push !");
                playerMoney += playerBet;
            }
            playerHand.emptyDeck();
            dealerHand.emptyDeck();
            bankDeck.emptyDeck();
            bankDeck.fillDeck();
            bankDeck.Shuffle();
        }
        System.out.println("Game over!");
    }
}