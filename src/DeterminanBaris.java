public class DeterminanBaris {
    public double determinanbaris(Matrix M){
        Gauss G = new Gauss();
        Matrix temp = new Matrix();
        temp.copyMatrix(M, temp);

        G.gauss_det(temp);
        double det = 0;

        double base = 1;
        for(int i=0; i<temp.getRow(temp); i++){
            base *= temp.getElement(i, i);
        }

        det += base;
        
        return det;
    }

    public void printdeterminanbaris(Matrix M){
        Gauss G = new Gauss();
        Matrix temp = new Matrix();
        temp.copyMatrix(M, temp);

        G.gauss(temp);
        double det = 0;

        double base = 1;
        for(int i=0; i<temp.getRow(temp); i++){
            base *= temp.getElement(i, i);
        }

        det += base;
        
        System.out.println(det);
    }
}