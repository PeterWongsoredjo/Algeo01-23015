public class InversAdj extends Matrix {
  public Matrix balikan(Matrix M) {
    /* WARNING: BELUM PUNYA VALIDATOR */
    double MARK = -999;

    DeterminanKofaktor det = new DeterminanKofaktor();
    Kofaktor kof = new Kofaktor();

    // State variabel
    double determinan, adj;
    Matrix kofaktor = kof.keluaran(M);

    // Buat Matriks invers
    determinan = det.detkof(M);
    Matrix mInvers = new Matrix();
    CreateMatrix(mInvers, getRow(kofaktor), getCol(kofaktor));

    // Input Matriks Invers
    if (determinan == 0 || getRow(M) != getCol(M)) {
      for (int i = 0; i < getRow(M); i++) {
        for (int j = 0; j < getCol(M); j++) {
          setElement(mInvers, i, j, MARK);
        }
      }
    } else {
      for (int i = 0; i < getRow(kofaktor); i++) {
        for (int j = 0; j < getCol(kofaktor); j++) {
          adj = (1.0 / determinan) * kofaktor.getElement(j, i);
          setElement(mInvers, i, j, adj);
        }
      }
    }
    return mInvers;
  }

  public void printbalikan(Matrix M){
    /* WARNING: BELUM PUNYA VALIDATOR */
    double MARK = -999;

    DeterminanKofaktor det = new DeterminanKofaktor();
    Kofaktor kof = new Kofaktor();

    // State variabel
    double determinan, adj;
    Matrix kofaktor = kof.keluaran(M);

    // Buat Matriks invers
    determinan = det.detkof(M);
    Matrix mInvers = new Matrix();
    CreateMatrix(mInvers, getRow(kofaktor), getCol(kofaktor));

    // Input Matriks Invers
    if (determinan == 0 || getRow(M) != getCol(M)) {
      System.out.println("Matriks tidak memiliki invers");
    } else {
      for (int i = 0; i < getRow(kofaktor); i++) {
        for (int j = 0; j < getCol(kofaktor); j++) {
          adj = (1.0 / determinan) * kofaktor.getElement(j, i);
          setElement(mInvers, i, j, adj);
        }
      }
    }

    mInvers.printMatrix(mInvers);
  }
}