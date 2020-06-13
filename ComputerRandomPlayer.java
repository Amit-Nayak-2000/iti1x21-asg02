public class ComputerRandomPlayer implements Player{
  
  //Play method returns false if the game is not playable, otherwise the computer will play at a random index in an array of empty spots.
  public boolean play(TicTacToe game){
    if(game.emptyPositions().length == 0){
        System.out.println("Game is not playable.");
        return false;
    }
    else{
        //array of empty slots is generated, the length of the array is used to calculate a random number.
        //this random number will be used as the index in the array of empty positions to pick a position which will be played.
        int[] freeSlots = game.emptyPositions();
        int range = freeSlots.length;
        int randomIndex = (int)(range*Math.random());
        game.play(freeSlots[randomIndex]);
        return true;
    }
    
  }

}