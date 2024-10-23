public class SPLCramer extends Matrix {
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
  
  public void cramer(Matrix M1, Matrix M2, Boolean kosong, Boolean no_solution, StringBuilder result) {

    // Menghitung determinan awal
    DeterminanKofaktor det = new DeterminanKofaktor();
    double detM = det.detkof(M1);

    // Hitung Hasil
    Matrix mHasil = new Matrix();
    CreateMatrix(mHasil, getRow(M1), 1);
    Matrix tempcheck = new Matrix();
    int count_null = 0;

    GaussJordan GJ = new GaussJordan();
    tempcheck.CreateMatrix(tempcheck, M1.getRow(M1), M1.getCol(M1) + 1);

    for(int i = 0; i<M1.getRow(M1); i++){
      for(int j = 0; j<M1.getCol(M1); j++){
        tempcheck.setElement(tempcheck, i, j, M1.getElement(i, j));
      }
    }

    for(int i = 0; i < M1.getRow(M1); i++){
      tempcheck.setElement(tempcheck, i, tempcheck.getLastColIdx(tempcheck), M2.getElement(i, 0));
    }

    GJ.gaussjordan(tempcheck);
    for(int i = tempcheck.getLastRowIdx(tempcheck); i>=0; i--){    
      boolean temp_kosong = true;
      for(int j=0; j < tempcheck.getLastColIdx(tempcheck); j++){
        // Cek Kosong
        if (tempcheck.getElement(i, j) != 0){
          temp_kosong =false;
        }
      }
      if(temp_kosong){
        if(tempcheck.getElement(i, tempcheck.getLastColIdx(tempcheck)) == 0){
          kosong = true;
          count_null ++;
        }
        if(tempcheck.getElement(i, tempcheck.getLastColIdx(tempcheck)) != 0){
          no_solution = true;
        }
      }
    }

    if(no_solution || M1.getRow(M1) != M1.getCol(M1)){
      result.append("Tidak ada solusi yang memenuhi.");
    }

    if(!no_solution && (tempcheck.getCol(tempcheck) + count_null -1) > tempcheck.getRow(tempcheck)){
      for(int i = 0; i<=tempcheck.getLastRowIdx(tempcheck); i++){
        int first = 0; 
        if (!kosong(tempcheck, i)){ 
          for(int j = 0; j<tempcheck.getCol(tempcheck) - 1; j++){          
            if (first == 0){
              if (tempcheck.getElement(i, j) != 0){
                result.append (tempcheck.getElement(i, j) + " x" + (j+1) + " ");
                first = 1;
              }
            } 
            else{
              if(tempcheck.getElement(i, j) > 0){
                result.append("+" + tempcheck.getElement(i, j) + " x" + (j+1) + " ");
              }
              if(tempcheck.getElement(i, j) < 0){
                result.append(tempcheck.getElement(i, j) + " x" + (j+1) + " ");
              }
            }
          }
          result.append("= " + tempcheck.getElement(i, tempcheck.getCol(tempcheck) - 1));
          result.append("\n");
        }   
      }
    }
    
    if (det.detkof(M1) != 0){
      Matrix temp = new Matrix();
      for (int j = 0; j < temp.getCol(M1); j++){
        CreateMatrix(temp, getRow(M1), getCol(M1));
        copyMatrix(M1, temp);
        System.out.println("X\n");
        for (int i = 0; i < temp.getRow(temp); i++){
          temp.setElement(temp, i, j, M2.getElement(i, 0));
        }
        printMatrix(temp);
        double newDet = det.detkof(temp);
        System.out.println(newDet + " " + detM);
        double resultDet = newDet / detM;
        mHasil.setElement(mHasil, j, 0, resultDet);
      }
      for(int i = 0; i<=mHasil.getLastRowIdx(mHasil) ; i++){
        result.append("x" + (i+1) + " = " + mHasil.getElement(i, 0) + "\n");
      } 
    }
  }
}