package otello.model;

import javafx.geometry.Point2D;

public interface Strategy {
    
    public abstract Point2D nextMove(State[][] playingFieldState, State myColor);
}
