public class DeterminanBaris {
    public double determinan(Matrix M){
        Gauss G = new Gauss();
        Matrix temp = new Matrix();
        temp.copyMatrix(M, temp);

        G.gauss(temp);
        double det = determinan(temp);
        
        return det;
    }
}