public class ComputerRandomPlayer implements Player{

  public boolean play(TicTacToe game){
    if(game.gameState != GameState.PLAYING){
        System.out.println("Game is not playable.");
        return false;
    }
    else{
        int freeSpace = (game.numRows * game.numColumns) - game.numRounds;
        int[] freeSlots = new int[freeSpace];
        int counter = 0;
        for(int i = 1; i <=(game.numRows * game.numColumns); i++){
            if(game.valueAt(i) == CellValue.EMPTY){
              freeSlots[counter] = i;
              counter +=1;
            }
        }
        int randomIndex = (int)(freeSpace*Math.random());
        game.play(freeSlots[randomIndex]);
        return true;
    }
    
  }

}