public class DeterminanKofaktor extends Matrix {
  public double detkof(Matrix M) {
    Kofaktor kof = new Kofaktor();

    double determinankof;
    determinankof = kof.hitung(M);

    return determinankof;
  }
}
