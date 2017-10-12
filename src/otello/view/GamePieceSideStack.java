package otello.view;

import java.util.ArrayList;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import otello.model.Game;

public class GamePieceSideStack extends VBox{
    
    private ArrayList<GamePieceSide> pieces;
    
    public GamePieceSideStack(Color color, int numberOfPieces){
        pieces = new ArrayList<>();
        for(int i = 0; i < numberOfPieces; i++){
            pieces.add(new GamePieceSide(color));
        }
        this.setSpacing(1);
        this.getChildren().addAll(pieces);
    }
    
    public void setPieces(Color color, int numberOfPieces){
        pieces.clear();
        for(int i = 0; i < numberOfPieces; i++){
            pieces.add(new GamePieceSide(color));
        }
        this.getChildren().clear();
        this.getChildren().addAll(pieces);
    }
    
    private class GamePieceSide extends Rectangle{
        
        private static final double WIDTH = 45; 
        private static final double HEIGHT = 10; 
        
        public GamePieceSide(Color color){
            setWidth(WIDTH);
            setHeight(HEIGHT);
            setFill(color);
            setStrokeWidth(1);
            setStroke(Color.LIGHTGRAY);
        }
        
        public GamePieceSide(){
            this(Color.BLACK);
        }
    }
}
