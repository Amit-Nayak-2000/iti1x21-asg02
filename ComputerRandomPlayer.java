public class ComputerRandomPlayer implements Player{

  public boolean play(TicTacToe game){
    if(game.gameState == GameState.DRAW || game.emptyPositions().length == 0){
        System.out.println("Game is not playable.");
        return false;
    }
    else{
        
        int[] freeSlots = game.emptyPositions();
        int range = freeSlots.length;
        // int counter = 0;
        // for(int i = 1; i <=(game.numRows * game.numColumns); i++){
        //     if(game.valueAt(i) == CellValue.EMPTY){
        //       freeSlots[counter] = i;
        //       counter +=1;
        //     }
        // }
        int randomIndex = (int)(range*Math.random());
        game.play(freeSlots[randomIndex]);
        return true;
    }
    
  }

}