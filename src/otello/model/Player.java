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
        this(State.BLACK, Game.MAX_NR_OF_GAME_PIECES_PER_PLAYER);
    }

    public State getPlayerColor() {
        return playerColor;
    }

    public int getGamePiecesRemaining() {
        return gamePiecesRemaining;
    }

    public void setGamePiecesRemaining(int gamePiecesRemaining) {
        this.gamePiecesRemaining = gamePiecesRemaining;
    }
}
