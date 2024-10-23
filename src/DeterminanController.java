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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DeterminanController {
    @FXML
    private TextField ukuranMatrix;

    @FXML
    private TextArea inputMatrix; 
    
    @FXML
    private TextArea outputMatrix;

    private Stage stage;
    private Scene scene;
    private Parent root;

    Determinan determinanNormal = new Determinan();
    DeterminanBaris determinanBaris = new DeterminanBaris();
    DeterminanKofaktor determinanKofaktor = new DeterminanKofaktor();

    Matrix matrixDeterminan = new Matrix();

    @FXML
    public void sizeMatrix(){
        String size = ukuranMatrix.getText();

        int intSize = Integer.parseInt(size);

        System.err.println(intSize);

        matrixDeterminan.CreateMatrix(matrixDeterminan, intSize, intSize);
        System.out.println("Matrix Created.");
    }

    @FXML
    public void fillMatrix(Matrix matrixDeterminan){
        String fillMatrixDeterminan = inputMatrix.getText();

        String[] rows = fillMatrixDeterminan.trim().split("\n");
        for (int i = 0; i < matrixDeterminan.getRow(matrixDeterminan); i++) {
            String[] elements = rows[i].trim().split("\\s+");
    
            for (int j = 0; j < matrixDeterminan.getCol(matrixDeterminan); j++) {
                matrixDeterminan.setElement(matrixDeterminan, i, j, Double.parseDouble(elements[j]));
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
                    firstLine = line.trim(); // Assume the first line contains the size of the matrix (n)
                    isFirstLine = false;
                } else {
                    content.append(line).append("\n");
                }
            }
            inputMatrix.setText(content.toString());

            // Set the value of ukuranMatrix
            if (firstLine != null && !firstLine.isEmpty()) {
                ukuranMatrix.setText(firstLine);
            } else {
                outputMatrix.setText("Error: First line (size) is empty.");
                return;
            }
        } catch (IOException e) {
            outputMatrix.setText("Error: " + e.getMessage());
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
            bw.write(outputMatrix.getText());
        } catch (IOException e) {
            outputMatrix.setText("Error: " + e.getMessage());
        }
    }

    public void DeterminanInversRun(){
        sizeMatrix();
        fillMatrix(matrixDeterminan);
        double resultMatrix;
        matrixDeterminan.printMatrix(matrixDeterminan);
        resultMatrix = determinanNormal.determinan(matrixDeterminan);
        StringBuilder result = new StringBuilder();
        result.append(resultMatrix);
        outputMatrix.setText(result.toString());
    }

    public void DeterminanControllerRun(){
        sizeMatrix();
        fillMatrix(matrixDeterminan);
        double resultMatrix;
        matrixDeterminan.printMatrix(matrixDeterminan);
        resultMatrix = determinanBaris.determinanbaris(matrixDeterminan);
        StringBuilder result = new StringBuilder();
        result.append(resultMatrix);
        outputMatrix.setText(result.toString());
    }

    public void DeterminanKofaktorRun(){
        sizeMatrix();
        fillMatrix(matrixDeterminan);
        double resultMatrix;
        matrixDeterminan.printMatrix(matrixDeterminan);
        resultMatrix = determinanKofaktor.detkof(matrixDeterminan);
        StringBuilder result = new StringBuilder();
        result.append(resultMatrix);
        outputMatrix.setText(result.toString());
    }

    public void switchToHome (ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}