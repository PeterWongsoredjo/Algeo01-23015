public class SPLCramer extends Matrix {
  public Matrix cramer(Matrix M1, Matrix M2) {
    DeterminanKofaktor det = new DeterminanKofaktor();

    double detM = det.detkof(M1);

    Matrix mHasil = new Matrix();
    CreateMatrix(mHasil, getRow(M1), 1);

    Matrix temp = new Matrix();
    CreateMatrix(temp, getRow(M1), getCol(M1));

    for (int i = 0; i < getCol(M1); i++) {
      for (int j = 0; j < getRow(M1); j++) {
        setElement(temp, j, i, M2.getElement(j, 0));
      }
      double tempDet = det.detkof(temp);
      double detAkhir = tempDet / detM;
      setElement(mHasil, i, 0, detAkhir);
    }

    return mHasil;
  }
}