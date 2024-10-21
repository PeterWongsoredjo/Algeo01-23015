import java.lang.Math;

public class BicubicSplineInterpolation {
    public double Interpolation(Matrix M, double x, double y){
        Matrix temp = new Matrix();
        Matrix MX = new Matrix();
        Matrix MA = new Matrix();
        
        MX.CreateMatrix(MX, 16, 16);
        for(int i = 0; i<16; i++){
            for (int j = 0; j<16 ; j++){
                MX.setElement(MX, i, j, 0);
            }
        }

        // F
        MX.setElement(MX, 0, 0, 1);
        for(int i = 0; i<4; i++){
            MX.setElement(MX, 1, i, 1);
        }
        for(int i = 0; i<4; i++){
            MX.setElement(MX, 2, i*4, 1);
        }
        for(int i=0; i<16; i++){
            MX.setElement(MX, 3, i, 1);
        }
        

        // Fx
        MX.setElement(MX, 4, 1, 1);
        MX.setElement(MX, 5, 1, 1);
        MX.setElement(MX, 5, 2, 2);
        MX.setElement(MX, 5, 3, 3);
        for(int i = 0; i<4; i++){
            MX.setElement(MX, 6, i*4 +1, 1);
        }
        for(int i=0; i<4; i++){
            MX.setElement(MX, 7, i*4 +1, 1);
            MX.setElement(MX, 7, i*4 +2, 2);
            MX.setElement(MX, 7, i*4 +3, 3);
        }

        // Fy
        MX.setElement(MX, 8, 4, 1);
        for(int i = 4; i<8; i++){
            MX.setElement(MX, 9, i, 1);
        }
        MX.setElement(MX, 10, 4, 1);
        MX.setElement(MX, 10, 8, 2);
        MX.setElement(MX, 10, 12, 3);
        for(int i=0; i<4; i++){
            MX.setElement(MX, 11, i+4, 1);
            MX.setElement(MX, 11, i+8, 2);
            MX.setElement(MX, 11, i+12, 3);
        }

        // Fxy
        MX.setElement(MX, 12, 5, 1);
        MX.setElement(MX, 13, 5, 1);
        MX.setElement(MX, 13, 6, 2);
        MX.setElement(MX, 13, 7, 3);
        MX.setElement(MX, 14, 5, 1);
        MX.setElement(MX, 14, 9, 2);
        MX.setElement(MX, 14, 13, 3);
        MX.setElement(MX, 15, 5, 1);
        MX.setElement(MX, 15, 6, 2);
        MX.setElement(MX, 15, 7, 3);
        MX.setElement(MX, 15, 9, 2);
        MX.setElement(MX, 15, 10, 4);
        MX.setElement(MX, 15, 11, 6);
        MX.setElement(MX, 15, 13, 3);
        MX.setElement(MX, 15, 14, 6);
        MX.setElement(MX, 15, 15, 9);

        //MX.printMatrix(MX);
        // Inversing the Matrix X
        InversGauss IG = new InversGauss();
        Matrix MXIG = new Matrix();
        MXIG = IG.inversgauss(MX);
        //MXIG.printMatrix(MXIG);

        // Making M into a one column Matrix
        temp.CreateMatrix(temp, 16, 1);
        int idx_temp = 0; 
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                temp.setElement(temp, idx_temp, 0, M.getElement(i, j));
                idx_temp ++;
            }
        }
        //temp.printMatrix(temp);

        // Multiplying Matrix 
        MA.CreateMatrix(MA, 16, 1);
        MA = MXIG.multiplyMatrix(MXIG, temp);
        //MA.printMatrix(MA);

        // Moving Matrix
        Matrix MAY = new Matrix();
        MAY.CreateMatrix(MAY, 4, 4);
        int idx = 0;
        for(int i = 0; i<4; i++){
            for(int j = 0; j<4;j++){
                MAY.setElement(MAY, j, i, MA.getElement(idx, 0));
                idx ++;
            }
        }
        //MAY.printMatrix(MAY);

        double result = 0;
        for(int i = 0; i<4; i++){
            for(int j = 0; j<4; j++){
                result = result + ( MAY.getElement(i,j) * Math.pow(x, i) * Math.pow(y, j));
            }
        }

        return result;
    }
}