public class RegresiKuadratikBerganda {
    public void regresikuadratik(Matrix MX, Matrix MY, StringBuilder result){

        Matrix ExtendedX = new Matrix();
        ExtendedX.CreateMatrix(ExtendedX, MX.getRow(MX), 6);

        // Bikin Matrix ExtendedX
        for(int i = 0; i < ExtendedX.getRow(ExtendedX); i++){
            double x1 = MX.getElement(i, 0);
            double x2 = MX.getElement(i, 1);

            ExtendedX.setElement(ExtendedX, i, 0, 1);
            ExtendedX.setElement(ExtendedX, i, 1, x1);
            ExtendedX.setElement(ExtendedX, i, 2, x2);
            ExtendedX.setElement(ExtendedX, i, 3, x1 * x1);
            ExtendedX.setElement(ExtendedX, i, 4, x2 * x2);
            ExtendedX.setElement(ExtendedX, i, 5, x1 * x2);
        }
        //ExtendedX.printMatrix(ExtendedX);

        // Bikin Matrix Transpose dari Extend
        Matrix TransposeX = new Matrix();
        TransposeX = ExtendedX.transposeMatrix(ExtendedX);
        //TransposeX.printMatrix(TransposeX);

        // Kaliin Transpose sama MX
        Matrix XTX = new Matrix();
        XTX = XTX.multiplyMatrix(TransposeX, ExtendedX);
        //XTX.printMatrix(XTX);
        
        // Kaliin Transpose sama MY
        Matrix XTY = new Matrix();
        XTY = XTY.multiplyMatrix(TransposeX, MY);
        //XTY.printMatrix(XTY);

        // SPL
        SPL spl = new SPL();
        spl.gauss(XTX, XTY, false, false, result);
    }

    public void regresikuadratiktaksir(Matrix MX, Matrix MY, double x, StringBuilder result){

        Matrix ExtendedX = new Matrix();
        ExtendedX.CreateMatrix(ExtendedX, MX.getRow(MX), 6);

        // Bikin Matrix ExtendedX
        for(int i = 0; i < ExtendedX.getRow(ExtendedX); i++){
            double x1 = MX.getElement(i, 0);
            double x2 = MX.getElement(i, 1);

            ExtendedX.setElement(ExtendedX, i, 0, 1);
            ExtendedX.setElement(ExtendedX, i, 1, x1);
            ExtendedX.setElement(ExtendedX, i, 2, x2);
            ExtendedX.setElement(ExtendedX, i, 3, x1 * x1);
            ExtendedX.setElement(ExtendedX, i, 4, x2 * x2);
            ExtendedX.setElement(ExtendedX, i, 5, x1 * x2);
        }
        //ExtendedX.printMatrix(ExtendedX);

        // Bikin Matrix Transpose dari Extend
        Matrix TransposeX = new Matrix();
        TransposeX = ExtendedX.transposeMatrix(ExtendedX);
        //TransposeX.printMatrix(TransposeX);

        // Kaliin Transpose sama MX
        Matrix XTX = new Matrix();
        XTX = XTX.multiplyMatrix(TransposeX, ExtendedX);
        //XTX.printMatrix(XTX);
        
        // Kaliin Transpose sama MY
        Matrix XTY = new Matrix();
        XTY = XTY.multiplyMatrix(TransposeX, MY);
        //XTY.printMatrix(XTY);

        Matrix temp = new Matrix();
        Matrix hasil = new Matrix();
        Gauss gauss = new Gauss();

        temp.CreateMatrix(temp, XTX.getRow(XTX), XTX.getCol(XTX) + 1);

        for(int i = 0; i<XTX.getRow(XTX); i++){
            for(int j = 0; j<XTX.getCol(XTX); j++){
                temp.setElement(temp, i, j, XTX.getElement(i, j));
            }
        }

        for(int i = 0; i < XTX.getRow(XTX); i++){
            temp.setElement(temp, i, temp.getLastColIdx(temp), XTY.getElement(i,0));
        }

        hasil.CreateMatrix(hasil, XTX.getRow(XTX), 1);

        gauss.gauss(temp);
        temp.printMatrix(temp);

        boolean kosong = false;
        boolean no_solution = false;
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
                if (!kosong){
                    for(int j = 0; j<temp.getLastColIdx(temp); j++){
                        if(temp.getElement(i, j) != 0){
                            if(temp.getElement(i, j) >0){
                                result.append("+ x" + (j+1) + " ");
                            }
                            else{
                                result.append("- x" + (j+1) + " ");
                            }
                        }
                    }
                    result.append("= " + temp.getElement(i, temp.getLastColIdx(temp)));
                    result.append("\n");
                }   
            }
        }

        /* 

        for (int i = XTX.getRow(XTX) - 1; i >= 0; i--) {
            for (int k = i - 1; k >= 0; k--) {
                double factor = temp.getElement(k, i);
                for (int j = 0; j < XTX.getCol(XTX); j++) {
                    temp.setElement(temp, k, j, temp.getElement(k, j) - factor * temp.getElement(i, j));
                }
            }
        }
        */
        

        for(int i = 0; i<=temp.getLastRowIdx(temp); i++){
            hasil.setElement(hasil, i, hasil.getLastColIdx(hasil), temp.getElement(i, temp.getLastColIdx(temp)));
        }

        if(!kosong && !no_solution){
            for(int i = 0; i<=hasil.getLastRowIdx(hasil) ; i++){
                result.append("x" + (i+1) + " = " + hasil.getElement(i, 0));
            }
        }
    }
}