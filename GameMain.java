import java.util.Scanner;

public class GameMain {

  /**
   * <b>main</b> of the application. Creates the instance of  TicTacToe
   * and starts the game. If two parameters lines  and columns
   * are passed, they are used. If the paramters lines, columns
   * and win are passed, they are used.
   * Otherwise, a default value is used. Defaults values (3) are also
   * used if the paramters are too small (less than 2).
   * Here, we assume that the command lines arguments are indeed integers
   *
   * @param args command lines parameters
   */
  public static void main(String[] args) {

    Player p1 = validatePlayer(args, 0, "h");
    Player p2 = validatePlayer(args, 1, "ic");

    int lines = validateInt(args, 2);
    int columns = validateInt(args, 3);
    int wins = validateInt(args, 4);

    if (args.length > 5){
        System.out.println("Too many arguments. Only the first 5 are used.");
    }

    TicTacToe game = new TicTacToe(lines,columns,wins);
    Player[] players = new Player[] {p1, p2};

    //intial set of conditions for the "first move"
    boolean playGame = true;
    boolean firstMove = true;
    int currentPlayer = 0;
    

    Scanner scan = new Scanner(System.in);

    //game loop, only ends if someone wins and player does not want to play again.
    while(playGame){
      //following statements only if first move or game restarted.
      if(firstMove){
        //intitializes new game
        game = new TicTacToe(lines,columns,wins);
        //randomly picks a player based on the array of players 
        currentPlayer = (int)(players.length*Math.random());
        //prompts which player's turn it is
        promptNext(currentPlayer);
        //the current player will play
        players[currentPlayer].play(game);
        //changes who the current player is after the last player has made a move.
        currentPlayer = nextPlayer(currentPlayer);
        //prompts the next player and sets the first move boolean to false so that this chain of statements isnt run again.
        promptNext(currentPlayer);
        firstMove = false;
        continue;
      }

      //current player plays and the following switch statement is for
      //if the game is a draw or win has occured, the game will ask the user if they want to play again. 
      //if the user wants to play again,
      if(players[currentPlayer].play(game) == true){
        switch(game.gameState){
          case XWIN: 
            //assumes player does not want to play again.
            playGame = false;
            System.out.println("Game Over\n");
            System.out.println(game.toString());
            System.out.println("\nResult: XWIN"); 
            System.out.println("Play Again (y)?: ");
            char playAgain = scan.next().charAt(0);
            if(playAgain == 'y'){
              //if they want to play again restart from scratch.
              playGame = true;
              firstMove = true;
            }
            else{
              playGame = false;
            }
            break;

          case OWIN: 
            System.out.println("Game Over\n");
            System.out.println(game.toString());
            System.out.println("\nResult: OWIN"); 
            System.out.println("Play Again (y)?: ");
            playAgain = scan.next().charAt(0);
            if(playAgain == 'y'){
              playGame = true;
              firstMove = true;
            }
            else{
              playGame = false;
            }
            break;

          case DRAW: 
            System.out.println("Game Over\n");
            System.out.println(game.toString());
            System.out.println("\nResult: DRAW"); 
            System.out.println("Play Again (y)?: ");
            playAgain = scan.next().charAt(0);
            if(playAgain == 'y'){
              playGame = true;
              firstMove = true;
            }
            else{
              playGame = false;
            }
            break;

          default:
            //changes who the next player is and prompts them to play.
            currentPlayer = nextPlayer(currentPlayer);
            promptNext(currentPlayer);
            break;

        }
          
      }
      

    }
  }

  /**
   * Toggle the current player to be the next player
   * @param currentPlayer the current player index (0 or 1)
   * @return The next player index (1 or 0)
   */
  private static int nextPlayer(int currentPlayer) {
    return currentPlayer == 1 ? 0 : 1;
  }

  /**
   * Extract the player from the arguments
   * "h" for HumanPlayer
   * "ic" for ComputerInOrderPlayer
   * "rc" for ComputerRandomPlayer
   *
   * @param args The command lines parameters
   * @param index Which index to parse
   * @param defaultIfMissing Default value if no input at that index
   * @return String The value at that position of the default if not available
   */
  private static Player validatePlayer(String[] args, int index, String defaultIfMissing) {
    String player;
    if (index >= 0 && index < args.length) {
      player = args[index];
    } else {
      player = defaultIfMissing;
    }

    switch (player) {
    case "h":
      return new HumanPlayer();
    case "ic":
      return new ComputerInOrderPlayer();
    case "rc":
      return new ComputerRandomPlayer();
    default:
      System.out.println("Unknown player " + player + " expected 'h', 'ic' or 'rc', defaulting to Human.");
      return new HumanPlayer();
    }
  }

  /**
   * Extract an integer from the provided argument
   * it must be 2 or more.
   *
   * @param args The command lines parameters
   * @param index Which index to parse
   */
  private static int validateInt(String[] args, int index) {
    if (index >= 0 && index < args.length) {
      int num = Integer.parseInt(args[index]);
      if(num >= 2){
        return num;
      } else {
        System.out.println("Invalid argument, using default...");
      }
    }
    return 3;
  }

  /**
   * prints to the screen whos turn it is.
   *
   * @param currentPlayer who the current player is
   */
  private static void promptNext(int currentPlayer){
    if(currentPlayer == 0){
      System.out.print("Player 1's Turn. \n");
    }
    else{
      System.out.print("Player 2's Turn. \n");
    }

  }

}