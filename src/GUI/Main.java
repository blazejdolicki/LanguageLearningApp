package GUI;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Main extends Application{
    private static Stage primaryStage;

    @Override
    public void start(Stage ps) throws FileNotFoundException{
        primaryStage = ps;

        MenuView menu = new MenuView();
        Scene scene = new Scene(menu);

        primaryStage.setTitle("Learn English");
        primaryStage.setScene(scene);
        primaryStage.setWidth(600);
        primaryStage.setHeight(500);
        primaryStage.setResizable(true);

        //put stage in the middle
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);

        primaryStage.show();
    }

    public static Stage getStage(){
        return primaryStage;
    }

    public static void main(String[]args) {
        Application.launch(args);
    }
}
