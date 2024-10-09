/* ********** ADT MATRIX ********** */
/* ********** TEAM RakJav ********** */
public class Matrix {
    private ElType[][] ELMT;
    private int baris;
    private int kolom;

    /* ********** KONSTRUKTOR ********** */
    /* Konstruktor : create Matrix kosong  */
    public void CreateMatrix(Matrix M, int y, int x){
        /* I.S. Matrix M sembarang */
        /* F.S. Terbentuk Matrix M kosong dengan kapasitas CAPACITY */
        /* Proses: Inisialisasi semua elemen List l dengan MARK */
        M.baris = y;
        M.kolom = x;
        M.ELMT = new ElType[baris][kolom];
    }

    /* ********** SELEKTOR ********** */
    /* *** Selektor elemen *** */
    public ElType getElement(int baris, int kolom){
        /* Return nilai elemen ketika User memasukkan baris dan kolom Matrix */
        return ELMT[baris][kolom];
    }

    /* *** Selektor LENGTH *** */
    public int matrixLength(Matrix M, boolean vertikal){
        /* Return panjang Matrix */
        if (vertikal){
            return M.baris;
        } else {
            return M.kolom;
        }
    }

    /* *** Selektor INDEKS *** */
    public int getFirstIdx(Matrix M, boolean vertikal){
        /* Return index pertama Matrix */
        return 0;
    }

    public int getLastIdx(Matrix M, boolean vertikal){
        /* Return index terakhir Matrix */
        if (vertikal){
            return (matrixLength(M, true) - 1);
        } else {
            return (matrixLength(M, false) - 1);
        }
    }

    /* ********** VALIDATOR ********** */
    public boolean isIdxValid(Matrix M, int i, boolean vertikal){
        if (vertikal){
            return i >= 0 && i <= getLastIdx(M, true);
        } else {
            return i >= 0 && i <= getLastIdx(M, false);
        }
    }
}