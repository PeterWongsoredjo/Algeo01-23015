public class SPLInvers extends Matrix {
  public Matrix SPLinv(Matrix M1, Matrix M2) {
    // Ax = B;
    // x = B * A invers

    Matrix mTemp = new Matrix();
    CreateMatrix(mTemp, getRow(M1), 1);

    InversAdj balikan = new InversAdj();
    mTemp = balikan.balikan(M1);

    multiplyMatrix(M2, mTemp);

    return M2;
  }
}
