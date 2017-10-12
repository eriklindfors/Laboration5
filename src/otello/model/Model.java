package otello.model;

public class Model {
    
    
    private Game game;
    
    public Model(){
        game= new Game();
    }
    
    public void printPlayingField(){
        for(int i = 0; i < game.getPlayingField().getRows(); i++){
            for(int j = 0; j < game.getPlayingField().getColumns(); j++){
                System.out.print(game.getPlayingField().getState(i, j).toString() + " ");
            }
            System.out.println("\n");
        }
    }
    
    public void mouseClickedAt(int row, int column){
        game.removePieceFromCurrentPlayer();
        game.updatePlayingField(row, column);
        game.updateCurrentPlayer();
    }
    
    public PlayingField getPlayingField(){
        return game.getPlayingField();
    }
    
    public Player currentPlayer(){
        return game.getCurrentPlayer();
    }
}
