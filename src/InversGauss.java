public class InversGauss {
    public Matrix inversgauss(Matrix M) {
    /* WARNING : BELUM PUNYA VALIDATOR */
        DeterminanBaris DB = new DeterminanBaris();
        if(DB.determinanbaris(M) != 0){

            Matrix temp = new Matrix();
            GaussJordan GJ = new GaussJordan();

            int i, j;
            temp.CreateMatrix(temp, M.getRow(M), 2 * M.getCol(M));
            for (i = 0; i < M.getRow(M); i++){
                for (j = 0; j < M.getCol(M); j++){
                    temp.setElement(temp,i,j,M.getElement(i, j));
                }
            }

            for (i = 0; i < temp.getRow(temp); i++){
                for (j = M.getCol(M); j < temp.getCol(temp); j++){
                    if (i + M.getCol(M) == j){
                        temp.setElement(temp,i,j, 1);
                    } else {
                        temp.setElement(temp,i,j, 0);
                    }
                }
            }
                
            GJ.gaussjordan(temp);
            Matrix result = new Matrix();
            result.CreateMatrix(result, M.getRow(M), M.getCol(M));
            for (i = 0; i < M.getRow(M); i++){
                for (j = 0; j < M.getCol(M); j++){
                    temp.setElement(result,i,j,temp.getElement(i, j + M.getCol(M)));
                }
            }
            return result;
        } 
        else{
            System.out.println("Matriks adalah matriks singular dan tidak memiliki balikan.");
        }
        return M;
    }

    public void printinversgauss(Matrix M){
        /* WARNING : BELUM PUNYA VALIDATOR */
        DeterminanBaris DB = new DeterminanBaris();
        if(DB.determinanbaris(M) != 0){

            Matrix temp = new Matrix();
            GaussJordan GJ = new GaussJordan();

            int i, j;
            temp.CreateMatrix(temp, M.getRow(M), 2 * M.getCol(M));
            for (i = 0; i < M.getRow(M); i++){
                for (j = 0; j < M.getCol(M); j++){
                    temp.setElement(temp,i,j,M.getElement(i, j));
                }
            }

            for (i = 0; i < temp.getRow(temp); i++){
                for (j = M.getCol(M); j < temp.getCol(temp); j++){
                    if (i + M.getCol(M) == j){
                        temp.setElement(temp,i,j, 1);
                    } else {
                        temp.setElement(temp,i,j, 0);
                    }
                }
            }
                
            GJ.gaussjordan(temp);
            Matrix result = new Matrix();
            result.CreateMatrix(result, M.getRow(M), M.getCol(M));
            for (i = 0; i < M.getRow(M); i++){
                for (j = 0; j < M.getCol(M); j++){
                    result.setElement(result,i,j,temp.getElement(i, j + M.getCol(M)));
                }
            }
            result.printMatrix(result);
        } 
        else{
            System.out.println("Matriks adalah matriks singular dan tidak memiliki balikan.");
        }
    }
}