package otello.controller;

import javafx.geometry.Point2D;
import otello.model.Model;
import otello.view.View;

public class Controller {
    
    private final View view;
    private final Model model;
    
    public Controller(View view, Model model){
        this.view = view;
        this.model = model;
        
        view.getPlayingSurface().setOnMouseClicked(e ->{
            Point2D position = view.getArrayPosition();
            model.mouseClickedAt((int)position.getY(), (int)position.getX());
            view.updateView();  
        });
    }
}
