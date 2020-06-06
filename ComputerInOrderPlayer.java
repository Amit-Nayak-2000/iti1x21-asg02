public class ComputerInOrderPlayer implements Player  {
  public boolean play(TicTacToe game){
    if(game.gameState != GameState.PLAYING){
        System.out.println("Game is not playable.");
        return false;
    }
    else{
        for(int i = 0; i < (game.numRows * game.numColumns); i++){
            if(game.valueAt(i) == CellValue.EMPTY){
              game.play(i);
              return true;
            }
        }
    }
    return false;
  }
}