public class InversAdj extends Matrix {
  public Matrix balikan(Matrix M) {
    DeterminanKofaktor det = new DeterminanKofaktor();
    Kofaktor kof = new Kofaktor();

    // State variabel
    double determinan, adj;
    Matrix kofaktor = kof.keluaran(M);
    int i, j;

    // Buat Matriks invers
    Matrix mInvers = new Matrix();
    CreateMatrix(mInvers, getRow(kofaktor), getCol(kofaktor));
    determinan = det.detkof(M);

    // Input Matriks Invers
    for (i = 0; i < getRow(kofaktor); i++) {
      for (j = 0; j < getCol(kofaktor); j++) {
        if (determinan == 0) {
          adj = 0;
        } else {
          adj = (double) (1 / determinan) * kofaktor.getElement(j, i);
        }
        setElement(mInvers, i, j, adj);
      }
    }

    return mInvers;
  }
}