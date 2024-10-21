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
        String sizeMatrix = ukuranMatrix.getText();

        int intRows = Integer.parseInt(sizeMatrix);
        int intCols = Integer.parseInt(sizeMatrix);
        
        matrixDeterminan.CreateMatrix(matrixDeterminan, intRows, intCols);
        System.out.println("Matrix Created.");
    }

    @FXML
    public void fillMatrix(){
        String fillMatrix = inputMatrix.getText();

        String[] rows = fillMatrix.trim().split("\n");
        for (int i = 0; i < matrixDeterminan.getRow(matrixDeterminan); i++) {
            String[] elements = rows[i].trim().split("\\s+");
    
            for (int j = 0; j < matrixDeterminan.getCol(matrixDeterminan); j++) {
                matrixDeterminan.setElement(matrixDeterminan, i, j, Double.parseDouble(elements[j]));
            }
        }
    }

    public void DeterminanInversRun(){
        sizeMatrix();
        fillMatrix();
        double resultMatrix;
        matrixDeterminan.printMatrix(matrixDeterminan);
        resultMatrix = determinanNormal.determinan(matrixDeterminan);
        StringBuilder result = new StringBuilder();
        result.append(resultMatrix);
        outputMatrix.setText(result.toString());
    }

    public void DeterminanControllerRun(){
        sizeMatrix();
        fillMatrix();
        double resultMatrix;
        matrixDeterminan.printMatrix(matrixDeterminan);
        resultMatrix = determinanBaris.determinanbaris(matrixDeterminan);
        StringBuilder result = new StringBuilder();
        result.append(resultMatrix);
        outputMatrix.setText(result.toString());
    }

    public void DeterminanKofaktorRun(){
        sizeMatrix();
        fillMatrix();
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