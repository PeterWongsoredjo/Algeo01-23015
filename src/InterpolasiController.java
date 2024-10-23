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


public class InterpolasiController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private TextArea inputArea;

    @FXML
    private TextArea resultField;

    @FXML
    private TextField inputN;

    @FXML
    private TextField inputX;

    Matrix M = new Matrix();
    GaussJordan GJ = new GaussJordan();

    @FXML
    public void sizeMatrix(){
        String n = inputN.getText();
        int degree = Integer.parseInt(n);
        M.CreateMatrix(M, degree, degree+1);
    }

    @FXML
    public void fillMatrix(Matrix M, int degree){
        M.CreateMatrix(M, degree, degree+1);

        for (int i = 0; i < degree; i++) {
            String[] xy = inputArea.getText().split("\n")[i].split("\\s+");
            double x = Double.parseDouble(xy[0]);
            double y = Double.parseDouble(xy[1]);

            for (int j = 0; j < degree; j++) {
                if (i < degree && j < degree) {
                    M.setElement(M, i, j, Math.pow(x, j)); 
                }
            }
            
            if (i < degree) {
                M.setElement(M, i, degree, y); 
            }
        }
    }

    @FXML
    public void handleLoadFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            readFile(file);
        }
    }

    private void readFile(File file) {
        StringBuilder content = new StringBuilder();
        String firstLine = null;
        String lastLine = null;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    firstLine = line.trim(); // Assume the first line contains the value of n
                    isFirstLine = false;
                } else {
                    if (lastLine != null) {
                        content.append(lastLine).append("\n");
                    }
                    lastLine = line.trim(); // Assume the last line contains the value of x
                }
            }
            // Remove the last line from content
            if (content.length() > 0 && content.charAt(content.length() - 1) == '\n') {
                content.deleteCharAt(content.length() - 1);
            }
            inputArea.setText(content.toString());

            // Set the value of inputN
            if (firstLine != null && !firstLine.isEmpty()) {
                inputN.setText(firstLine);
            } else {
                resultField.setText("Error: First line (n) is empty.");
                return;
            }
    
            // Set the value of inputX
            if (lastLine != null && !lastLine.isEmpty()) {
                inputX.setText(lastLine);
            } else {
                resultField.setText("Error: Last line (x) is empty.");
                return;
            }
        } catch (IOException e) {
            resultField.setText("Error: " + e.getMessage());
        }
    }

    public void resultOutput(TextField resultField, StringBuilder result){
        resultField.setText(result.toString());
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
    private void calculateInterpolate(ActionEvent event) {
        try {
            sizeMatrix();
            fillMatrix(M, M.getRow(M));
            GJ.gaussjordan(M);
        
            Matrix hasil = new Matrix();
            hasil.CreateMatrix(hasil, M.getRow(M), 1);
            for(int i = 0; i<M.getRow(M); i++){
                hasil.setElement(hasil, i, 0, M.getElement(i,M.getLastColIdx(M)));
            }

            double result = 0;
            for(int i = 0; i<M.getRow(M);i++){
                String xValue = inputX.getText();
                if (xValue == null || xValue.isEmpty()) {
                    String[] lines = inputArea.getText().split("\n");
                    xValue = lines[lines.length - 1].trim(); // Assume the last line contains the value of x
                }
                double x = Double.parseDouble(xValue);
                result += (hasil.getElement(i, 0) * Math.pow(x, i));
            }
            resultField.setText(Double.toString(result));
        } catch (Exception e) {
            e.printStackTrace();
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
