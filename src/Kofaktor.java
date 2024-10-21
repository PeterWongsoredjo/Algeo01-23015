public class Kofaktor extends Matrix {
  // Mencari Matriks Kofaktor
  public Matrix keluaran(Matrix mIn) {
    int i, j;
    Matrix mOut = new Matrix();
    CreateMatrix(mOut, getRow(mIn), getCol(mIn));
    Matrix mTemp = new Matrix();

    for (i = 0; i < getRow(mIn); i++) {
      for (j = 0; j < getCol(mIn); j++) {
        // Buat matriks sementara
        mTemp = minor(mIn, i, j);

        // Cek apakah - atau +
        int pangkat = ((i + j) % 2 == 0) ? 1 : -1;
        double det = pangkat * hitung(mTemp);
        if (det == -0.0) {
          det = 0.0;
        }

        setElement(mOut, i, j, det);
      }
    }

    return mOut;
  }

  // Menghitung Kofaktor
  public double hitung(Matrix M) {
    double hitung = 0;

    // Hitung Determinan
    if (getRow(M) == 1) {
      return M.getElement(0, 0);
    }

    if (getRow(M) == 2) {
      return M.getElement(0, 0) * M.getElement(1, 1)
          - M.getElement(0, 1) * M.getElement(1, 0);
    }
    // Jika matriks masih lebih dari 2x2
    for (int i = 0; i < getCol(M); i++) {
      Matrix mTemp = minor(M, 0, i);
      int pangkat = (i % 2 == 0) ? 1 : -1;
      hitung += pangkat * M.getElement(0, i) * hitung(mTemp);
    }

    return hitung;
  }

  // Membuat matriks kofaktor
  public Matrix minor(Matrix M, int row, int col) {
    // Buat matriks dengan ukuran dikurang 1
    Matrix kofak1 = new Matrix();
    CreateMatrix(kofak1, getRow(M) - 1, getCol(M) - 1);

    // Input Matriks ke Matriks sementara
    int r = 0, c = 0;
    for (int i = 0; i < getRow(M); i++) {
      if (i == row) {
        continue;
      }
      c = 0;
      for (int j = 0; j < getCol(M); j++) {
        if (j == col) {
          continue;
        }
        setElement(kofak1, r, c, M.getElement(i, j));
        c++;
      }
      r++;
    }
    return kofak1;
  }
}