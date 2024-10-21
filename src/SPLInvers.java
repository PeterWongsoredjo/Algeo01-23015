public class SPLInvers extends Matrix {
  public Matrix SPLinv(Matrix M1, Matrix M2) {

    Matrix mTemp = new Matrix();
    CreateMatrix(mTemp, getRow(M1), getCol(M1));

    InversAdj balikan = new InversAdj();

    if (M1.getElement(0, 0) == -999) {
      return M1;
    } else {
      copyMatrix(balikan.balikan(M1), mTemp);
    }

    Matrix mHasil = mTemp.multiplyMatrix(mTemp, M2);

    return mHasil;
  }
}
