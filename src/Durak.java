import java.util.InputMismatchException;
import java.util.Scanner;

public class Durak {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static void main(String[] args) {
        int exc;
        Scanner scanner = new Scanner(System.in);
        Card card = new Card();
        card.setupgame();
        System.out.println(ANSI_GREEN + "Выберете сколько у каждого игрока будет карт в руке в начале игры: " + ANSI_RESET);
        try {
            card.hand(scanner.nextInt());
        } catch (InputMismatchException | IndexOutOfBoundsException e) {
            exc = 0;
            card.hand(exc);
        }
        while(!card.myhand.isEmpty() & !card.computerhand.isEmpty()) {
            card.startgame();
        }
        card.endgame();
    }
}

