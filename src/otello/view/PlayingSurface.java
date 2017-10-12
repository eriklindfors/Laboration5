package otello.view;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PlayingSurface extends Pane{
    
    private GamePiece[][] gamePieces;
    private PlayingSurfaceTile[][] gameTiles;
    private int rows, columns;

    public PlayingSurface(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        gamePieces = new GamePiece[rows][columns];
        gameTiles = new PlayingSurfaceTile[rows][columns];
        
        init();
    }
    
    public void update(){
       
    }
    
    public void setGamePiece(int row, int column, Color color){
        gamePieces[row][column] = new GamePiece((column + GamePiece.getGamePieceDiameter() / 2) + column * GamePiece.getGamePieceDiameter(),
                                (row + GamePiece.getGamePieceDiameter() / 2) + row * GamePiece.getGamePieceDiameter(), 
                                color);
        this.getChildren().add(gamePieces[row][column]);
    }
    

    
    public void init(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                gameTiles[i][j] = new PlayingSurfaceTile(j + j * GamePiece.getGamePieceDiameter(),
                        i + i * GamePiece.getGamePieceDiameter(),
                        GamePiece.getGamePieceDiameter(),
                        GamePiece.getGamePieceDiameter());
                gameTiles[i][j].setFill(Color.GREEN);
                gameTiles[i][j].setStroke(Color.DARKGREEN);
                gameTiles[i][j].setStrokeWidth(2);
                this.getChildren().add(gameTiles[i][j]);
            }
        }
    }
    
    private class PlayingSurfaceTile extends Rectangle{
        
        public PlayingSurfaceTile(double x, double y, double width, double height){
            super(x, y, width, height);
        }
    }
    
}
