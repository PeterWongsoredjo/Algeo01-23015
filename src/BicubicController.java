import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class BicubicController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private TextArea inputArea;

    @FXML
    private TextField resultField;

    @FXML
    public void fillMatrix(Matrix mX){
        mX.CreateMatrix(mX, 4, 4);
        String[] rows = inputArea.getText().split("\n");
        for (int i = 0; i < 4; i++) {
            String[] elements = rows[i].trim().split("\\s+");
    
            for (int j = 0; j < 4; j++) {
                mX.setElement(mX, i, j, Double.parseDouble(elements[j]));
            }
        }
    }
    
    public void resultOutput(TextArea resulTextArea, StringBuilder result){
        resulTextArea.setText(result.toString());
    }

    @FXML
    private void handleInterpolate(ActionEvent event) {
        BicubicSplineInterpolation bicubicSplineInterpolation = new BicubicSplineInterpolation();
        try {
            Matrix mX = new Matrix();
            fillMatrix(mX);

            // Parse the x and y input
            String[] xy = inputArea.getText().split("\n")[4].split("\\s+");
            double x = Double.parseDouble(xy[0]);
            double y = Double.parseDouble(xy[1]);

            // Perform bicubic spline interpolation (assuming a method exists)
            double result = bicubicSplineInterpolation.Interpolation(mX, x, y);

            // Display the result
            resultField.setText("Result: " + result);
        } catch (Exception e) {
            resultField.setText("Error: " + e.getMessage());
        }
    }
    public void switchToHome (ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
