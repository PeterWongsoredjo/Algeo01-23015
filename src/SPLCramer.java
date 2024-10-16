public class SPLCramer extends Matrix {
  public Matrix cramer(Matrix M) {
    double MARK = -999;

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

    // Menghitung determinan awal
    DeterminanKofaktor det = new DeterminanKofaktor();
    double detM = det.detkof(M1);

    Matrix temp = new Matrix();
    CreateMatrix(temp, getRow(M1), getCol(M1));
    copyMatrix(M1, temp);

    // Hitung Hasil
    Matrix mHasil = new Matrix();
    CreateMatrix(mHasil, getRow(M), 1);
    if (detM == 0) {
      for (int i = 0; i < getRow(M1); i++) {
        setElement(mHasil, i, 0, MARK);
      }
    } else { // Jika determinan awal tidak 0
      for (int i = 0; i < getRow(M1); i++) {
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