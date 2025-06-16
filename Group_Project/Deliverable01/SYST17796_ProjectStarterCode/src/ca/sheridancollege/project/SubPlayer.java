package ca.sheridancollege.project;

/**
 *
 * @author siddiq73
 */

public class SubPlayer extends Player {
    private int score;
    private boolean alive = true;
    
    public boolean isAlive(){
        return alive;
    }
    
    public void eliminate(){
        alive = false;
    }

    public SubPlayer(String name) {
        super(name);
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore() {
        score++;
    }
    
        @Override
    public void play() {
        // Since subplayer extends Player and Player is an abstract class. Overriding abstract method is must.
    }
}
