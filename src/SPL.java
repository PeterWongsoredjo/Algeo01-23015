public class SPL {
    private boolean kosong(Matrix M, int i){ 
        boolean temp_kosong = true;
        for(int j=0; j < M.getLastColIdx(M); j++){
            // Cek Kosong
            if (M.getElement(i, j) != 0){
                temp_kosong =false;
            }
        }
        return temp_kosong;
    }

    public void gauss(Matrix M1, Matrix M2, Boolean kosong, Boolean no_solution, StringBuilder result){
        Matrix temp = new Matrix();
        Matrix hasil = new Matrix();
        Gauss gauss = new Gauss();

        temp.CreateMatrix(temp, M1.getRow(M1), M1.getCol(M1) + 1);

        for(int i = 0; i<M1.getRow(M1); i++){
            for(int j = 0; j<M1.getCol(M1); j++){
                temp.setElement(temp, i, j, M1.getElement(i, j));
            }
        }

        for(int i = 0; i < M1.getRow(M1); i++){
            temp.setElement(temp, i, temp.getLastColIdx(temp), M2.getElement(i,0));
        }

        hasil.CreateMatrix(hasil, M1.getRow(M1), 1);

        gauss.gauss(temp);
        temp.printMatrix(temp);

        kosong = false;
        no_solution = false;
        int count_null = 0;

        GaussJordan GJ = new GaussJordan();
        GJ.gaussjordan(temp);
        temp.printMatrix(temp);


        for(int i = temp.getLastRowIdx(temp); i>=0; i--){    
            boolean temp_kosong = true;
            for(int j=0; j < temp.getLastColIdx(temp); j++){
                // Cek Kosong
                if (temp.getElement(i, j) != 0){
                    temp_kosong =false;
                }
            }
            if(temp_kosong){
                if(temp.getElement(i, temp.getLastColIdx(temp)) == 0){
                    kosong = true;
                    count_null ++;
                }
                if(temp.getElement(i, temp.getLastColIdx(temp)) != 0){
                    no_solution = true;
                }
            }
        }

        if(no_solution){
            result.append("Tidak ada solusi yang memenuhi.");
        }

        if(!no_solution && (temp.getCol(temp) + count_null -1) > temp.getRow(temp)){
            for(int i = 0; i<=temp.getLastRowIdx(temp); i++){
                int first = 0; 
                if (!kosong(temp, i)){ 
                    for(int j = 0; j<temp.getCol(temp) - 1; j++){          
                        if (first == 0){
                            if (temp.getElement(i, j) != 0){
                                result.append (temp.getElement(i, j) + " x" + (j+1) + " ");
                                first = 1;
                            }
                        } 
                        else{
                            if(temp.getElement(i, j) > 0){
                                result.append("+" + temp.getElement(i, j) + " x" + (j+1) + " ");
                            }
                            if(temp.getElement(i, j) < 0){
                                result.append(temp.getElement(i, j) + " x" + (j+1) + " ");
                            }
                        }
                    }
                    result.append("= " + temp.getElement(i, temp.getCol(temp) - 1));
                    result.append("\n");
                }   
            }
        }

        for(int i = 0; i<=temp.getLastRowIdx(temp); i++){
            hasil.setElement(hasil, i, hasil.getLastColIdx(hasil), temp.getElement(i, temp.getLastColIdx(temp)));
        }

        if(!((temp.getCol(temp) + count_null -1) > temp.getRow(temp)) && !no_solution){   
            for(int i = 0; i<=hasil.getLastRowIdx(hasil) ; i++){
                if(!kosong(temp,i)){
                    result.append("x" + (i+1) + " = " + hasil.getElement(i, 0) + "\n");
                }
            }
        }
    }

    public void gaussjordan(Matrix M1, Matrix M2, Boolean kosong, Boolean no_solution, StringBuilder result){
        Matrix temp = new Matrix();
        Matrix hasil = new Matrix();
        int count_null = 0;

        GaussJordan GJ = new GaussJordan();

        temp.CreateMatrix(temp, M1.getRow(M1), M1.getCol(M1) + 1);

        for(int i = 0; i<M1.getRow(M1); i++){
            for(int j = 0; j<M1.getCol(M1); j++){
                temp.setElement(temp, i, j, M1.getElement(i, j));
            }
        }

        for(int i = 0; i < M1.getRow(M1); i++){
            temp.setElement(temp, i, temp.getLastColIdx(temp), M2.getElement(i, 0));
        }

        hasil.CreateMatrix(hasil, M1.getRow(M1), 1);

        GJ.gaussjordan(temp);
        for(int i = temp.getLastRowIdx(temp); i>=0; i--){    
            boolean temp_kosong = true;
            for(int j=0; j < temp.getLastColIdx(temp); j++){
                // Cek Kosong
                if (temp.getElement(i, j) != 0){
                    temp_kosong =false;
                }
            }
            if(temp_kosong){
                if(temp.getElement(i, temp.getLastColIdx(temp)) == 0){
                    kosong = true;
                    count_null ++;
                }
                if(temp.getElement(i, temp.getLastColIdx(temp)) != 0){
                    no_solution = true;
                }
            }
        }

        if(no_solution){
            result.append("Tidak ada solusi yang memenuhi.");
        }

        if(!no_solution && (temp.getCol(temp) + count_null -1) > temp.getRow(temp)){
            for(int i = 0; i<=temp.getLastRowIdx(temp); i++){
                int first = 0; 
                if (!kosong(temp, i)){ 
                    for(int j = 0; j<temp.getCol(temp) - 1; j++){          
                        if (first == 0){
                            if (temp.getElement(i, j) != 0){
                                result.append (temp.getElement(i, j) + " x" + (j+1) + " ");
                                first = 1;
                            }
                        } 
                        else{
                            if(temp.getElement(i, j) > 0){
                                result.append("+" + temp.getElement(i, j) + " x" + (j+1) + " ");
                            }
                            if(temp.getElement(i, j) < 0){
                                result.append(temp.getElement(i, j) + " x" + (j+1) + " ");
                            }
                        }
                    }
                    result.append("= " + temp.getElement(i, temp.getCol(temp) - 1));
                    result.append("\n");
                }   
            }
        }

        for(int i = 0; i<=temp.getLastRowIdx(temp); i++){
            hasil.setElement(hasil, i, hasil.getLastColIdx(hasil), temp.getElement(i, temp.getLastColIdx(temp)));
        }

        if(!((temp.getCol(temp) + count_null -1) > temp.getRow(temp)) && !no_solution){   
            for(int i = 0; i<=hasil.getLastRowIdx(hasil) ; i++){
                if(!kosong(temp,i)){
                    result.append("x" + (i+1) + " = " + hasil.getElement(i, 0) + "\n");
                }
            }
        }
    }
}
