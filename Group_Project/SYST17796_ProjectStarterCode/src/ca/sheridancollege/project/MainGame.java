package ca.sheridancollege.project;

/**
 *
 * @author siddiq73
 */

import java.util.*;

public class MainGame extends Game {
    
//    ArrayList of type subplayers to store players of the game
    private ArrayList<SubPlayer> player = new ArrayList<>();
//    List of the possible objects on the cards to display.
    private final List<String> symbolPool = Arrays.asList(
            "Sun", "Moon", "Star", "Tree", "Fish", "Eye", "Heart", "Car", "Book", "Key",
    "Bell", "Fire", "Cloud", "Apple", "Crown", "Horse", "Clock", "Ghost",
    "Dog", "Boat", "Anchor", "Flower", "Mountain", "House", "Plane", "Rocket",
    "Leaf", "Cup", "Ring", "Hat", "Lamp", "Banana", "Train", "Ship"
    );
    
    private int potatoIndex;
    
    public MainGame(String name) {
        super(name);
    }

    @Override
    public void play() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\nWelcome players. Let's play Spot It!");
        System.out.println("Before directly jumping into the game lets know how the game works: "
                + "\n1. Enter the number of players and their initials one by one."
                + "\n2. Player number 1 will be carrying the hot potato and will spot the first common object."
                + "\n3. Player number 1 will get the choice of chosing any other player to pass the potato."
                + "\n4. The chosen one will continue the game."
                + "\n5. Each wrong answer will result in elimination of that player. No second chance."
                + "\n6. Last one standing will be the winner of the game."
                + "\n Sounds Interesting? So without further delay, Lets begin to Spot-It!!");
        
        // Register multiple players
        System.out.print("How many players (players range is between 3 to 6): ");
        int numPlayers = Integer.parseInt(scanner.nextLine());
        while (numPlayers < 3 || numPlayers > 6) {
            System.out.print("Please enter a number between 3 and 6: ");
            numPlayers = Integer.parseInt(scanner.nextLine());
        }

        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Enter name for Player " + i + ": ");
            String name = scanner.nextLine();
            player.add(new SubPlayer(name));
        }

        // Random starting player
        Random random = new Random();
        potatoIndex = random.nextInt(player.size());
        System.out.println("Lets begin! \n" + player.get(potatoIndex).getName() + " starts with the Hot Potato!");

        // Game loop
        while (countAlivePlayers() > 1) {
            SubPlayer currentPlayer = player.get(potatoIndex);
            if (!currentPlayer.isAlive()) {
                potatoIndex = getNextAlivePlayerIndex(potatoIndex);
                continue;
            }

            System.out.println("\n " + currentPlayer.getName() + ", it's your turn!");
            List<SubCard> cards = generateThreeCardsWithOneCommonSymbol();
            String commonSymbol = findCommonSymbol(cards.get(0), cards.get(1), cards.get(2));

            System.out.println("Card 1: " + cards.get(0));
            System.out.println("Card 2: " + cards.get(1));
            System.out.println("Card 3: " + cards.get(2));
            System.out.print("Enter the common symbol quickly: ");
            String guess = scanner.nextLine().trim();

            if (cards.get(0).contains(guess) && cards.get(1).contains(guess)) {
                System.out.println("You Smashed it! That's Correct!");

                // Pass the potato
                System.out.println("Choose a player to pass the Hot Potato to:");
                System.out.println(getAlivePlayerNamesExcept(currentPlayer.getName()));
                String targetName = scanner.nextLine().trim();
                int targetIndex = findPlayerIndexByName(targetName);

                if (targetIndex != -1 && player.get(targetIndex).isAlive()) {
                    potatoIndex = targetIndex;
                    System.out.println("Hot Potato passed to " + player.get(targetIndex).getName() + "!");
                } else {
                    System.out.println("Invalid choice. Potato moves clockwise.");
                    potatoIndex = getNextAlivePlayerIndex(potatoIndex);
                }
            } else {
                System.out.println("Wrong! The correct symbol was: " + commonSymbol);
                currentPlayer.eliminate();
                System.out.println(currentPlayer.getName() + " has been eliminated!");
                potatoIndex = getNextAlivePlayerIndex(potatoIndex);
            }
        }

        declareWinner();
        scanner.close();
    }

    @Override
    public void declareWinner() {
        for (SubPlayer p : player) {
            if (p.isAlive()) {
                System.out.println("\nCongratulations! " + p.getName() + " is the last one standing and wins Spot-It!");
                return;
            }
        }
        System.out.println("Game ended with no winner.");
    }

    private int countAlivePlayers() {
        int count = 0;
        for (SubPlayer p : player) {
            if (p.isAlive()) count++;
        }
        return count;
    }

    private int getNextAlivePlayerIndex(int currentIndex) {
        int next = (currentIndex + 1) % player.size();
        while (!player.get(next).isAlive()) {
            next = (next + 1) % player.size();
        }
        return next;
    }

    private int findPlayerIndexByName(String name) {
        for (int i = 0; i < player.size(); i++) {
            if (player.get(i).getName().equalsIgnoreCase(name)) return i;
        }
        return -1;
    }

    private String getAlivePlayerNamesExcept(String exclude) {
        StringBuilder sb = new StringBuilder();
        for (SubPlayer p : player) {
            if (p.isAlive() && !p.getName().equalsIgnoreCase(exclude)) {
                sb.append(p.getName()).append("  ");
            }
        }
        return sb.toString().trim();
    }

    private List<SubCard> generateThreeCardsWithOneCommonSymbol() {
        Random random = new Random();
        String commonSymbol = symbolPool.get(random.nextInt(symbolPool.size()));

        List<SubCard> cards = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Set<String> symbols = new HashSet<>();
            symbols.add(commonSymbol);
            while (symbols.size() < 5) {
                symbols.add(symbolPool.get(random.nextInt(symbolPool.size())));
            }
            cards.add(new SubCard(symbols.toArray(new String[0])));
        }

        return cards;
    }

    private String findCommonSymbol(SubCard c1, SubCard c2, SubCard c3) {
        for (String s1 : c1.getSymbols()) {
            for (String s2 : c2.getSymbols()) {
                for (String s3 : c3.getSymbols()) {
                    if (s1.equalsIgnoreCase(s2) & s1.equalsIgnoreCase(s3)) {
                        return s1;
                    }
                }
            }
        }
        return null;
    }
}