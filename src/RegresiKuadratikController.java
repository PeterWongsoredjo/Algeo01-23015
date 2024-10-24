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
import java.util.ArrayList;
import java.util.List;

public class RegresiKuadratikController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextArea inputAreaX;

    @FXML
    private TextArea inputAreaY;

    @FXML
    private TextArea resultField;

    @FXML
    private TextField inputCase;

    @FXML
    private TextField newInputField;

    @FXML
    private void sizeMatrix(Matrix mX, Matrix mY) {
        int numRows = inputAreaX.getText().split("\n").length;
        int numCols = inputAreaX.getText().split("\\s+").length / numRows;

        mX.CreateMatrix(mX, numRows, numCols);
        mY.CreateMatrix(mY, numRows, 1);
    }

    private void fillMatrix(Matrix mX, Matrix mY) {
        String[] rowsX = inputAreaX.getText().split("\n");
        for (int i = 0; i < rowsX.length; i++) {
            String[] elementsX = rowsX[i].trim().split("\\s+");
            for (int j = 0; j < elementsX.length; j++) {
                mX.setElement(mX, i, j, Double.parseDouble(elementsX[j]));
            }
        }

        String[] rowsY = inputAreaY.getText().split("\n");
        for (int i = 0; i < rowsY.length; i++) {
            mY.setElement(mY, i, 0, Double.parseDouble(rowsY[i].trim()));
        }
    }

    public void resultOutput(TextArea resulTextArea, StringBuilder result){
        resulTextArea.setText(result.toString());
    }

    @FXML
    public void handleFileLoad() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        if (file != null) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                StringBuilder matrixX = new StringBuilder();
                StringBuilder matrixY = new StringBuilder();
                String line;
                List<String> lines = new ArrayList<>();

                while ((line = br.readLine()) != null) {
                    lines.add(line);
                }

                if (!lines.isEmpty()) {
                    String[] firstLine = lines.get(0).split("\\s+");
                    inputCase.setText(firstLine[0]);
                    inputAreaX.setText(firstLine[1]);
                }

                for (int i = 1; i < lines.size() - 1; i++) {
                    String[] values = lines.get(i).split("\\s+");
                    for (int j = 0; j < values.length - 1; j++) {
                        matrixX.append(values[j]).append(" ");
                    }
                    matrixX.append("\n");
                    matrixY.append(values[values.length - 1]).append("\n");
                }

                inputAreaX.setText(matrixX.toString());
                inputAreaY.setText(matrixY.toString());

                if (!lines.isEmpty()) {
                    newInputField.setText(lines.get(lines.size() - 1));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    public void handleFileSave() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(stage);
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        if (file != null) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                bw.write(inputCase.getText() + "\n");

                String[] matrixXLines = inputAreaX.getText().split("\n");
                String[] matrixYLines = inputAreaY.getText().split("\n");

                for (int i = 0; i < matrixXLines.length; i++) {
                    bw.write(matrixXLines[i] + " " + matrixYLines[i] + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void regresiKuadratikRun() {
        try {
            Matrix mX = new Matrix();
            Matrix mY = new Matrix();

            sizeMatrix(mX, mY);
            fillMatrix(mX, mY);

            String newInput = newInputField.getText();
            String[] newInputValues = newInput.split("\\s+");
            double[] xValues = new double[newInputValues.length];
            for (int i = 0; i < newInputValues.length; i++) {
                xValues[i] = Double.parseDouble(newInputValues[i]);
            }

            StringBuilder result = new StringBuilder();

            RegresiKuadratikBerganda regresi = new RegresiKuadratikBerganda();

            regresi.regresikuadratiktaksir(mX, mY, xValues, result);

            resultOutput(resultField, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public void switchToRegresi (ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Regresi.fxml"));
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

}
