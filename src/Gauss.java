public class Gauss{
    public void gauss(Matrix M){
        Matrix temp = new Matrix();
        temp = M;

        GaussJordan GJ = new GaussJordan();

        int i;
        for (i = 0; i < M.getLastColIdx(temp); i++){
            // Memastikan diagonal bukan 0
            if(M.getElement(i, i) == 0){
                for(int j = i; j<= M.getLastRowIdx(temp); j++){
                    if (M.getElement(j, i) != 0){
                        GJ.switchRows(temp, i, j);
                        break;
                    }
                }
            }

            if (M.getElement(i, i) != 0){
                // Membagi baris diagonal dengan diagonal sendiri (mendapatkan 1)
                GJ.divideByX(temp, i, M.getElement(i, i));

                // Mengurangkan baris bawah dengan multiplier diagonal
                for (int j = i+1; j <= M.getLastRowIdx(temp); j++){
                    GJ.plusMinRows(temp, j, i, GJ.multiplier(temp, i, j));
                }
            }
        }
    }
}