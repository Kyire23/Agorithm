package DataStructures;
//稀疏数组的实现
public class SparseArray {
    //创建一个原始的数组11*11 0表示没有棋子 1 黑 2 蓝
    public static void main(String[] args) {
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2]=1;
        chessArr1[2][3]=2;
        //输出原始二维数组
        System.out.println("原始的二维数组");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d",data);
            }
            System.out.println();
        }
            //先将二维数组 转稀疏数组
            //1.先遍历二维数组 得到非零数据个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] !=0){
                    sum ++;
                }
            }
        }
        //2.创建对应稀疏数组
        int sparseArr[][] = new int[sum+1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //遍历二维数组 将非零的值
        int count = 0; //记录第几个非零数据
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] !=0){
                    count++;
                sparseArr[count][0] = i;
                sparseArr[count][1] = j;
                sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
       // 输出稀疏数组形式
        System.out.println();
        System.out.println("得到的稀疏数组为");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }
        System.out.println();
        //恢复成原始的二维数组
        //1.先读取稀疏数组的第一行，根据第一行数据。创建原始的二维数组
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]]=sparseArr[1][2];
        }
        System.out.println();
        System.out.println("恢复后");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d",data);
            }
            System.out.println();
        }

    }



}
