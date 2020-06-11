public class ComputerRandomPlayer implements Player{

  public boolean play(TicTacToe game){
    if(game.emptyPositions().length == 0){
        System.out.println("Game is not playable.");
        return false;
    }
    else{
        
        int[] freeSlots = game.emptyPositions();
        int range = freeSlots.length;
        int randomIndex = (int)(range*Math.random());
        game.play(freeSlots[randomIndex]);
        return true;
    }
    
  }

}