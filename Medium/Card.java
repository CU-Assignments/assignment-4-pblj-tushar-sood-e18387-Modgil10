import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class Card {
    private String rank;   
    private String symbol; 

    public Card(String rank, String symbol) {
        this.rank = rank;
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
    public String toString() {
        return rank + " of " + symbol;
    }
}

public class CardCollection {
    private static final List<Card> cardList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nCard Collection System");
            System.out.println("1. Add a Card");
            System.out.println("2. Search Cards by Symbol");
            System.out.println("3. Display All Cards");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1 -> addCard();
                case 2 -> searchCardsBySymbol();
                case 3 -> displayCards();
                case 4 -> {
                    System.out.println("Exiting Card Collection System. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }
    private static void addCard() {
        System.out.print("Enter card rank (e.g., Ace, 2, King): ");
        String rank = scanner.nextLine();

        System.out.print("Enter card symbol (e.g., Hearts, Spades): ");
        String symbol = scanner.nextLine();

        cardList.add(new Card(rank, symbol));
        System.out.println("Card added successfully!");
    }
    private static void searchCardsBySymbol() {
        System.out.print("Enter the symbol to search for (e.g., Hearts, Spades): ");
        String symbol = scanner.nextLine();

        List<Card> foundCards = new ArrayList<>();
        for (Card card : cardList) {
            if (card.getSymbol().equalsIgnoreCase(symbol)) {
                foundCards.add(card);
            }
        }

        if (foundCards.isEmpty()) {
            System.out.println("No cards found for symbol: " + symbol);
        } else {
            System.out.println("\nCards with symbol " + symbol + ":");
            for (Card card : foundCards) {
                System.out.println(card);
            }
        }
    }
    private static void displayCards() {
        if (cardList.isEmpty()) {
            System.out.println("No cards in the collection.");
        } else {
            System.out.println("\nAll Stored Cards:");
            for (Card card : cardList) {
                System.out.println(card);
            }
        }
    }
}
