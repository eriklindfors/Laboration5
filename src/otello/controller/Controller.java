package otello.controller;

import javafx.application.Platform;
import otello.model.Model;
import otello.view.MenuView;
import otello.view.GameView;

public class Controller {
    
    private final GameView view;
    private final Model model;
    
    public Controller(GameView view, MenuView menuView, Model model){
        this.view = view;
        this.model = model;
    }
    
    public void handlePlayingSurfaceClicked(int row, int column){
        model.mouseClickedAt(row, column);
        view.updateView();  
    }
    
    public void handleResetButtonClicked(){
        model.reset();
        view.updateView();
    }
    
    public void handleExitButtonClicked(){
        Platform.exit();
    }
    
    public void handleExitToMenuButton(){
        
    }
    
    
}
