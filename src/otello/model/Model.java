package otello.model;

public class Model {

    private Game game;

    public Model() {
        game = new Game();
    }

    public State getStateOfCurrentPlayer() {
        return game.getCurrentColorPlaying();
    }

    public int getPlayingFieldRows() {
        return game.getPlayingField().getRows();
    }

    public int getPlayingFieldColumns() {
        return game.getPlayingField().getColumns();
    }

    public int getMaximumGamePiecesPerPlayer() {
        return game.getMaxNrOfGamePiecesPerPlayer();
    }

    public int getGamePiecesRemaining(State color) {
        return game.getGamePiecesRemaining(color);
    }

    public boolean isPlayingFieldPositionEmpty(int row, int column){
        if(game.getPlayingField().getState(row, column) == State.EMPTY){
            return true;
        }
        return false;
    }

    public State getPlayingFieldPositionState(int row, int column){
        return game.getPlayingField().getState(row, column);
    }

    public void mouseClickedAt(int row, int column){
        game.updatePlayingField(row, column);
    }
    
    public void reset(){
        game.reset();
    }
}
