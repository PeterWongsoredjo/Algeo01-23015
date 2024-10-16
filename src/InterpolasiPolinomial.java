import java.util.Scanner;

public class InterpolasiPolinomial {
    public void polinomial(Matrix M){
        GaussJordan GJ = new GaussJordan();

        Scanner scanner = new Scanner(System.in);

        // Inisialisasi Matriks Polinomial
        System.out.println("Input Derajat:");
        int degree = scanner.nextInt();

        M.CreateMatrix(M, degree, degree+1);

        for (int i = 0; i < degree; i++) {
            System.out.print("Enter x value for point " + (i + 1) + ": ");
            double x = scanner.nextDouble();  // Input x value

            System.out.print("Enter y value for point " + (i + 1) + ": ");
            double y = scanner.nextDouble();  // Input y value

            for (int j = 0; j < degree; j++) {
                // Ensure the indices are within bounds
                if (i < degree && j < degree) {
                    M.setElement(M, i, j, Math.pow(x, j)); // x^j term for the current row
                }
            }
            // Ensure the augmented column (y value) is within bounds
            if (i < degree) {
                M.setElement(M, i, degree, y); // Set the y value in the augmented part
            }

            System.out.println("Updated matrix after point " + (i + 1) + ":");
            M.printMatrix(M);
        }

        GJ.gaussjordan(M);
        
        Matrix hasil = new Matrix();
        hasil.CreateMatrix(hasil, M.getRow(M), 1);
        for(int i = 0; i<M.getRow(M); i++){
            hasil.setElement(hasil, i, 0, M.getElement(i,M.getLastColIdx(M)));
        }

        hasil.printMatrix(hasil);
    }
}