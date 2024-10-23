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

public class RegresiController {
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
    private TextField inputX;

    @FXML
    public void sizeMatrix(Matrix mX, Matrix mY){
        String sizeMatrix = inputCase.getText();
        String sizeX = inputX.getText();

        int intRows = Integer.parseInt(sizeMatrix);
        int intCols = Integer.parseInt(sizeX);

        mX.CreateMatrix(mX, intRows, intCols);
        mY.CreateMatrix(mY, intRows, intCols);
    }

    @FXML
    public void fillMatrix(Matrix mX, Matrix mY){
        String[] rowsX = inputAreaX.getText().split("\n");
        for (int i = 0; i < rowsX.length; i++) {
            String[] elementsX = rowsX[i].trim().split("\\s+");

            for (int j = 0; j < elementsX.length; j++) {
                mX.setElement(mX, i, j, Double.parseDouble(elementsX[j]));
            }
        }

        String[] rowsY = inputAreaY.getText().split("\n");
        for (int i = 0; i < rowsX.length; i++) {
            String[] elementsY = rowsY[i].trim().split("\\s+");
                mY.setElement(mY, i, 0, Double.parseDouble(elementsY[0]));
        }
    }

    public void resultOutput(TextArea resulTextArea, StringBuilder result){
        resulTextArea.setText(result.toString());
    }

    public void regresiLinearRun(){
        try{
            Matrix mX = new Matrix();
            Matrix mY = new Matrix();
            sizeMatrix(mX, mY);
            fillMatrix(mX, mY);

            StringBuilder result = new StringBuilder();
            Matrix resultMatrix = new Matrix();
            resultMatrix.CreateMatrix(resultMatrix, 2, 1);

            RegresiLinearBerganda regresiLinear = new RegresiLinearBerganda();
            
            Matrix hasil = regresiLinear.processRegression(mX, mY);

            for (int i = 0; i < hasil.getRow(hasil); i++) {
                for (int j = 0; j < hasil.getCol(hasil); j++) {
                    result.append(hasil.getElement(i, j));
                }
                result.append("\n");
            }
            hasil.printMatrix(hasil);

            resultOutput(resultField, result);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void switchToRegresiKuadratik (ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("RegresiKuadratik.fxml"));
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
