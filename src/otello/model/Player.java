package otello.model;

public class Player {
    
    private State playerColor;
    private int gamePiecesRemaining;
    
    public Player(State playerColor, int gamePiecesRemaining){
        if(playerColor == State.EMPTY){
            throw new IllegalPlayerColorException("Illegal color.");
        }
        this.playerColor = playerColor;
        this.gamePiecesRemaining = gamePiecesRemaining;
    }
    
    public Player(){
        this(State.BLACK, 32);
    }

    public State getPlayerColor() {
        return playerColor;
    }

    public int getGamePiecesRemaining() {
        return gamePiecesRemaining;
    }

    public void decrementGamePieces() {
        this.gamePiecesRemaining -= 1;
    }
}
