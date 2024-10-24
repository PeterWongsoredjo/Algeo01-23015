import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class SPLController {
    @FXML
    private TextField rowField;

    @FXML
    private TextField colField;

    @FXML
    private TextArea xField;

    @FXML
    private TextArea yField;

    @FXML
    private TextArea outputField;

    Matrix M = new Matrix();
    Matrix matrixX = new Matrix();
    Matrix matrixY = new Matrix();
    SPL spl = new SPL();
    SPLInvers invers = new SPLInvers();
    SPLCramer cramer = new SPLCramer();

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void sizeMatrix(){
        String rows = rowField.getText();
        String cols = colField.getText();

        int intRows = Integer.parseInt(rows);
        int intCols = Integer.parseInt(cols);

        System.err.println(intRows);
        System.err.println(intCols);

        matrixX.CreateMatrix(matrixX, intRows, intCols);
        matrixY.CreateMatrix(matrixY, intRows, 1);
        System.out.println("Matrix Created.");
    }

    @FXML
    public void fillMatrix(Matrix mX, Matrix mY){
        String fillX = xField.getText();
        String fillY = yField.getText();

        String[] rows = fillX.trim().split("\n");
        for (int i = 0; i < mX.getRow(mX); i++) {
            String[] elements = rows[i].trim().split("\\s+");
    
            for (int j = 0; j < mX.getCol(mX); j++) {
                mX.setElement(mX, i, j, Double.parseDouble(elements[j]));
            }
        }
        
        String[] cols = fillY.trim().split("\n");
        for (int i = 0; i < mY.getRow(mY); i++){
            mY.setElement(mY, i, mY.getCol(mY) - 1, Double.parseDouble(cols[i]));
        }
    }
    
    public void resultOutput(TextArea resulTextArea, StringBuilder result){
        resulTextArea.setText(result.toString());
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
        StringBuilder xContent = new StringBuilder();
        StringBuilder yContent = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            String firstLine = null;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    firstLine = line.trim(); 
                    isFirstLine = false;
                } else {
                    String[] values = line.trim().split("\\s+");
                    for (int i = 0; i < values.length - 1; i++) {
                        xContent.append(values[i]).append(" ");
                    }
                    xContent.append("\n");
                    yContent.append(values[values.length - 1]).append("\n");
                }
            }

            if (firstLine != null && !firstLine.isEmpty()) {
                String[] dimensions = firstLine.split("\\s+");
                if (dimensions.length == 2) {
                    rowField.setText(dimensions[0]);
                    colField.setText(dimensions[1]);
                } else {
                    outputField.setText("Error: First line must contain exactly two numbers (row and column).");
                    return;
                }
            } else {
                outputField.setText("Error: First line (row and column) is empty.");
                return;
            }

            xField.setText(xContent.toString().trim());
            yField.setText(yContent.toString().trim());
        } catch (IOException e) {
            outputField.setText("Error: " + e.getMessage());
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
            bw.write(outputField.getText());
        } catch (IOException e) {
            outputField.setText("Error: " + e.getMessage());
        }
    }
    
    public void gaussRun(){
        sizeMatrix();
        fillMatrix(matrixX, matrixY);
        boolean kosong = false, no_solution = false;
        StringBuilder result = new StringBuilder();
        spl.gauss(matrixX, matrixY, kosong, no_solution, result);
        resultOutput(outputField, result);
    }

    public void gaussJordanRun(){
        sizeMatrix();
        fillMatrix(matrixX, matrixY);
        boolean kosong = false, no_solution = false;
        StringBuilder result = new StringBuilder();
        spl.gaussjordan(matrixX, matrixY, kosong, no_solution, result);
        resultOutput(outputField, result);
    }

    public void inversRun(){
        sizeMatrix();
        fillMatrix(matrixX, matrixY);
        boolean kosong = false, no_solution = false;
        StringBuilder result = new StringBuilder();
        invers.SPLinv(matrixX, matrixY, kosong, no_solution, result);
        resultOutput(outputField, result);
        // resultOutput(resultMatrix, outputField);
    }

    public void cramerRun(){
        sizeMatrix();
        fillMatrix(matrixX, matrixY);
        boolean kosong = false, no_solution = false;
        StringBuilder result = new StringBuilder();
        cramer.cramer(matrixX, matrixY, kosong, no_solution, result);
        resultOutput(outputField, result);
        // resultOutput(resultMatrix, outputField);
    }

    public void switchToHome (ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
