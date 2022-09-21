public class Cards {

    private Suits suit;
    private Values value;

    public Cards(Suits suit, Values value){
        this.suit = suit;
        this.value = value;
    }

    public Values getValue(){
        return this.value;
    }

    public Suits getSuit(){
        return this.suit;
    }

    @Override
    public String toString(){
        return this.suit.toString() + " - " + this.value.toString();
    }
}
