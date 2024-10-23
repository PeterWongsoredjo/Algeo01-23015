public class RegresiLinearBerganda {
    public Matrix processRegression(Matrix M, Matrix y) {
        /* KEDUA MATRIX TEST CASE DIANGGAP SUDAH ADA */
        //Matrix M adalah Matrix test case
        //Matrix y adalah Matrix hasil
        Matrix result = new Matrix();
        InversAdj invers = new InversAdj();
        result = y.multiplyMatrix(result.multiplyMatrix(invers.balikan(result.multiplyMatrix(M.transposeMatrix(M), M)), M.transposeMatrix(M)), y);
        return result;
    }

    public void printprocessRegression(Matrix M, Matrix y){
        Matrix result = new Matrix();
        InversAdj invers = new InversAdj();
        result = y.multiplyMatrix(result.multiplyMatrix(invers.balikan(result.multiplyMatrix(M.transposeMatrix(M), M)), M.transposeMatrix(M)), y);
        result.printMatrix(result);
    }
}