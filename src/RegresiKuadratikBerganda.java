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
        ExtendedX.printMatrix(ExtendedX);

        // Bikin Matrix Transpose dari Extend
        Matrix TransposeX = new Matrix();
        TransposeX = ExtendedX.transposeMatrix(ExtendedX);
        TransposeX.printMatrix(TransposeX);

        // Kaliin Transpose sama MX
        Matrix XTX = new Matrix();
        XTX = XTX.multiplyMatrix(TransposeX, ExtendedX);
        XTX.printMatrix(XTX);
        
        // Kaliin Transpose sama MY
        Matrix XTY = new Matrix();
        XTY = XTY.multiplyMatrix(TransposeX, MY);
        XTY.printMatrix(XTY);

        // SPL
        SPL spl = new SPL();
        spl.gauss(XTX, XTY, false, false, result);
    }
}
