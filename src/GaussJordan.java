public class GaussJordan {
    public static double EPSILON = 1e-10;

    public void divideByX(Matrix M, int row, double x){     // Membagi baris dengan x
        for(int i=0; i <= M.getLastColIdx(M); i++){
            double temp = M.getElement(row,i);
            M.setElement(M, row, i, temp / x);
        }
    }

    public void switchRows(Matrix M, int row1, int row2){    // Menukar dua baris
        double temp;
        for(int i=0; i <= M.getLastColIdx(M); i++){
            temp = M.getElement(row1, i);
            M.setElement(M, row1, i, M.getElement(row2, i));
            M.setElement(M, row2, i, temp);	
        }        
    }

    public void plusMinRows(Matrix M, int row1, int row2, double multiplier){     // Menambah ataupun mengurangkan baris dengan baris lain
        for(int i=0; i<= M.getLastColIdx(M); i++){
            double temp = M.getElement(row1, i) - (M.getElement(row2, i) * multiplier);
            M.setElement(M, row1, i, temp);
        }        
    }

    public double multiplier(Matrix M,int d, int rowx){             // Rumus multiplier
        double temp;
        temp = M.getElement(rowx, d) / M.getElement(d, d);
        return temp;
    }

    public void gaussjordan(Matrix M){
        Matrix temp = new Matrix();
        temp = M;

        int x = M.getLastColIdx(temp);

        if(M.getLastColIdx(temp) > M.getLastRowIdx(temp)){
            x = M.getLastRowIdx(temp);
        }

        for (int i = 0; i < x+1; i++){
            // Memastikan diagonal bukan 0
            if(Math.abs(M.getElement(i, i)) < EPSILON){
                for(int j = i; j<= M.getLastRowIdx(temp); j++){
                    if (Math.abs(M.getElement(j, i)) >= EPSILON){
                        switchRows(temp, i, j);
                        break;
                    }
                }
            }

            if(Math.abs(M.getElement(i, i)) >= EPSILON){
                // Membagi baris diagonal dengan diagonal sendiri (mendapatkan 1)
                divideByX(temp, i, M.getElement(i, i));

                // Mengurangkan baris bawah dengan multiplier diagonal
                for (int j = i+1; j <= M.getLastRowIdx(temp); j++){
                    plusMinRows(temp, j, i, multiplier(temp, i, j));
                }

                // Mengurangkan baris atas dengan multiplier diagonal
                for (int j = 0; j < i; j++){
                    plusMinRows(temp, j, i, multiplier(temp, i, j));
                }
            }
        }

        for (int row = 0; row <= M.getLastRowIdx(temp); row++) {
            for (int col = 0; col <= M.getLastColIdx(temp); col++) {
                if (Math.abs(M.getElement(row, col)) < EPSILON) {
                    temp.setElement(temp, row, col, 0);  // Set to 0 to avoid -0.0
                }
            }
        }
    }
}
