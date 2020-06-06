import java.util.Scanner;

public class HumanPlayer implements Player{

  public boolean play(TicTacToe game){
    if(game.gameState != GameState.PLAYING){
        System.out.println("Game is not playable.");
        return false;
    }
    else{
        Scanner sc = new Scanner(System.in);
        int move = 0;
        System.out.println(game.toString());

        if (game.nextPlayer() == CellValue.X){
            System.out.println("X to play: ");
            move = sc.nextInt();
        }
        if (game.nextPlayer()  == CellValue.O) {
            System.out.println("O to play: ");
            move = sc.nextInt();
        }
        System.out.println(game.play(move));
        return true;

    }
  }

}