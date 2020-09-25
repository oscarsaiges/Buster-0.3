package se.saiges.buster;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.saiges.buster.modul.DataBaseSetup;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main_view.fxml"));
        primaryStage.setTitle("Buster 0.2");
        primaryStage.setScene(new Scene(root, 1024, 600));
        primaryStage.show();
    }

    @Override
    public void stop() {
        DataBaseSetup.getInstance().close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
