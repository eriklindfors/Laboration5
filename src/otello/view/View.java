package otello.view;

import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import otello.model.Model;


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
        playingField = new PlayingSurface(model.getPlayingFieldRows(), model.getPlayingFieldColumns());
        arrayPosition = new Point2D(0, 0);

        currentPlayer = new Label("Current Player: " + model.getCurrentColorPlaying());
        currentPlayer.setFont(Font.font("Arial", 15));
        
        whiteGamePieceStack = new GamePieceSideStack(Color.WHITE, model.getMaximumGamePiecesPerPlayer());
        blackGamePieceStack = new GamePieceSideStack(Color.BLACK, model.getMaximumGamePiecesPerPlayer());
        
        
        bottomHBox = new HBox();
        bottomHBox.setSpacing(5);
        bottomHBox.getChildren().add(currentPlayer);
       
        BorderPane.setMargin(whiteGamePieceStack, new Insets(5, 5, 5, 5));
        BorderPane.setMargin(blackGamePieceStack, new Insets(5, 5, 5, 5));
        
        
        playingField.setOnMouseMoved(e -> {
            arrayPosition = new Point2D((int)(e.getX() / (GamePiece.getGamePieceDiameter())),
                    (int)(e.getY() / (GamePiece.getGamePieceDiameter())));
            System.out.println("row: " + arrayPosition.getY() + " col: " + arrayPosition.getX());
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
        
        
        if(model.getCurrentColorPlaying().equals(Color.WHITE.toString())){
            whiteGamePieceStack.setPieces(Color.WHITE, model.getGamePiecesRemaining(Color.WHITE.toString()));
            System.out.println("Whites remaining: " + model.getGamePiecesRemaining(Color.WHITE.toString()));
        }
        else{
            blackGamePieceStack.setPieces(Color.BLACK, model.getGamePiecesRemaining(Color.BLACK.toString()));
            System.out.println("Blacks remaining: " + model.getGamePiecesRemaining(Color.BLACK.toString()));
        }
        

        for (int i = 0; i < model.getPlayingFieldRows(); i++) {
            for (int j = 0; j < model.getPlayingFieldColumns(); j++) {
                if(!model.isPlayingFieldPositionEmpty(i, j)){
                    if (model.getPlayingFieldPositionColor(i, j).equals(Color.BLACK.toString())) {
                        playingField.setGamePiece(i, j, Color.BLACK);
                    } else {
                        playingField.setGamePiece(i, j, Color.WHITE);
                    }
                }
            }
        }
        
        currentPlayer.setText("Current Player: " + Color.web(model.getCurrentColorPlaying()));
    }
    
    
    public PlayingSurface getPlayingSurface(){
        return playingField;
    }
    
    public Point2D getArrayPosition(){
        return arrayPosition;
    }
}
