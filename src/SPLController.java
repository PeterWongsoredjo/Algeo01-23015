import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.stage.Stage;
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
