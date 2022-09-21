public class Blackjack {

    public static void main(String[] args) {

        System.out.println("Welcome on the table !");

        Deck playingDeck = new Deck();
        playingDeck.fillDeck();
        playingDeck.Shuffle();

    }
}