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
        String cardList = "";
        int i = 0;
        for (Cards card : this.cardDeck){
            cardList += "\n" + i + " - " + card.toString();
            i++;
        }
        return cardList;
    }

}
