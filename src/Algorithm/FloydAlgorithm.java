package Algorithm;

import java.lang.reflect.Array;
import java.util.Arrays;

public class FloydAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A','B','C','D','E','F','G'};
        //创建领接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};
        Graph02 graph = new Graph02(vertex.length, matrix, vertex);
        graph.floyd();
        graph.show();

    }
}


class Graph02 {
    private char[] vertex;//存放顶点数组
    private int[][] dis;//保存 从各个顶点出发到其他顶点的距离 最后的结果保留在该数组
    private int[][] pre;//保存到目标顶点的前驱顶点

    /**
     * @param length 大小、长度
     * @param matrix 领接矩阵
     * @param vertex 顶点数组
     */
    public Graph02(int length, int[][] matrix, char[] vertex) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];
        //对per初始化 注意存放的是前驱顶点的下标
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    public void floyd(){
        int len = 0;
        for (int i = 0; i < dis.length; i++) {//作为中间顶点  {'A', 'B', 'C', 'D', 'E', 'F', 'G'}
            for (int j = 0; j < dis.length; j++) {//作为起始顶点 {'A', 'B', 'C', 'D', 'E', 'F', 'G'}
                for (int k = 0; k < dis.length; k++) { //作为终点 {'A', 'B', 'C', 'D', 'E', 'F', 'G'}
                     len = dis[j][i] + dis[i][k];//起始顶点到中间顶点的距离 + 中间顶点到终点的距离
                    if(len < dis[j][k]){ //如果这个距离 < 起始顶点到终点的距离 就更改
                        dis[j][k] = len;//更新最短距离
                        pre[j][k] = pre[i][k];//更新出发顶点的后继结点  终点的前驱结点
                    }
                }
            }
            //当上面两层for循环结束  就更新了所有以A为中间顶点的所有线段的最短距离
            //然后进行下一次for循环  就开始所有以B为中间结点更新他们的最短距离
        }
    }

    public void floyd02(){
        int len = 0;
        for (int mid = 0; mid < dis.length; mid++) {//遍历中间结点
            for (int start = 0; start < dis.length; start++) {//遍历开始结点
                for (int end = 0; end < dis.length; end++) {//遍历结束结点
                    len = dis[start][mid] + dis[mid][end];
                    if (len < dis[start][end]){
                        dis[start][end] = len;
                        pre[start][end] = pre[mid][end];//更新前驱结点
                    }
                }
            }
        }
    }

    //显示pre数组 和 dis 数组
    //打印pre数组和dis数组
    public void show() {
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        for (int i = 0; i < dis.length; i++) {
            //打印pre数组
            for (int j = 0; j < dis.length; j++) {
                System.out.print(vertex[pre[i][j]] + " ");
            }
            System.out.println();
            //打印dis数组
            for (int j = 0; j < dis.length; j++) {
                System.out.print("("+"顶点" + vertex[i] + "到顶点" + vertex[j] + "的最短距离=" + dis[i][j] + " "+")");
            }
            System.out.println();
            System.out.println();
        }
    }

}