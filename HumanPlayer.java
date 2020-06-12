import java.util.Scanner;

public class HumanPlayer implements Player{

  public boolean play(TicTacToe game){
    if(game.gameState != GameState.PLAYING){
        System.out.println("Game is not playable.");
        return false;
    }
    else{
        boolean flag = true;

        
        Scanner sc = new Scanner(System.in);
        int move = 0;
        System.out.print("\n");
        System.out.print(game.toString());
        System.out.print("\n");
        
        while(flag){
          if (game.nextPlayer() == CellValue.X){
            
              System.out.print("\nX to play: ");
            
              move = sc.nextInt();
          }
          if (game.nextPlayer()  == CellValue.O) {
            
              System.out.print("\nO to play: ");
            
              move = sc.nextInt();
          }

        
          String message = game.play(move);
          if(message != null){
            System.out.println(message);
          }
          else{
            break;
          }
        }

    return true;
    }
  }
}