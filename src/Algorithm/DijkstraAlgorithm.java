package Algorithm;
import java.util.Arrays;

/**
 * 迪杰斯特拉算法  --》 最短路径问题（一个顶点到其他顶点的最短路径）
 * 问题描述：有七个村庄(A,B,C,D,E,F,G) 现在有6个邮差 从G点出发  分贝把邮件送到A,B,C,D,E,F 6个村庄
 * 问：如何计算出G村庄到其他各个村庄的最短距离
 */
public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        int[][] matrix = new int[vertexes.length][vertexes.length];
        final int N = 65535;
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};
        Graph graph = new Graph(vertexes, matrix);
        graph.print();
        graph.dijkstra(6);
        graph.show();
    }
}

class VisitedVertex {
    public int[] already_arr;//记录该索引的顶点是否已经被访问 0表示没有被访问  1表示被访问 动态更新
    public int[] pre_visited;//记录该索引对应的顶点的前驱结点的索引 初始化为0 动态更新
    public int[] dis;//记录出发顶点到其他所有顶点的距离 比如G为出发顶点，就会记录G到其他顶点的距离，
    //会动态更新  最后求的最短距离会被放在这个数组中 初始化的时候出发顶点到其他顶点的距离都默认为65535  到自己的距离为0

    /**
     * 构造器初始化以上三个数组
     *
     * @param index 出发点的索引
     * @param len   顶点数
     */
    public VisitedVertex(int index, int len) {
        already_arr = new int[len];
        already_arr[index] = 1;
        pre_visited = new int[len];
        dis = new int[len];
//        for (int i = 0; i < len; i++) {
//            dis[i] = 65535;
//        }
        Arrays.fill(dis, 65535);
        dis[index] = 0;
    }


    /**
     * 判断对应索引的结点是否已经被访问过
     *
     * @param index 索引
     * @return 如果被访问过 返回真  否则返回假
     */
    public boolean isVisited(int index) {
        return already_arr[index] == 1;
    }

    /**
     * 更新指定索引结点的索引为前驱结点的索引
     *
     * @param index 指定结点
     * @param pre   前驱结点
     */
    public void updatePre(int index, int pre) {
        pre_visited[index] = pre;
    }

    /**
     * 更新出发结点到其他结点的距离
     *
     * @param index 其他结点的距离
     * @param len   距离
     */
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    /**
     * 返回指定索引的结点到出发结点的距离
     *
     * @param index 指定索引
     * @return 返回出发结点到指定索引结点的距离
     */
    public int getDis(int index) {
        return dis[index];
    }

    /**
     * 获取到出发结点的下一个新的访问结点 原则上是离出发结点最近的且没有被访问的结点
     * 访问的顺序是G-->A(2)
     * G-->B(3)
     * G-->E(4)
     * G-->F(6)
     * G-->C(G-->A-->C 9)
     * G-->D(G->F->D 10)
     *
     * @param index 出发结点的索引
     */
    public int nextNoVisitedVertex(int index) {
        int min = 65535;
        int next = -1;
        for (int i = 0; i < dis.length; i++) {
            if (!isVisited(i) && dis[i] < min) { //第一次访问后 dis:{2,3,65535,65535,4,6,0}
                min = dis[i];
                next = i;
            }
        }
        already_arr[next] = 1;//标记为已访问
        return next;
    }

    @Override
    public String toString() {
        return "VisitedVertex{" +
                "already_arr=" + Arrays.toString(already_arr) +
                "\n pre_visited=" + Arrays.toString(pre_visited) +
                "\n dis=" + Arrays.toString(dis) +
                '}';
    }
}

class Graph {
    private char[] vertexes;//顶点数组
    public int[][] matrix;//邻接矩阵
    VisitedVertex visitedVertex;

    public Graph(char[] vertexes, int[][] matrix) {
        this.matrix = matrix;
        this.vertexes = vertexes;
    }

    //打印图
    public void print() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 迪杰斯特拉算法的入口 从指定索引的结点出发
     *
     * @param index 指定索引
     */
    public void dijkstra(int index) {
        visitedVertex = new VisitedVertex(index, vertexes.length);
        update(index);
        for (int i = 1; i < vertexes.length; i++) { //已经有一个结点被访问  所以再循环vertexes.length - 1次
            index = visitedVertex.nextNoVisitedVertex(index);
            update(index);
        }
    }

    /**
     * 更新出发结点的所有后继节点 以及 出发结点到所有其他结点的距离
     *
     * @param index 出发结点的索引
     */
    private void update(int index) {
        for (int i = 0; i < matrix[index].length; i++) {
            //visitedVertex.getDis(index) 恒为0  因为初始化dis数组的时候  出发结点到其他结点的距离统一为65535 除了自己
            int len = visitedVertex.getDis(index) + matrix[index][i];//出发结点到index索引结点的距离 + index结点到i结点的距离
            if (!visitedVertex.isVisited(i) && len < visitedVertex.dis[i]) {//前提没有被访问 算出的距离还必须小于已有的距离
                visitedVertex.updatePre(i, index);//更新该索引结点的前驱结点为index这个索引（即出发顶点）
                visitedVertex.updateDis(i, len);//更新该索引的结点到出发结点点的距离
                if (visitedVertex.pre_visited[index] != 0) {
                    System.out.println(vertexes[visitedVertex.pre_visited[index]] + "-->" + vertexes[index] + "-->" + vertexes[i] + " len=" + len);
                }
            }
        }
        if (visitedVertex.pre_visited[index] != 0) {//排除出发点自己  因为出发点的前驱始终为0
            System.out.println("顶点" + vertexes[index] + "的前驱=" + vertexes[visitedVertex.pre_visited[index]]);
        }
    }

    public void show() {
        System.out.println(visitedVertex);//打印最后的数组  dis数组即为最终解
    }
}

