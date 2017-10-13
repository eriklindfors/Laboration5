package otello.otello;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import otello.controller.Controller;
import otello.model.Model;
import otello.view.MenuView;
import otello.view.GameView;

public class OthelloMain extends Application{
    
    @Override
    public void start(Stage stage){
    
        Model model = new Model();
        GameView view = new GameView(model);
        MenuView menuView = new MenuView();
        Controller controller = new Controller(view, menuView, model);
        
        Scene scene = new Scene(view);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    
    public static void main(String[] args){
        launch(args);
    }
}
