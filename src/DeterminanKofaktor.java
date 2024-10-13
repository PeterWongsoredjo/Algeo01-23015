public class DeterminanKofaktor {
  public double detkof(Matrix M) {
    Kofaktor kof = new Kofaktor();

    double determinankof;
    // Menghitung determinan
    determinankof = kof.hitung(M);

    return determinankof;
  }
}
