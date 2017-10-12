package otello.view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GamePiece extends Circle{
    
    private static final double RADIUS = 60;
    
    public GamePiece(double posX, double posY, Color color){
        super(posX, posY, RADIUS, color);
    }
    
    public GamePiece(){
        this(0.0, 0.0, Color.BLACK);
    }
    
    public static double getGamePieceDiameter(){
        return RADIUS * 2;
    }
}
