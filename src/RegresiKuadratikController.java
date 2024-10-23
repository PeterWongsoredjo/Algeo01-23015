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
    public void sizeMatrix(Matrix mX1, Matrix mY){
        String sizeMatrix = inputCase.getText();

        int intRows = Integer.parseInt(sizeMatrix);
        int intCols = 2;

        mX1.CreateMatrix(mX1, intRows, intCols);
        mY.CreateMatrix(mY, intRows, 1);
    }

    @FXML
    public void fillMatrix(Matrix mX, Matrix mY){
        String[] rowsX = inputAreaX.getText().split("\n");
        String[] rowsY = inputAreaY.getText().split("\n");

        for (int i = 0; i < rowsX.length; i++) {
            String[] elementsX = rowsX[i].trim().split("\\s+");
            String[] elementsY = rowsY[i].trim().split("\\s+");

            for(int j = 0; j < elementsX.length; j++){
                mX.setElement(mX, i, j, Double.parseDouble(elementsX[j]));
            }
            mY.setElement(mY, i, 0, Double.parseDouble(elementsY[0]));
        }
    }

    public void resultOutput(TextArea resulTextArea, StringBuilder result){
        resulTextArea.setText(result.toString());
    }

    public void regresiKuadratikRun(){
        try{
            Matrix mX = new Matrix();
            Matrix mY = new Matrix();

            sizeMatrix(mX, mY);
            fillMatrix(mX, mY);

            StringBuilder result = new StringBuilder();

            RegresiKuadratikBerganda regresi = new RegresiKuadratikBerganda();
            regresi.regresikuadratik(mX, mY, result);

            resultOutput(resultField, result);
        } catch (Exception e){
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