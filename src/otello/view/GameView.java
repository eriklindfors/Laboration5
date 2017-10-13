package otello.view;

import javafx.application.Platform;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import otello.controller.Controller;

import otello.model.Model;
import otello.model.State;

public class GameView extends BorderPane{

    private Point2D arrayPosition;
    
    private Label currentPlayerLabel, timeLeftLabel;
    private HBox bottomHBox, bottomRightHBox, surfaceStacksHistoryHBox;
    private BorderPane surfaceStacksBorderPane;
    private TextArea historyArea;
    private GamePieceSideStack whiteGamePieceStack, blackGamePieceStack;
    private PlayingSurface playingSurface;
    private Button resetButton;
    private Button exitToMenuButton;
    private MenuBar menuBar;
    private Menu file;
    private MenuItem instructions, exit;
    
    
    private final Model model;
    
    public GameView(Model model){
        this.model = model;
        Controller controller = new Controller(this, model);
        initView(controller);
    }
    

    public void initView(Controller controller) {
        
        //Initialize MENU
        instructions = new MenuItem("Instructions");
        exit = new MenuItem("Exit");
        file = new Menu("File");
        file.getItems().addAll(instructions, exit);
        menuBar = new MenuBar(file);
        
        playingSurface = new PlayingSurface(model.getPlayingFieldRows(), model.getPlayingFieldColumns());
        arrayPosition = new Point2D(0, 0);
        
        //Bottom
        currentPlayerLabel = new Label("Current Player: " + model.getStateOfCurrentPlayer());
        currentPlayerLabel.setFont(Font.font("Verdana", 15));
        timeLeftLabel = new Label("Time left: ");
        timeLeftLabel.setFont(Font.font("Verdana", 15));
        resetButton = new Button("Reset");
        exitToMenuButton = new Button("Exit To Menu");
        
        bottomRightHBox = new HBox();
        bottomRightHBox.getChildren().addAll(resetButton, exitToMenuButton);
        bottomRightHBox.setAlignment(Pos.CENTER_RIGHT);
        bottomRightHBox.setSpacing(10);
        HBox.setHgrow(bottomRightHBox, Priority.ALWAYS);
        HBox.setMargin(bottomRightHBox, new Insets(10, 10, 10, 10));
        
        bottomHBox = new HBox();
        bottomHBox.getChildren().addAll(currentPlayerLabel, bottomRightHBox);
        bottomHBox.setAlignment(Pos.CENTER_LEFT);
        
        //Right
        
        whiteGamePieceStack = new GamePieceSideStack(Color.WHITE, model.getMaximumGamePiecesPerPlayer());
        blackGamePieceStack = new GamePieceSideStack(Color.BLACK, model.getMaximumGamePiecesPerPlayer());
        
        historyArea = new TextArea();
        historyArea.setPrefWidth(160);
        historyArea.setEditable(false);
        
        
        surfaceStacksBorderPane = new BorderPane();
        surfaceStacksBorderPane.setLeft(whiteGamePieceStack);
        surfaceStacksBorderPane.setCenter(playingSurface);
        surfaceStacksBorderPane.setRight(blackGamePieceStack);
        
        surfaceStacksHistoryHBox = new HBox();
        surfaceStacksHistoryHBox.getChildren().addAll(surfaceStacksBorderPane, historyArea);
       
        BorderPane.setMargin(whiteGamePieceStack, new Insets(5, 5, 5, 5));
        BorderPane.setMargin(blackGamePieceStack, new Insets(5, 5, 5, 5));
        HBox.setMargin(historyArea, new Insets(0, 5, 0, 0));
        BorderPane.setAlignment(playingSurface, Pos.CENTER);
        
        
        //***************************EVENT HANDLING*****************************
        
        playingSurface.setOnMouseMoved(e -> {
            arrayPosition = new Point2D((int)(e.getX() / (GamePiece.getGamePieceDiameter())),
                    (int)(e.getY() / (GamePiece.getGamePieceDiameter())));
            //System.out.println("row: " + arrayPosition.getY() + " col: " + arrayPosition.getX());
        });
        
        playingSurface.setOnMouseDragged(e -> {
            arrayPosition = new Point2D((int)(e.getX() / GamePiece.getGamePieceDiameter()), 
                    (int)(e.getY() / GamePiece.getGamePieceDiameter()));
            //System.out.println("row: " + arrayPosition.getY() + " col: " + arrayPosition.getX());
        });
        
        exit.setOnAction(e -> {
            controller.handleExitButtonClicked();
        });
        
        playingSurface.setOnMouseClicked(e ->{
            Point2D position = arrayPosition;
            controller.handlePlayingSurfaceClicked((int)position.getY(), (int)position.getX());  
        });
        
        resetButton.setOnAction(e -> {
            controller.handleResetButtonClicked();
        });
        
        exitToMenuButton.setOnAction(e -> {
            controller.handleExitToMenuButton(); 
        });
        
        //**********************************************************************
        this.setTop(menuBar);
        this.setCenter(surfaceStacksHistoryHBox);
        this.setBottom(bottomHBox);
        this.setLeft(whiteGamePieceStack);

        updateView();
    }

    public void updateView() {

        //Kolla om den verkligen rensar gamePane på GamePiece-objekt.
        playingSurface.getChildren().removeIf(n -> n instanceof GamePiece);
        
        
        if(model.getStateOfCurrentPlayer() == State.WHITE){
            whiteGamePieceStack.setPieces(Color.WHITE, model.getGamePiecesRemaining(State.WHITE));
            System.out.println("Whites remaining: " + model.getGamePiecesRemaining(State.WHITE));
        }
        else{
            blackGamePieceStack.setPieces(Color.BLACK, model.getGamePiecesRemaining(State.BLACK));
            System.out.println("Blacks remaining: " + model.getGamePiecesRemaining(State.BLACK));
        }
        

        for (int i = 0; i < model.getPlayingFieldRows(); i++) {
            for (int j = 0; j < model.getPlayingFieldColumns(); j++) {
                if(!model.isPlayingFieldPositionEmpty(i, j)){
                    if (model.getPlayingFieldPositionState(i, j) == State.BLACK) {
                        playingSurface.setGamePiece(i, j, Color.BLACK);
                    } else {
                        playingSurface.setGamePiece(i, j, Color.WHITE);
                    }
                }
            }
        } 
        currentPlayerLabel.setText("Current Player: " + model.getStateOfCurrentPlayer());
    }
}
