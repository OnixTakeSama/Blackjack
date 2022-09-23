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
            dealerHand.drawCard(bankDeck);

            String playerStop = "Stand";
            String playerChoice = "";
            Scanner playerChoiceIn = new Scanner(System.in);
            while(!playerChoice.equals(playerStop)){
                System.out.println("Your hand is : " + playerHand.showDeck());
                System.out.println("Dealer's hand is : " + dealerHand.getCard(0) + " + HIDDEN CARD");
                System.out.println("Do you want to Stand, Hit or Double ?");
                playerChoice = playerChoiceIn.next();
                if (playerChoice.equals("Hit")){
                    playerHand.drawCard(bankDeck);
                    System.out.println("Your hand is : " + playerHand.showDeck());
                }
                break;
            }
        }
        System.out.println("Game over!");
    }
}