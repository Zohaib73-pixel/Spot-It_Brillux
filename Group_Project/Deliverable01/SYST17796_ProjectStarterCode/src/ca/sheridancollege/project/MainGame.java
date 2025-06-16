package ca.sheridancollege.project;

/**
 *
 * @author siddiq73
 */

import java.util.*;

public class MainGame extends Game {

    private SubPlayer player;
    private final List<String> symbolPool = Arrays.asList(
            "Sun", "Moon", "Tree", "Fish", "Star", "Leaf", "Car", "Cat", "Book", "Apple"
    );

    public MainGame(String name) {
        super(name);
    }

    @Override
    public void play() {
        Scanner scanner = new Scanner(System.in);

        // Player registration
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        player = new SubPlayer(name);

        System.out.println("\nWelcome, " + name + "! Let's play Spot It!");

        boolean continuePlaying = true;

        while (continuePlaying) {
            // Generate 3 cards with one common symbol
            List<SubCard> cards = generateThreeCardsWithOneCommonSymbol();

            System.out.println("\nHere are your cards:");
            for (int i = 0; i < cards.size(); i++) {
                System.out.println("Card " + (i + 1) + ": " + cards.get(i));
            }

            // Ask for guess
            System.out.print("Enter the common symbol: ");
            String guess = scanner.nextLine();

            // Check answer
            if (cards.get(0).contains(guess) && cards.get(1).contains(guess) && cards.get(2).contains(guess)) {
                System.out.println("✅ Correct!");
                player.increaseScore();
            } else {
                System.out.println("❌ Incorrect! Try again.");
            }

            System.out.println("Current Score: " + player.getScore());

            System.out.print("\nPlay another round? (yes/no): ");
            String response = scanner.nextLine().toLowerCase();
            if (!response.equals("yes")) {
                continuePlaying = false;
            }
        }

        declareWinner();
    }

    @Override
    public void declareWinner() {
        System.out.println("\nGame Over!");
        System.out.println("Thanks for playing, " + player.getName() + "!");
        System.out.println("Your Final Score: " + player.getScore());
    }

    private List<SubCard> generateThreeCardsWithOneCommonSymbol() {
        Random random = new Random();
        String commonSymbol = symbolPool.get(random.nextInt(symbolPool.size()));

        List<SubCard> cards = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Set<String> symbols = new HashSet<>();
            symbols.add(commonSymbol);

            while (symbols.size() < 5) {
                String symbol = symbolPool.get(random.nextInt(symbolPool.size()));
                symbols.add(symbol);
            }

            cards.add(new SubCard(symbols.toArray(new String[0])));
        }

        return cards;
    }
}