import java.util.LinkedList;

public class TicTacToeEnumerations {

  // CellValue[] board;
  int numRows;
  int numColumns;
  // int numRounds;
  // GameState gameState;
  int sizeToWin;
  // CellValue currentPlayer;
  // int lastPlayedPosition;

  // YOUR CODE HERE

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
  //   this.gameState = GameState.PLAYING;
  //   this.currentPlayer = CellValue.EMPTY;
  //   this.lastPlayedPosition = 0;

  //   int boardSize = numRows * numColumns;
  //   board = new CellValue[boardSize];
  //   for (int i=0; i<boardSize; i++) {
  //     board[i] = CellValue.EMPTY;
  //   }
  // }

  /**
   * Generate a list of lists of all games, storing the
   * result in the member variables `allGames`.
   */
  public LinkedList<LinkedList<TicTacToe>> generateAllGames() {
    TicTacToe game = new TicTacToe(this.numRows, this.numColumns, this.sizeToWin);
    LinkedList<TicTacToe> baseLevel = new LinkedList<TicTacToe>();
    baseLevel.add(game);
    allGames.add(baseLevel);
    int counter = 1;
    boolean valid = true;

    while(valid){
      LinkedList<TicTacToe> nextLevel = new LinkedList<TicTacToe>();
      allGames.add(nextLevel);

      for(int i = 0; i < allGames.get(counter - 1).size(); i++){
        
        if(allGames.get(counter-1).get(i).gameState == GameState.PLAYING){
          int[] emptySlots = allGames.get(counter-1).get(i).emptyPositions();


          for(int j = 0; j < emptySlots.length; j++){
            TicTacToe compare = allGames.get(counter - 1).get(i).cloneNextPlay(emptySlots[j]);
            boolean isRepeated = false;

            for(int k = 0; k < allGames.get(counter).size(); k++){
              if(compare.equals(allGames.get(counter).get(k))){
                isRepeated = true;
              }

            }

            if(isRepeated == false){
              allGames.get(counter).add(compare);
            }
          }
        }
      }
      
      valid = false;
      for(int i = 0; i < allGames.get(counter).size(); i++){
        if(allGames.get(counter).get(i).gameState == GameState.PLAYING){
          valid = true;
        }
      }
      counter++;
    }
		// returns the linked list of linked lists of tic tac toe games
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