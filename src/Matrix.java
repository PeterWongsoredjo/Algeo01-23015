/* ********** ADT MATRIX ********** */
/* ********** TEAM RakJav ********** */
public class Matrix {
    private double [][] ELMT;
    private int rows;
    private int cols;

    /* ********** KONSTRUKTOR ********** */
    /* Konstruktor : create Matrix kosong  */
    public void CreateMatrix(Matrix M, int y, int x){
        /* I.S. Matrix M sembarang */
        /* F.S. Terbentuk Matrix M kosong dengan kapasitas CAPACITY */
        /* Proses: Inisialisasi semua elemen List l dengan MARK */
        M.rows = y;
        M.cols = x;
        M.ELMT = new double [rows][cols];
    }

    /* ********** SELEKTOR ********** */
    /* *** Selektor elemen *** */
    public double getElement(int baris, int kolom){
        /* Return nilai elemen ketika User memasukkan baris dan kolom Matrix */
        return ELMT[baris][kolom];
    }

    /* *** Selektor LENGTH *** */

    /* *** Selektor LENGTH *** */
    public int getRow(Matrix M){
        /* Return jumlah baris Matrix */
        return M.rows;
    }

    public int getCol(Matrix M){
        /* Return jumlah kolom Matrix */
        return M.cols;
    }

    /* *** Selektor INDEKS *** */
    public int getFirstIdx(Matrix M){
        /* Return index pertama Matrix */
        return 0;
    }

    public int getLastRowIdx(Matrix M){
        /* Return index terakhir baris Matrix */
        return getRow(M) - 1;
    }

    public int getLastColIdx(Matrix M){
        /* Return index terakhir kolom Matrix */
        return getCol(M) - 1;
    }

    /* ********** VALIDATOR ********** */
    public boolean isRowValid(Matrix M, int i){
        /* Melakukan validasi index baris */
        return i >= 0 && i <= getLastRowIdx(M);
    }

    public boolean isColValid(Matrix M, int i){
        /* Melakukan validasi index kolom */
        return i >= 0 && i <= getLastColIdx(M);
    }

    /* ********** SETTER ********** */
    public void setElement(Matrix M, int baris, int kolom, double value){
        /* Set nilai elemen Matrix */
        if (isRowValid(M, baris) && (isColValid(M, baris))){
            M.ELMT[baris][kolom] = value;
        }
        else{
            System.out.println("Index out of bound");
        }
    }

    /* ********** OPERATIONS ********** */
    public void transposeMatrix(Matrix M){
        /* Men-Transpose Matrix */
        Matrix temp = new Matrix();
        CreateMatrix(temp, getCol(M), getRow(M));
        for (int i = 0; i < getRow(M); i++){
            for (int j = 0; i < getCol(M); j++){
                setElement(temp, i, j, M.getElement(j, i));
            }
        }
        M = temp;
    }

    public void multiplyMatrix(Matrix M1, Matrix M2){
        /* Mengalikan 2 Matrix */
        if (getCol(M1) != getRow(M2)){
            System.out.println("Matrix tidak dapat dikalikan");
            return;
        }
        Matrix temp = new Matrix();
        CreateMatrix(temp, getRow(M1), getCol(M2));
        for (int i = 0; i < getRow(M1); i++){
            for (int j = 0; j < getCol(M2); j++){
                double sum = 0.0;
                for (int k = 0; k < getCol(M1); k++){
                    sum = sum + ((M1.getElement(i, k)) * (M2.getElement(k, j)));
                }
                setElement(temp, i, j, sum);
            }
        }
        M1 = temp;
    }

    public void addMatrix(Matrix M1, Matrix M2){
        /* Menjumlahkan Dua Buah Matriks */
        if (getRow(M1) != getRow(M2) || getCol(M1)!=getCol(M2)){
            System.out.println("Matrix tidak dapat dijumlahkan");
            return;
        }
        Matrix temp = new Matrix();
        CreateMatrix(temp, getRow(M1), getCol(M1));
        for (int i = 0; i < getRow(M1); i++){
            for (int j = 0; j < getCol(M1); j++){
                setElement(temp, i, j, M1.getElement(i, j) + M2.getElement(i, j));
            }
        }
        M1 = temp;
    }

    public void subMatrix(Matrix M1, Matrix M2){
        /* Mengurangkan Dua Buah Matriks */
        if (getRow(M1) != getRow(M2) || getCol(M1)!=getCol(M2)){
            System.out.println("Matrix tidak dapat dijumlahkan");
            return;
        }
        Matrix temp = new Matrix();
        CreateMatrix(temp, getRow(M1), getCol(M1));
        for (int i = 0; i < getRow(M1); i++){
            for (int j = 0; j < getCol(M1); j++){
                setElement(temp, i, j, M1.getElement(i, j) - M2.getElement(i, j));
            }
        }
        M1 = temp;
    }
}