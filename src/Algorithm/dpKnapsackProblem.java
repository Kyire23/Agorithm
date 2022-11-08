package Algorithm;

public class dpKnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1, 4, 3};//物品的重量
        int[] val = {1500, 3000, 2000};
        int m = 4;//背包的容量
        int n = val.length;//物品的个数

        //记录放入商品的情况 定义一个二维数组
        int[][] path = new int[n + 1][m + 1];

        //创建二维数组
        //v[i][j] 表示在前i个物品中能装入容量为j的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];

        //初始第一行和第一列 在程序中可以不用去处理 默认为0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;//将第一行设置为0
        }

        //根据公式进行DP

        for (int i = 1; i < v.length; i++) {
            //不处理第一行 第一行全0
            for (int j = 1; j < v[0].length; j++) {
                //不处理第一列
                //公式
                if (w[i - 1] > j) {//i是从1 开始
                    v[i][j] = v[i - 1][j];
                } else {//i是从1 开始
                    //v[i][j]=Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);
                    //为了记录商品存放到背包的情况 不能直接使用上面公式
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                         //把当前情况记录到path
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }
        //输出最后放入的是哪些商品
        //遍历path
        System.out.println("============");
//        for (int i = 0; i < path.length; i++) {
//            for (int j = 0; j < path[i].length; j++) {
//                if (path[i][j] == 1){
//                System.out.printf("第%d个商品放入到背包\n",i);
//                }
//            }
//        }
        //只需要最后的放入
        int i = path.length -1;//行的最大下标
        int j = path[0].length - 1;//列的最大下标
        while (i > 0 && j > 0) {
            //从path的最后开始查找
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包\n", i);
                j -= w[i - 1];
            }
            i--;
        }
    }
}
