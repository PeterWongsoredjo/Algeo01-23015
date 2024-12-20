public class Determinan {
    public double determinan(Matrix M){
        Matrix temp = new Matrix();
        temp = M;

        Gauss G = new Gauss();
        G.gauss(temp);

        double result = 1;
        int i;
        for (i = 0; i <= M.getLastRowIdx(M); i++){
            i *= M.getElement(i, i);
        }
        return result;
    }

    public void printDeterminan(Matrix M){
        Matrix temp = new Matrix();
        temp = M;

        Gauss G = new Gauss();
        G.gauss(temp);

        double result = 1;
        int i;
        for (i = 0; i <= M.getLastRowIdx(M); i++){
            i *= M.getElement(i, i);
        }
        System.out.println(result);
    }
}
