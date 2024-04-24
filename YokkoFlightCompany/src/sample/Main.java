package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample/Templates/LoadingScreen.fxml"));
        primaryStage.setTitle("Application");
        primaryStage.setScene(new Scene(root, 1200, 700));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

/*
    --module-path
    \[Полный путь]\YokkoFlightCompany\javafx-sdk-17.0.11-WIN\lib
    --add-modules
    javafx.controls,javafx.fxml
 */

/*
 Цвета:
 ff0074
 ff8713
 6dccff
 13a3ed
 002e73

 e3efee
 */
