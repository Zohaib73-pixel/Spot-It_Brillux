package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * @author siddiq73
 */
public class GroupOfCards {
     //The group of cards, stored in an ArrayList
    private ArrayList<Card> cards;
    private int size;//the size of the grouping

    public GroupOfCards(int size) {
        this.size = size;
        this.cards = new ArrayList<>();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * @return the size of the group of cards
     */
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
}
