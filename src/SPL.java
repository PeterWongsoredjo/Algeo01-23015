public class SPL {
    public Matrix gauss(Matrix M){
        Matrix temp = new Matrix();
        Matrix hasil = new Matrix();
        Gauss gauss = new Gauss();

        temp.copyMatrix(M, temp);
        hasil.CreateMatrix(hasil, M.getRow(M), 1);

        gauss.gauss(temp);
        
        GaussJordan GJ = new GaussJordan();

        for (int i = M.getRow(M) - 1; i >= 0; i--) {
            for (int k = i - 1; k >= 0; k--) {
                double factor = temp.getElement(k, i);
                for (int j = 0; j < M.getCol(M); j++) {
                    temp.setElement(temp, k, j, temp.getElement(k, j) - factor * temp.getElement(i, j));
                }
            }
        }

        for(int i = 0; i<=temp.getLastRowIdx(temp); i++){
            hasil.setElement(hasil, i, hasil.getLastColIdx(hasil), temp.getElement(i, temp.getLastColIdx(temp)));
        }

        return hasil;
    }

    public Matrix gaussjordan(Matrix M){
        Matrix temp = new Matrix();
        Matrix hasil = new Matrix();

        temp.copyMatrix(M, temp);
        hasil.CreateMatrix(hasil, M.getRow(M), 1);

        gaussjordan(temp);

        for(int i = 0; i<temp.getRow(temp); i++){
            hasil.setElement(hasil, i, 0, temp.getElement(i, temp.getLastColIdx(temp)));
        }

        return hasil;
    }
}
