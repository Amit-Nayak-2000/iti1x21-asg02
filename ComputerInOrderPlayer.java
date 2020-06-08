public class ComputerInOrderPlayer implements Player  {

  public boolean play(TicTacToe game){
    if(game.gameState == GameState.DRAW || game.emptyPositions().length == 0){
        System.out.println("Game is not playable.");
        return false;
    }
    else{
        for(int i = 1; i <=(game.numRows * game.numColumns); i++){
            if(game.valueAt(i) == CellValue.EMPTY){
              game.play(i);
              return true;
            }
        }
    }
    return true;
  }
}