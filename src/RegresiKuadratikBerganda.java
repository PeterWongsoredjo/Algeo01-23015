public class RegresiKuadratikBerganda {
    public void regresikuadratik(Matrix MX, Matrix MY, StringBuilder result){


        Matrix ExtendedX = new Matrix();
        ExtendedX.CreateMatrix(ExtendedX, MX.getRow(MX), 6);

        // Bikin Matrix ExtendedX
        for(int i = 0; i < ExtendedX.getRow(ExtendedX); i++){
            double x1 = MX.getElement(i, 0);
            double x2 = MX.getElement(i, 1);

            ExtendedX.setElement(ExtendedX, i, 0, 1);
            ExtendedX.setElement(ExtendedX, i, 1, x1);
            ExtendedX.setElement(ExtendedX, i, 2, x2);
            ExtendedX.setElement(ExtendedX, i, 3, x1 * x1);
            ExtendedX.setElement(ExtendedX, i, 4, x2 * x2);
            ExtendedX.setElement(ExtendedX, i, 5, x1 * x2);
        }
        //ExtendedX.printMatrix(ExtendedX);

        // Bikin Matrix Transpose dari Extend
        Matrix TransposeX = new Matrix();
        TransposeX = ExtendedX.transposeMatrix(ExtendedX);
        //TransposeX.printMatrix(TransposeX);

        // Kaliin Transpose sama MX
        Matrix XTX = new Matrix();
        XTX = XTX.multiplyMatrix(TransposeX, ExtendedX);
        //XTX.printMatrix(XTX);
        
        // Kaliin Transpose sama MY
        Matrix XTY = new Matrix();
        XTY = XTY.multiplyMatrix(TransposeX, MY);
        //XTY.printMatrix(XTY);

        // SPL
        SPL spl = new SPL();
        spl.gauss(XTX, XTY, false, false, result);
    }

    private boolean kosong(Matrix M, int i){ 
        boolean temp_kosong = true;
        for(int j=0; j < M.getLastColIdx(M); j++){
            // Cek Kosong
            if (M.getElement(i, j) != 0){
                temp_kosong =false;
            }
        }
        return temp_kosong;
    }

    public void regresikuadratiktaksir(Matrix MX, Matrix MY, double[] xi, StringBuilder result) {

        int numVariables = MX.getCol(MX); 
        int numDataPoints = MX.getRow(MX); 
    
        int numTerms = 1 + numVariables + numVariables + (numVariables * (numVariables - 1)) / 2;
    
        Matrix ExtendedX = new Matrix();
        ExtendedX.CreateMatrix(ExtendedX, numDataPoints, numTerms);

        for (int i = 0; i < numDataPoints; i++) {
            int col = 0;
            ExtendedX.setElement(ExtendedX, i, col++, 1);
    
            for (int j = 0; j < numVariables; j++) {
                double x = MX.getElement(i, j);
                ExtendedX.setElement(ExtendedX, i, col++, x);
            }
    
            for (int j = 0; j < numVariables; j++) {
                double x = MX.getElement(i, j);
                ExtendedX.setElement(ExtendedX, i, col++, x * x);
            }
    
            for (int j = 0; j < numVariables; j++) {
                for (int k = j + 1; k < numVariables; k++) {
                    double x1 = MX.getElement(i, j);
                    double x2 = MX.getElement(i, k);
                    ExtendedX.setElement(ExtendedX, i, col++, x1 * x2);
                }
            }
        }
    
        Matrix TransposeX = ExtendedX.transposeMatrix(ExtendedX);
    
        // Multiply TransposeX with ExtendedX (XTX)
        Matrix XTX = new Matrix();
        XTX = XTX.multiplyMatrix(TransposeX, ExtendedX);
    
        // Multiply TransposeX with MY (XTY)
        Matrix XTY = new Matrix();
        XTY = XTY.multiplyMatrix(TransposeX, MY);
    
        // Create augmented matrix for Gaussian elimination
        Matrix temp = new Matrix();
        Matrix hasil = new Matrix();
        temp.CreateMatrix(temp, XTX.getRow(XTX), XTX.getCol(XTX) + 1);
    
        for (int i = 0; i < XTX.getRow(XTX); i++) {
            for (int j = 0; j < XTX.getCol(XTX); j++) {
                temp.setElement(temp, i, j, XTX.getElement(i, j));
            }
            // Append XTY as the last column
            temp.setElement(temp, i, temp.getLastColIdx(temp), XTY.getElement(i, 0));
        }
    
        // Apply Gaussian elimination and Gauss-Jordan elimination
        Gauss gauss = new Gauss();
        gauss.gauss(temp);
    
        GaussJordan GJ = new GaussJordan();
        GJ.gaussjordan(temp);
    
        // Extract the solution (regression coefficients)
        hasil.CreateMatrix(hasil, XTX.getRow(XTX), 1);
        for (int i = 0; i <= temp.getLastRowIdx(temp); i++) {
            hasil.setElement(hasil, i, hasil.getLastColIdx(hasil), temp.getElement(i, temp.getLastColIdx(temp)));
        }
    
        // Compute the predicted y using the calculated coefficients and the input values xi[]
        double result_y = hasil.getElement(0, 0); // Intercept
        int col = 1;
    
        // Linear terms
        for (int i = 0; i < xi.length; i++) {
            result_y += hasil.getElement(col++, 0) * xi[i];
        }
    
        // Quadratic terms
        for (int i = 0; i < xi.length; i++) {
            result_y += hasil.getElement(col++, 0) * xi[i] * xi[i];
        }
    
        // Interaction terms
        for (int i = 0; i < xi.length; i++) {
            for (int j = i + 1; j < xi.length; j++) {
                result_y += hasil.getElement(col++, 0) * xi[i] * xi[j];
            }
        }
    
        // Output the result
        result.append("Hasil taksiran y = " + result_y);
    }
    
}
