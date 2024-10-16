import java.util.Scanner;

/* ********** ADT MATRIX ********** */
/* ********** TEAM RakJav ********** */
public class Matrix {
    private double[][] ELMT;
    private int rows;
    private int cols;

    /* ********** KONSTRUKTOR ********** */
    /* Konstruktor : create Matrix kosong */
    public void CreateMatrix(Matrix M, int y, int x) {
        /* I.S. Matrix M sembarang */
        /* F.S. Terbentuk Matrix M kosong dengan kapasitas CAPACITY */
        /* Proses: Inisialisasi semua elemen List l dengan MARK */
        M.rows = y;
        M.cols = x;
        M.ELMT = new double[y][x];
    }

    /* ********** SELEKTOR ********** */
    /* *** Selektor elemen *** */
    public double getElement(int baris, int kolom) {
        /* Return nilai elemen ketika User memasukkan baris dan kolom Matrix */
        return ELMT[baris][kolom];
    }

    /* *** Selektor LENGTH *** */
    public int getRow(Matrix M) {
        /* Return jumlah baris Matrix */
        return M.rows;
    }

    public int getCol(Matrix M) {
        /* Return jumlah kolom Matrix */
        return M.cols;
    }

    /* *** Selektor INDEKS *** */
    public int getFirstIdx(Matrix M) {
        /* Return index pertama Matrix */
        return 0;
    }

    public int getLastRowIdx(Matrix M) {
        /* Return index terakhir baris Matrix */
        return getRow(M) - 1;
    }

    public int getLastColIdx(Matrix M) {
        /* Return index terakhir kolom Matrix */
        return getCol(M) - 1;
    }

    /* ********** VALIDATOR ********** */
    public boolean isRowValid(Matrix M, int i) {
        /* Melakukan validasi index baris */
        return i >= 0 && i <= getLastRowIdx(M);
    }

    public boolean isColValid(Matrix M, int i) {
        /* Melakukan validasi index kolom */
        return i >= 0 && i <= getLastColIdx(M);
    }

    /* ********** SETTER ********** */
    public void setElement(Matrix M, int baris, int kolom, double value) {
        /* Set nilai elemen Matrix */
        if (isRowValid(M, baris) && (isColValid(M, baris))) {
            M.ELMT[baris][kolom] = value;
        } else {
            System.out.println("Index out of bound");
        }
    }

    /* ********** READ & PRINT ********** */

    public void readMatrix(Matrix M) {
        /* Melakukan scanning kepada setiap elemen */
        System.out.println("Input Matrix:\n");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Input Rows:");
        int rows = scanner.nextInt();
        while (rows < 0) {
            // Mengulang Prompt hingga mendapatkan input yang valid
            System.out.println("Invalid input. Please enter a non-negative integer.");
            rows = scanner.nextInt();
        }

        System.out.println("Input Coloumns:");
        int cols = scanner.nextInt();
        while (cols < 0) {
            // Mengulang Prompt hingga mendapatkan input yang valid
            System.out.println("Invalid input. Please enter a non-negative integer.");
            cols = scanner.nextInt();
        }

        CreateMatrix(M, rows, cols);

        scanner.nextLine();

        System.out.println("Input Elements:");
        for (int i = 0; i < getRow(M); i++) {
            String inputRows = scanner.nextLine();
            String[] elements = inputRows.split(" ");
            for (int j = 0; j < getCol(M); j++) {
                setElement(M, i, j, Double.parseDouble(elements[j]));
            }
        }
        scanner.close();
    }

    public void printMatrix(Matrix M) {
        /* Mengeluarkan matrix dalam bentuk rows x cols */
        for (int i = 0; i < getRow(M); i++) {
            for (int j = 0; j < getCol(M); j++) {
                System.out.print(M.getElement(i, j) + " ");
            }
            System.out.print("\n");
        }
    }

    public void copyMatrix(Matrix mIn, Matrix mOut) {
        mOut.CreateMatrix(mOut, mIn.rows, mIn.cols);
        for (int i = 0; i <= getLastRowIdx(mIn); i++) {
            for (int j = 0; j <= getLastColIdx(mIn); j++) {
                setElement(mOut, i, j, mIn.getElement(i, j));
            }
        }
    }

    /* ********** OPERATIONS ********** */
    public Matrix transposeMatrix(Matrix M) {
        /* Men-Transpose Matrix */
        Matrix temp = new Matrix();
        CreateMatrix(temp, getCol(M), getRow(M));
        for (int i = 0; i < getRow(M); i++) {
            for (int j = 0; i < getCol(M); j++) {
                setElement(temp, i, j, M.getElement(j, i));
            }
        }
        return temp;
    }

    public void multiplyMatrix(Matrix M1, Matrix M2) {
        /* Mengalikan 2 Matrix */
        if (getCol(M1) != getRow(M2)) {
            System.out.println("Matrix tidak dapat dikalikan");
            return;
        }
        Matrix temp = new Matrix();
        CreateMatrix(temp, getRow(M1), getCol(M2));
        for (int i = 0; i < getRow(M1); i++) {
            for (int j = 0; j < getCol(M2); j++) {
                double sum = 0.0;
                for (int k = 0; k < getCol(M1); k++) {
                    sum = sum + ((M1.getElement(i, k)) * (M2.getElement(k, j)));
                }
                setElement(temp, i, j, sum);
            }
        }
        M1 = temp;
    }

    public void addMatrix(Matrix M1, Matrix M2) {
        /* Menjumlahkan Dua Buah Matriks */
        if (getRow(M1) != getRow(M2) || getCol(M1) != getCol(M2)) {
            System.out.println("Matrix tidak dapat dijumlahkan");
            return;
        }
        Matrix temp = new Matrix();
        CreateMatrix(temp, getRow(M1), getCol(M1));
        for (int i = 0; i < getRow(M1); i++) {
            for (int j = 0; j < getCol(M1); j++) {
                setElement(temp, i, j, M1.getElement(i, j) + M2.getElement(i, j));
            }
        }
        M1 = temp;
    }

    public void subMatrix(Matrix M1, Matrix M2) {
        /* Mengurangkan Dua Buah Matriks */
        if (getRow(M1) != getRow(M2) || getCol(M1) != getCol(M2)) {
            System.out.println("Matrix tidak dapat dijumlahkan");
            return;
        }
        Matrix temp = new Matrix();
        CreateMatrix(temp, getRow(M1), getCol(M1));
        for (int i = 0; i < getRow(M1); i++) {
            for (int j = 0; j < getCol(M1); j++) {
                setElement(temp, i, j, M1.getElement(i, j) - M2.getElement(i, j));
            }
        }
        M1 = temp;
    }
}