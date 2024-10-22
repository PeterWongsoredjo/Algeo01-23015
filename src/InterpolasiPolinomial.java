import java.util.Scanner;
import java.math.*;

public class InterpolasiPolinomial {
    public void polinomial(){
        GaussJordan GJ = new GaussJordan();

        Scanner scanner = new Scanner(System.in);

        // Inisialisasi Matriks Polinomial
        System.out.println("Input Derajat:");
        int degree = scanner.nextInt();

        Matrix M = new Matrix();
        M.CreateMatrix(M, degree, degree+1);

        for (int i = 0; i < degree; i++) {
            double x = scanner.nextDouble();  // Input x 
            double y = scanner.nextDouble();  // Input y 

            for (int j = 0; j < degree; j++) {
                if (i < degree && j < degree) {
                    M.setElement(M, i, j, Math.pow(x, j)); 
                }
            }
            
            if (i < degree) {
                M.setElement(M, i, degree, y); 
            }
        }

        GJ.gaussjordan(M);
        
        Matrix hasil = new Matrix();
        hasil.CreateMatrix(hasil, M.getRow(M), 1);
        for(int i = 0; i<M.getRow(M); i++){
            hasil.setElement(hasil, i, 0, M.getElement(i,M.getLastColIdx(M)));
        }

        Double Xin = scanner.nextDouble();
        double result = 0;
        for(int i = 0; i<M.getRow(M);i++){
            result += (hasil.getElement(i,0) * Math.pow(Xin, i));
        }

        System.out.println(result);
    }
}