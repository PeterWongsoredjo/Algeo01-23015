public class InversGauss {
    public void inversgauss(Matrix M) {
        DeterminanBaris D = new DeterminanBaris(); 
        if (D.determinan(M) != 0){
            Matrix temp = new Matrix();
            GaussJordan GJ = new GaussJordan();

            int i, j;
            temp.CreateMatrix(temp, M.getRow(M), 2 * M.getCol(M));
            for (i = 0; i < M.getRow(M); i++){
                for (j = 0; j < M.getCol(M); j++){
                    double x = M.getElement(i, j);
                    System.out.println(x);
                    temp.setElement(temp,i,j,M.getElement(i, j));
                }
            }

            for (i = 0; i < temp.getRow(temp); i++){
                for (j = M.getCol(M); j < temp.getCol(temp); j++){
                    if (i + M.getCol(M)  == j){
                        temp.setElement(temp,i,j, 1);
                    } else {
                        temp.setElement(temp,i,j, 0);
                    }
                }
            }
            
            GJ.gaussjordan(temp);
            for (i = 0; i < M.getRow(M); i++){
                for (j = 0; j < M.getCol(M); j++){
                    temp.setElement(M,i,j,temp.getElement(i, j + M.getCol(M)));
                }
            }
        } 
        else {
            System.out.println("Matrix tidak memiliki Invers.");
        }
    }
}