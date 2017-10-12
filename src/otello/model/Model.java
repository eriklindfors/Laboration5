package otello.model;

public class Model {

    private Game game;

    public Model() {
        game = new Game();
    }

    public String getCurrentColorPlaying() {
        if(game.getCurrentColorPlaying() == State.BLACK){
            return "0x000000ff";
        }
        else{
            return "0xffffffff";
        }
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

    public int getGamePiecesRemaining(String color) {
        return game.getGamePiecesRemaining(color);
    }

    public boolean isPlayingFieldPositionEmpty(int row, int column){
        if(game.getPlayingField().getState(row, column) == State.EMPTY){
            return true;
        }
        return false;
    }

    public String getPlayingFieldPositionColor(int row, int column){
        if(isPlayingFieldPositionEmpty(row, column)){
            throw new playingFieldPosEmptyException("Cannot fetch color from empty position");
        }
        else{
            if(game.getPlayingField().getState(row, column) == State.BLACK){
                return "0x000000ff";
            }
            else{
                return "0x00000000";
            }
        }
    }

    public void mouseClickedAt(int row, int column){
        game.updatePlayingField(row, column);
    }
}
