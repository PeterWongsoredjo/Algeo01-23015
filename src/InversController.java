import java.io.File;

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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class InversController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private TextArea inputMatrix;

    @FXML
    private TextArea resultField;

    Matrix M = new Matrix();

    @FXML
    private TextField inputN;

    @FXML
    public void sizeMatrix(){
        String sizeMatrix = inputN.getText();

        int intRows = Integer.parseInt(sizeMatrix);
        int intCols = Integer.parseInt(sizeMatrix);
        
        M.CreateMatrix(M, intRows, intCols);
        System.out.println("Matrix Created.");
    }

    @FXML
    public void fillMatrix(){
        String fillMatrix = inputMatrix.getText();

        String[] rows = fillMatrix.trim().split("\n");
        for (int i = 0; i < M.getRow(M); i++) {
            String[] elements = rows[i].trim().split("\\s+");
    
            for (int j = 0; j < M.getCol(M); j++) {
                M.setElement(M, i, j, Double.parseDouble(elements[j]));
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
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            String firstLine = null;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    firstLine = line.trim(); // Assume the first line contains the value of n
                    isFirstLine = false;
                } else {
                    content.append(line).append("\n");
                }
            }
            inputMatrix.setText(content.toString());

            // Set the value of inputN
            if (firstLine != null && !firstLine.isEmpty()) {
                inputN.setText(firstLine);
            } else {
                resultField.setText("Error: First line (n) is empty.");
                return;
            }
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
    public void resultOutput(TextArea resulTextArea, StringBuilder result){
        resulTextArea.setText(result.toString());
    }

    public void InversGaussRun(){
        try{
            sizeMatrix();
            fillMatrix();

            StringBuilder result = new StringBuilder();
            Matrix resultMatrix = new Matrix();
            resultMatrix.CreateMatrix(resultMatrix, M.getRow(M), M.getCol(M));

            DeterminanKofaktor DK = new DeterminanKofaktor();

            double det = DK.detkof(M);

            if(M.getRow(M) != M.getCol(M) || det == 0){
                resultField.setText("Matriks tidak memiliki balikan.");
            }
            else{
                InversGauss IG = new InversGauss();
                resultMatrix = IG.inversgauss(M);

                for (int i = 0; i < M.getRow(M); i++) {
                    for (int j = 0; j < M.getCol(M); j++) {
                        result.append(resultMatrix.getElement( i, j) + " ");
                    }
                    result.append("\n");
                }

                resultOutput(resultField, result);
            }  
        }
        catch(Exception e){
            resultField.setText("Error: " + e);
        }
        
    }

    public void InversAdj(){
        try{
            sizeMatrix();
            fillMatrix();

            StringBuilder result = new StringBuilder();
            Matrix resultMatrix = new Matrix();
            resultMatrix.CreateMatrix(resultMatrix, M.getRow(M), M.getCol(M));
            DeterminanKofaktor DK = new DeterminanKofaktor();

            double det = DK.detkof(M);

            if(M.getRow(M) != M.getCol(M) || det == 0){
                resultField.setText("Matriks adalah matriks singular, sehingga tidak memiliki balikan.");
            }
            else{
                InversAdj IA = new InversAdj();
                resultMatrix = IA.balikan(M);

                if(resultMatrix.getElement(0, 0) == -999){
                    resultField.setText("Matriks adalah matriks singular dan tidak memiliki balikan.");
                }

                for (int i = 0; i < M.getRow(M); i++) {
                    for (int j = 0; j < M.getCol(M); j++) {
                        result.append(resultMatrix.getElement( i, j) + " ");
                    }
                    result.append("\n");
                }

                resultOutput(resultField, result);
            }
        }
        catch(Exception e){
            resultField.setText("Error: " + e);
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
