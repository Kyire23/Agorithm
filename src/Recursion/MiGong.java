package Recursion;

/**
 * @author
 * @create 2022-05-27-9:29
 */
public class MiGong {
    public static void main(String[] args) {
        //创建一个二维数组，模拟迷宫
        int[][] map = new int[8][7];

        //1表示墙
        //上下全部置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右全部置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
//        map[1][2] = 1;
//        map[2][2] = 1;

        System.out.println("最初地图为：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();

        }
        //测试小球通过递归回溯寻找通路
//        setWay(map, 1, 1);
        setWay2(map, 1, 1);
        //输出小球走过以后的地图
        System.out.println("小球寻路以后的地图为：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();

        }


    }

    //使用递归回溯来给小球找路
    //说明：
    //1.map表示地图
    //2. i,j表示从地图哪个位置出发，(1,1)
    //3.如果小球能够到达map(6,5)，说明通路找到
    //4.约定：当地图的map[i][j]=0时表示该点还没走过，如果是1表示墙，如果是2，表示通路，可以走。3表示该位置已经走过，但走不通。
    //5.在走迷宫时，需要确定一个策略（方法）：下->右->上->左，如果该点走不通，则回溯

    //下->右->上->左

    /**
     * @param map 表示地图
     * @param i   从哪个位置开始找
     * @param j
     * @return 如果找到路返回true，否则返回false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {//说明这个点还没走过
                map[i][j] = 2;//先走一步
                //再检测这一点是否能走
                if (setWay(map, i + 1, j)) {//下走
                    return true;
                } else if (setWay(map, i, j + 1)) {//右走
                    return true;
                } else if (setWay(map, i - 1, j)) {//上走
                    return true;
                } else if (setWay(map, i, j - 1)) {//左走
                    return true;
                } else {
                    map[i][j] = 3;//标记一下是死路
                    return false;
                }

            } else {
                return false;// 1 2 3 说明要么是不能走，要么是已经走过了
            }
        }

    }

    //修改策略：上->右->下->左
    public static boolean setWay2(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {//说明这个点还没走过
                map[i][j] = 2;//先走一步
                //再检测这一点是否能走
                if (setWay2(map, i - 1, j)) {//下走
                    return true;
                } else if (setWay2(map, i, j + 1)) {//右走
                    return true;
                } else if (setWay2(map, i + 1, j)) {//上走
                    return true;
                } else if (setWay2(map, i, j - 1)) {//左走
                    return true;
                } else {
                    map[i][j] = 3;//标记一下是死路
                    return false;
                }

            } else {
                return false;// 1 2 3 说明要么是不能走，要么是已经走过了
            }
        }

    }
}