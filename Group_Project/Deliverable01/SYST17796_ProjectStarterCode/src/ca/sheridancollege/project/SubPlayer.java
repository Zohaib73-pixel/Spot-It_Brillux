package ca.sheridancollege.project;

/**
 *
 * @author siddiq73
 */

public class SubPlayer extends Player {
    private int score;

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
        // Game logic handled in SpotItGame
    }
}

