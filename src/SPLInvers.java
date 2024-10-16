public class SPLInvers extends Matrix {
  public Matrix SPLinv(Matrix M) {
    // Buat Matriks 1
    Matrix M1 = new Matrix();
    CreateMatrix(M1, getRow(M), getCol(M) - 1);
    for (int i = 0; i < getRow(M); i++) {
      for (int j = 0; j < getCol(M) - 1; j++) {
        setElement(M1, i, j, M.getElement(i, j));
      }
    }

    // Buat Matriks 2
    Matrix M2 = new Matrix();
    CreateMatrix(M2, getRow(M), 1);
    for (int i = 0; i < getRow(M); i++) {
      setElement(M2, i, 0, M.getElement(i, getCol(M) - 1));
    }

    Matrix mTemp = new Matrix();
    CreateMatrix(mTemp, getRow(M1), getCol(M1));

    InversAdj balikan = new InversAdj();

    if (M1.getElement(0, 0) == -999) {
      return M1;
    } else {
      copyMatrix(balikan.balikan(M1), mTemp);
    }

    multiplyMatrix(mTemp, M2);

    return mTemp;
  }
}
