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

import javafx.stage.FileChooser;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
    public void handleLoadFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            readFile(file);
        }
    }

    private void readFile(File file) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
            inputArea.setText(content.toString());
        } catch (IOException e) {
            resultField.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    public void handleSaveFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Output File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            saveFile(file);
        }
    }

    private void saveFile(File file) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(resultField.getText());
        } catch (IOException e) {
            resultField.setText("Error: " + e.getMessage());
        }
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
