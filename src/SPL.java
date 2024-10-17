public class SPL {
    public Matrix gauss(Matrix M1, Matrix M2){
        Matrix temp = new Matrix();
        Matrix hasil = new Matrix();
        Gauss gauss = new Gauss();

        temp.CreateMatrix(temp, M1.getRow(M1), M1.getCol(M1) + 1);

        for(int i = 0; i<M1.getRow(M1); i++){
            for(int j = 0; j<M1.getCol(M1); j++){
                temp.setElement(temp, i, j, M1.getElement(i, j));
            }
        }

        for(int i = 0; i < M1.getRow(M1); i++){
            temp.setElement(temp, i, temp.getLastColIdx(temp), M2.getElement(i,0));
        }

        hasil.CreateMatrix(hasil, M1.getRow(M1), 1);

        gauss.gauss(temp);

        for (int i = M1.getRow(M1) - 1; i >= 0; i--) {
            for (int k = i - 1; k >= 0; k--) {
                double factor = temp.getElement(k, i);
                for (int j = 0; j < M1.getCol(M1); j++) {
                    temp.setElement(temp, k, j, temp.getElement(k, j) - factor * temp.getElement(i, j));
                }
            }
        }

        for(int i = 0; i<=temp.getLastRowIdx(temp); i++){
            hasil.setElement(hasil, i, hasil.getLastColIdx(hasil), temp.getElement(i, temp.getLastColIdx(temp)));
        }

        return hasil;
    }

    public Matrix gaussjordan(Matrix M1, Matrix M2){
        Matrix temp = new Matrix();
        Matrix hasil = new Matrix();

        GaussJordan GJ = new GaussJordan();

        temp.CreateMatrix(temp, M1.getRow(M1), M1.getCol(M1) + 1);

        for(int i = 0; i<M1.getRow(M1); i++){
            for(int j = 0; j<M1.getCol(M1); j++){
                temp.setElement(temp, i, j, M1.getElement(i, j));
            }
        }

        for(int i = 0; i < M1.getRow(M1); i++){
            temp.setElement(temp, i, temp.getLastColIdx(temp), M2.getElement(0, i));
        }

        hasil.CreateMatrix(hasil, M1.getRow(M1), 1);

        GJ.gaussjordan(temp);

        for(int i = 0; i<temp.getRow(temp); i++){
            hasil.setElement(hasil, i, 0, temp.getElement(i, temp.getLastColIdx(temp)));
        }

        return hasil;
    }
}
