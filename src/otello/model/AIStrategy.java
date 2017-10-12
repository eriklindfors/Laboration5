package otello.model;

import javafx.geometry.Point2D;

public class AIStrategy implements Strategy{
    
    @Override
    public Point2D nextMove(State[][] playingFieldState, State myColor){
        return new Point2D(0, 0);
    }
}
