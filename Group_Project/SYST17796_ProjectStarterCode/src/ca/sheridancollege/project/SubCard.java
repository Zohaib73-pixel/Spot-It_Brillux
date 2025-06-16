
package ca.sheridancollege.project;

/**
 *
 * @author siddiq73
 */
import java.util.Arrays;

public class SubCard extends Card {
    private String[] symbols;

    public SubCard(String[] symbols) {
        this.symbols = symbols;
    }

    public String[] getSymbols() {
        return symbols;
    }

    public boolean contains(String symbol) {
        for (String s : symbols) {
            if (s.equalsIgnoreCase(symbol)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return Arrays.toString(symbols);
    }
}

