package otello.view;

import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import otello.model.Game;
import otello.model.Model;
import otello.model.State;

public class View extends BorderPane{

    private Point2D arrayPosition;
    private HBox bottomHBox;
    private Label currentPlayer;
    private GamePieceSideStack whiteGamePieceStack, blackGamePieceStack;
    private PlayingSurface playingField;
    
    private final Model model;
    
    public View(Model model){
        this.model = model;
        initView();
    }
    

    public void initView() {
        playingField = new PlayingSurface(model.getPlayingField().getRows(), model.getPlayingField().getColumns());
        arrayPosition = new Point2D(0, 0);

        currentPlayer = new Label("Current Player: " + model.currentPlayer().getPlayerColor().toString());
        currentPlayer.setFont(Font.font("Arial", 15));
        
        whiteGamePieceStack = new GamePieceSideStack(Color.WHITE, Game.MAX_NR_OF_GAME_PIECES_PER_PLAYER);
        blackGamePieceStack = new GamePieceSideStack(Color.BLACK, Game.MAX_NR_OF_GAME_PIECES_PER_PLAYER);
        
        
        bottomHBox = new HBox();
        bottomHBox.setSpacing(5);
        bottomHBox.getChildren().add(currentPlayer);
       
        BorderPane.setMargin(whiteGamePieceStack, new Insets(5, 5, 5, 5));
        BorderPane.setMargin(blackGamePieceStack, new Insets(5, 5, 5, 5));
        
        
        playingField.setOnMouseMoved(e -> {
            arrayPosition = new Point2D((int)(e.getX() / (GamePiece.getGamePieceDiameter() + 1)), 
                    (int)(e.getY() / (GamePiece.getGamePieceDiameter() + 1)));
            //System.out.println("row: " + arrayPosition.getY() + " col: " + arrayPosition.getX());
        });
        
        playingField.setOnMouseDragged(e -> {
            arrayPosition = new Point2D((int)(e.getX() / GamePiece.getGamePieceDiameter()), 
                    (int)(e.getY() / GamePiece.getGamePieceDiameter()));
            //System.out.println("row: " + arrayPosition.getY() + " col: " + arrayPosition.getX());
        });
        
        this.setCenter(playingField);
        BorderPane.setAlignment(playingField, Pos.CENTER);
        this.setBottom(bottomHBox);
        this.setLeft(whiteGamePieceStack);
        this.setRight(blackGamePieceStack);

        updateView();
    }

    public void updateView() {

        //Kolla om den verkligen rensar gamePane på GamePiece-objekt.
        playingField.getChildren().removeIf(n -> n instanceof GamePiece);
        
        
        if(model.currentPlayer().getPlayerColor() == State.WHITE){
            
            
            whiteGamePieceStack.setPieces(Color.WHITE, model.currentPlayer().getGamePiecesRemaining());
            System.out.println("White remaining: " + model.currentPlayer().getGamePiecesRemaining());
        }
        else{
            
            
            blackGamePieceStack.setPieces(Color.BLACK, model.currentPlayer().getGamePiecesRemaining());
            System.out.println("Black remaining: " + model.currentPlayer().getGamePiecesRemaining());
        }
        

        for (int i = 0; i < model.getPlayingField().getRows(); i++) {
            for (int j = 0; j < model.getPlayingField().getColumns(); j++) {
                State state = model.getPlayingField().getState(i, j);
                if (state != State.EMPTY) {
                    if (state == State.BLACK) {
                        playingField.setGamePiece(i, j, Color.BLACK);
                    } else {
                        playingField.setGamePiece(i, j, Color.WHITE);
                    }
                }
            }
        }
        
        currentPlayer.setText("Current Player: " + model.currentPlayer().getPlayerColor().toString());
    }
    
    
    public PlayingSurface getPlayingSurface(){
        return playingField;
    }
    
    public Point2D getArrayPosition(){
        return arrayPosition;
    }
}
