import java.util.ArrayList;
import java.util.List;

public class DiceGame {

    private final int NUMBER_OF_DICES = 2;
    private final int WIN = 7;
    private final int MAX_ROLLS_IN_A_GAME = 3;
    private int rolls;
    private String playerName;
    private int wonGames;
    private List<Dice> dices = new ArrayList();
    private Printer printer;

    public DiceGame(Printer p, Dice d1, Dice d2){
        printer = p;
        dices.add(d1);
        dices.add(d2);
    }

    public void setPlayer(String name){
        this.playerName = name;
    }

    public void startGame(){
        rolls = 0;
        wonGames = 0;
    }

    public int roll() throws Exception {

        rolls++;

        if(rolls > MAX_ROLLS_IN_A_GAME){
            throw new Exception("ONLY three rolls in a game");
        }

        // Get dice rolls
        int result = dices.get(0).roll();
        result += dices.get(1).roll();

        if(result == WIN){
            wonGames++;
        }

        if(rolls == MAX_ROLLS_IN_A_GAME) {
            String text = (wonGames > 0) ? "Won the game, with: " + wonGames : "Lost the game";
            printer.print(playerName + " you " + text);
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        DiceGame game = new DiceGame(new Printer(), new Dice(), new Dice());
        game.setPlayer("Kurt");
        game.setPlayer("jens");
        System.out.println(game.roll());
        System.out.println(game.roll());
        System.out.println(game.roll());
    }

}
