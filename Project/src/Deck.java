import java.util.ArrayList;

public class Deck {

  private ArrayList<Card> cardList;

  public Deck() {
      cardList = new ArrayList<>(52);
      for (int i = 0; i < 4; i++){
          for (int j = 0; j < 13; j++){
              cardList.add(new Card(i, j));
          }
      }
  }

  public void shuffle() {
      ArrayList<Card> temp = new ArrayList<>();
      while(!cardList.isEmpty()) {
          int newInt = (int)(Math.random()*cardList.size());
          temp.add(cardList.get(newInt));
          cardList.remove(newInt);
      }
      cardList = temp;
  }

  public void printCards() {

      int i = 1;
      for (Card c:cardList){
          System.out.println((i++) + ": " + c.getValue() + " " + c.getSuit());
      }

  }

}