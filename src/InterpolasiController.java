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

    public void resultOutput(TextField resultField, StringBuilder result){
        resultField.setText(result.toString());
    }

    @FXML
    private void calculateInterpolate(ActionEvent event) {
        try {
            int degree = Integer.parseInt(inputN.getText());
            Matrix M = new Matrix();
            fillMatrix(M, degree);

            // Parse the x input
            double x = Double.parseDouble(inputX.getText());

            GJ.gaussjordan(M);
        
            Matrix hasil = new Matrix();
            hasil.CreateMatrix(hasil, M.getRow(M), 1);
            for(int i = 0; i<M.getRow(M); i++){
                hasil.setElement(hasil, i, 0, M.getElement(i,M.getLastColIdx(M)));
            }

            double result = 0;
            for(int i = 0; i<M.getRow(M);i++){
                result += (hasil.getElement(i,0) * Math.pow(x, i));
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
