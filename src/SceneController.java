import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class SceneController {
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToStart (ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Start.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToHome (ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSPL (ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("SPL.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToDeterminan (ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Determinan.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToBalikan (ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Balikan.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToInterpolasi (ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Interpolasi.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToBicubic (ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Bicubic.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToRegresi (ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Regresi.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToRegresiKuadratik (ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("RegresiKuadratik.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
