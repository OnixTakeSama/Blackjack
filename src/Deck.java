import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Cards> cardDeck;

    public Deck(){
        this.cardDeck = new ArrayList<>();
    }

    public void fillDeck(){
        for (Suits cardsSuit : Suits.values()){
            for (Values cardsValue : Values.values()){
                this.cardDeck.add(new Cards(cardsSuit, cardsValue));
            }
        }
    }

    public void Shuffle(){
        Collections.shuffle(this.cardDeck);
    }

    public String showDeck(){
        String res = "";
        for (int i = 0; i < cardDeck.size();i++){
            res += cardDeck.get(i) + " | ";
        }
        return res;
    }

    public void removeCard(int i){
        this.cardDeck.remove(i);
    }

    public Cards getCard(int i){
        return this.cardDeck.get(i);
    }
    public void addCard(Deck addedCard){
        this.cardDeck.add(addedCard.getCard(0));
    }

    public void drawCard(Deck cardFrome){
        this.cardDeck.add(cardFrome.getCard(0));
        cardFrome.removeCard(0);
    }

    public int deckValue(Deck deck){

    }
}
