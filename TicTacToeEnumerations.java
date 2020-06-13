import java.util.LinkedList;

public class TicTacToeEnumerations {

  //parameters of board to analyse.
  int numRows;
  int numColumns;
  int sizeToWin;



  /**
   * The list of lists of all generated games
   */
  LinkedList<LinkedList<TicTacToe>> allGames = new LinkedList<LinkedList<TicTacToe>>();


  /**
   * A constructor where you can specify the dimensions
   * of your game as rows x coluns grid, and a sizeToWin
   * to analyze.
   *
   * @param aNumRows the number of numRows in the game
   * @param aNumColumns the number of columns in the game
   * @param aSizeToWin the number of cells that must be aligned to win.
   */
  public TicTacToeEnumerations(int aNumRows, int aNumColumns, int aSizeToWin) {
    numRows = aNumRows;
    numColumns = aNumColumns;
    sizeToWin = aSizeToWin;
  }

  /**
   * Generate a list of lists of all games, storing the
   * result in the member variables `allGames`.
   */
  public LinkedList<LinkedList<TicTacToe>> generateAllGames() {
    //level 0 game.
    TicTacToe game = new TicTacToe(numRows, numColumns, sizeToWin);
    //linked list that contains all (1) game of level 0
    LinkedList<TicTacToe> baseLevel = new LinkedList<TicTacToe>();
    //adds game to base level and adds level to main linked list.
    baseLevel.add(game);
    allGames.add(baseLevel);
    //counter will be used for current level and dealing with the previous level.
    int counter = 1;
    boolean valid = true;

    //loop runs while there are games still playing.
    while(valid){
      LinkedList<TicTacToe> nextLevel = new LinkedList<TicTacToe>();
      allGames.add(nextLevel);

      for(int i = 0; i < allGames.get(counter - 1).size(); i++){
        //gets all of the empty slots from the previous level to play on.
        int[] emptySlots = allGames.get(counter-1).get(i).emptyPositions();

        if(allGames.get(counter-1).get(i).gameState == GameState.PLAYING){
          for(int j = 0; j < emptySlots.length; j++){
            //creates game with next position played for comparison.
            //if game passes comparison it is added to linked list.
            TicTacToe compare = allGames.get(counter - 1).get(i).cloneNextPlay(emptySlots[j]);
  
            //assume game is not a repeat
            boolean isRepeated = false;
            
            //checks if the game is a repeat with other games in the level.
            for(int k = 0; k < allGames.get(counter).size(); k++){
              if(compare.equals(allGames.get(counter).get(k))){
                isRepeated = true;
                break;
              }
            }

            //only adds games that are not repeats.
            if(isRepeated == false){
              allGames.get(counter).add(compare);
            }
          }
        }
      }
      
      valid = false;
      //assumes all games are done being playable, if loop encouters one game that is playable it will break loop and move onto next level.
      for(int i = 0; i < allGames.get(counter).size(); i++){
        if(allGames.get(counter).get(i).gameState == GameState.PLAYING){
          valid = true;
          break;
        }
      }
      //increments the next level to work with
      counter++;
    }
		// returns the linked list of linked lists of all of the games.
		return allGames;
  }

    
  

  public String toString() {
    if (allGames == null) {
      return "No games generated.";
    }

    StringBuilder s = new StringBuilder();

    int numGames = 0;
    int numXWin = 0;
    int numOWin = 0;
    int numDraw = 0;
    for (int i=0; i<allGames.size(); i++) {
      LinkedList<TicTacToe> games = allGames.get(i);
      int numStillPlaying = 0;
      for (TicTacToe g : games) {
        numGames += 1;
        switch (g.gameState) {
        case PLAYING:
          numStillPlaying += 1;
          break;
        case XWIN:
          numXWin += 1;
          break;
        case OWIN:
          numOWin += 1;
          break;
        case DRAW:
          numDraw += 1;
          break;
        }
      }
      s.append("======= level "+ i +" =======: ");
      s.append(games.size() + " element(s) (");
      s.append(numStillPlaying + " still playing)\n");
    }

    s.append("that's "+ numGames +" games\n");
    s.append(numXWin + " won by X\n");
    s.append(numOWin + " won by O\n");
    s.append(numDraw + " draw");
    return s.toString();
  }

}