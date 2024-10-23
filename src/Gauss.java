import java.math.*;

public class Gauss{
    private static double EPSILON = 1e-10;

    public void gauss(Matrix M){
        Matrix temp = new Matrix();
        temp = M;

        GaussJordan GJ = new GaussJordan();

        int x = M.getLastColIdx(temp);

        if(M.getLastColIdx(temp) > M.getLastRowIdx(temp)){
            x = M.getLastRowIdx(temp);
        }
        int i;

        for (i = 0; i < x+1; i++){
            // Memastikan diagonal bukan 0
            if(Math.abs(M.getElement(i, i)) < EPSILON){
                for(int j = i; j<= M.getLastRowIdx(temp); j++){
                    if (Math.abs(M.getElement(j, i)) >= EPSILON){
                        GJ.switchRows(temp, i, j);
                        break;
                    }
                }
            }

            if (Math.abs(M.getElement(i, i)) >= EPSILON){
                // Membagi baris diagonal dengan diagonal sendiri (mendapatkan 1)
                GJ.divideByX(temp, i, M.getElement(i, i));

                // Mengurangkan baris bawah dengan multiplier diagonal
                for (int j = i+1; j <= M.getLastRowIdx(temp); j++){
                    GJ.plusMinRows(temp, j, i, GJ.multiplier(temp, i, j));
                }
            }
        }
        for (int row = 0; row <= M.getLastRowIdx(temp); row++) {
            for (int col = 0; col <= M.getLastColIdx(temp); col++) {
                if (Math.abs(M.getElement(row, col)) < EPSILON) {
                    temp.setElement(temp, row, col, 0); 
                }
            }
        }
    }

    public void gauss_det(Matrix M){
        Matrix temp = new Matrix();
        temp = M;

        GaussJordan GJ = new GaussJordan();

        int x = M.getLastColIdx(temp);

        if(M.getLastColIdx(temp) > M.getLastRowIdx(temp)){
            x = M.getLastRowIdx(temp);
        }
        int i;

        for (i = 0; i < x+1; i++){
            // Memastikan diagonal bukan 0
            if(Math.abs(M.getElement(i, i)) < EPSILON){
                for(int j = i; j<= M.getLastRowIdx(temp); j++){
                    if (Math.abs(M.getElement(j, i)) >= EPSILON){
                        GJ.switchRows(temp, i, j);
                        break;
                    }
                }
            }

            if (Math.abs(M.getElement(i, i)) >= EPSILON){
                // Mengurangkan baris bawah dengan multiplier diagonal
                for (int j = i+1; j <= M.getLastRowIdx(temp); j++){
                    GJ.plusMinRows(temp, j, i, GJ.multiplier(temp, i, j));
                }
            }
        }
        for (int row = 0; row <= M.getLastRowIdx(temp); row++) {
            for (int col = 0; col <= M.getLastColIdx(temp); col++) {
                if (Math.abs(M.getElement(row, col)) < EPSILON) {
                    temp.setElement(temp, row, col, 0); 
                }
            }
        }
    }
}