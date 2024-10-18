public class SPLCramer extends Matrix {
  public Matrix cramer(Matrix M1, Matrix M2) {
    double MARK = -999;

    // Menghitung determinan awal
    DeterminanKofaktor det = new DeterminanKofaktor();
    double detM = det.detkof(M1);

    // Hitung Hasil
    Matrix mHasil = new Matrix();
    CreateMatrix(mHasil, getRow(M1), 1);
    if (detM == 0) {
      for (int i = 0; i < getRow(M1); i++) {
        setElement(mHasil, i, 0, MARK);
      }
    } else { // Jika determinan awal tidak 0
      for (int i = 0; i < getRow(M1); i++) {
        Matrix temp = new Matrix();
        CreateMatrix(temp, getRow(M1), getCol(M1));
        copyMatrix(M1, temp);
        for (int j = 0; j < getRow(M1); j++) {
          setElement(temp, j, i, M2.getElement(j, 0));
        }
        double tempDet = det.detkof(temp);
        double detAkhir = tempDet / detM;
        setElement(mHasil, i, 0, detAkhir);
      }
    }
    return mHasil;
  }
}