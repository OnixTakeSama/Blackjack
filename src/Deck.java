import java.util.ArrayList;
import java.util.Collections;

public class Deck{

    private ArrayList<Cards> cardDeck;

    public Deck() {
        this.cardDeck = new ArrayList<>();
    }

    public void fillDeck() {
        for (Suits cardsSuit : Suits.values()) {
            for (Values cardsValue : Values.values()) {
                this.cardDeck.add(new Cards(cardsSuit, cardsValue));
            }
        }
    }

    public void Shuffle() {
        Collections.shuffle(this.cardDeck);
    }

    public String showDeck() {
        String res = "";
        for (Cards cards : cardDeck) {
            res += cards + " | ";
        }
        return res;
    }

    public void removeCard(int i) {
        this.cardDeck.remove(i);
    }

    public Cards getCard(int i) {
        return this.cardDeck.get(i);
    }

    public void addCard(Deck addedCard) {
        this.cardDeck.add(addedCard.getCard(0));
    }

    public void drawCard(Deck cardFrome) {
        this.cardDeck.add(cardFrome.getCard(0));
        cardFrome.removeCard(0);
    }

    public void clear() {
        this.cardDeck.clear();
    }

    public int deckValue() {
        int value = 0;
        int aces = 0;
        for (Cards cards : this.cardDeck) {
            switch (cards.getValue()) {
                case TWO:
                    value += 2;
                    break;
                case THREE:
                    value += 3;
                    break;
                case FOUR:
                    value += 4;
                    break;
                case FIVE:
                    value += 5;
                    break;
                case SIX:
                    value += 6;
                    break;
                case SEVEN:
                    value += 7;
                    break;
                case EIGHT:
                    value += 8;
                    break;
                case NINE:
                    value += 9;
                    break;
                case TEN:
                    value += 10;
                    break;
                case JACK:
                    value += 10;
                    break;
                case QUEEN:
                    value += 10;
                    break;
                case KING:
                    value += 10;
                    break;
                case ACE:
                    aces += 1;
                    break;
            }
            for (int i = 0; i < aces; i++) {
                if (value > 10) {
                    value += 1;
                } else {
                    value += 11;
                }
            }
        }
        return value;
    }

    public int deckSize(){
        return this.cardDeck.size();
    }
}