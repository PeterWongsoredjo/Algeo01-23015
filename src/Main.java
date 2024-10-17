import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;
    private Scene startScene;
    private Scene homeScene;

    @Override
    public void start(Stage primaryStage) {
        try{
            Parent startFXML = FXMLLoader.load(getClass().getResource("Start.fxml"));
            startScene = new Scene(startFXML);
            /* PRIMARY STAGE */
            primaryStage.setTitle("RakaJava Matrix Calculator");
            primaryStage.setScene(startScene);
            primaryStage.show();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}