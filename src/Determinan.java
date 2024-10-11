public class Determinan {
    void determinan(Matrix M){
        Matrix temp = new Matrix();
        temp = M;

        Gauss G = new Gauss();
        G.gauss(temp);

        double result = 1;
        int i;
        for (i = 0; i <= M.getLastRowIdx(M); i++){
            i *= M.getElement(i, i);
        }
    }
}
