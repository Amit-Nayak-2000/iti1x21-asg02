public class ComputerInOrderPlayer implements Player  {

  //Play method returns false if the game is not playable, otherwise the computer will play at the next open spot in the game and return true.
  public boolean play(TicTacToe game){
    if(game.emptyPositions().length == 0){
        System.out.println("Game is not playable.");
        return false;
    }
    else{
      //generates array of empty positions.
      int[] freeSlots = game.emptyPositions();
      //plays at next available position.
      game.play(freeSlots[0]);
      return true;
    }
    
  }
}