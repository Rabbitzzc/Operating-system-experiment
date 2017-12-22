public class FIFO {
    public  static  double arr[][] = new double[6][8];
    public static int parr[][] = {
            {1,800,50},
            {2,815,30},
            {3,830,25},
            {4,835,20},
            {5,845,15},
            {6,900,10},
            {7,920,5}
    };
    public static void main(String[] args) {
        arr = FisrtSet(arr);
        arr = SecondSet(arr);
        for(int i=0; i<6; i++) {
            for(int j=0; j<8; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static double[][] FisrtSet(double[][] a) {
        for(int i=0; i<6; i++){
            for(int j=0; j<3; j++) {
                arr[i][j] = parr[i][j];
            }
        }
        return a;
    }

    public static double[][] SecondSet(double[][] a) {
        // 第一个代码的设置
        a[0][3] = 0;
        a[0][4] = a[0][1];
        a[0][5] = a[0][2] + a[0][4];
        a[0][6] = a[0][6] - a[0][0];
        a[0][7] = 1;

        // 针对于接下来的5行
        for(int i=1; i<6; i++) {
            a[i][3] = a[i-1][5] - a[i][0];
            a[i][4] = a[i][0] + a[i][3];
            a[i][5] = a[i][4] + a[i][1];
            a[i][6] = a[i][5] - a[i][0];
            a[i][7] = a[i][6] / a[i][2];

        }
        return a;
    }
}
