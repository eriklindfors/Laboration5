package otello.otello;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import otello.controller.Controller;
import otello.model.Model;
import otello.view.View;

public class OthelloMain extends Application{
    
    @Override
    public void start(Stage stage){
    
        Model model = new Model();
        View view = new View(model);
        Controller controller = new Controller(view, model);
        
        Scene scene = new Scene(view);
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args){
        launch(args);
    }
}
