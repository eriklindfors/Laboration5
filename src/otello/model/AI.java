package otello.model;

import javafx.geometry.Point2D;

public class AI extends Player{
    
    private Strategy strategy;
    
    public AI(State playerColor, int gamePiecesRemaining, Strategy strategy){
        super(playerColor, gamePiecesRemaining);
        this.strategy = strategy;
    }
    
    public Point2D getMove(State[][] playingFieldState){
        return strategy.nextMove(playingFieldState, getPlayerColor());
    }
}
