import java.util.Scanner;
public class Game {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String start;
        GameStart gameStart= new GameStart();
        System.out.println("-------------------------------------------------------");
        System.out.println("Welcome to \uD83E\uDD16 Humans vs Goblins \uD83D\uDC7D ");
        System.out.println();
        System.out.println("Instructions: ");
        System.out.println("- This is a game by turns, every turn you can move one position up (w) ,dow (s) ,left (a),right (d)");
        System.out.println("- When a robot and alien touches, the battle will start");
        System.out.println("- In combat, system will select the damage randomly");
        System.out.println("- The red circle is the character that you will move");
        System.out.println("The game ends in one of two cases:");
        System.out.println("    1) The game ends when all Robots die");
        System.out.println("    2) The game ends when all aliens die");
        System.out.println("-------------------------------------------------------");

        System.out.println("Press Enter Key to start");
        start = scanner.nextLine();
        if (start.equals("")){
            gameStart.play(scanner);
        }else{
            System.out.println("Exiting game");
        }
        scanner.close();
    }
}
