public class GaussJordan {
    public void function1(Matrix M, int row, double x){     // Membagi baris dengan x
        for(int i=0; i <= M.getLastColIdx(M); i++){
            double temp = M.getElement(row,i);
            M.setElement(M, row, i, temp / x);
        }
    }

    public void function2(Matrix M, int row1, int row2){    // Menukar dua baris
        double temp;
        for(int i=0; i <= M.getLastColIdx(M); i++){
            temp = M.getElement(row1, i);
            M.setElement(M, row1, i, M.getElement(row2, i));
            M.setElement(M, row2, i, temp);	
        }        
    }

    public void function3(Matrix M, int row1, int row2, double multiplier){     // Menambah ataupun mengurangkan baris dengan baris lain
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

        for (int i = 0; i < M.getLastColIdx(temp); i++){
            // Mengecek apakah diagonal bukan 0
            if(M.getElement(i, i) == 0){
                for(int j = i; j<= M.getLastRowIdx(temp); j++){
                    if (M.getElement(j, i) != 0){
                        function2(temp, i, j);
                        break;
                    }
                }
            }

            if(M.getElement(i, i) != 0){
                // Membagi baris diagonal dengan diagonal sendiri (mendapatkan 1)
                function1(temp, i, M.getElement(i, i));

                // Mengurangkan baris bawah dengan multiplier diagonal
                for (int j = i+1; j <= M.getLastRowIdx(temp); j++){
                    function3(temp, j, i, multiplier(temp, i, j));
                }

                // Mengurangkan baris atas dengan multiplier diagonal
                for (int j = 0; j < i; j++){
                    function3(temp, j, i, multiplier(temp, i, j));
                }
            }
        }
    }    
}
