package Algorithm;

import java.util.Arrays;

public class KruskalCase {
    private int edgesNum;//边数
    private char[] vertex;//存放顶点的集合
    private int[][] matrix;//邻接矩阵

    //用这个值表示邻接矩阵中两条边不连通
    private final static int INF = Integer.MAX_VALUE;


    public static void main(String[] args) {
        char[] vertex = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {{0, 12, INF, INF, INF, 16, 14},//默认0表示指向自己 比如AA BB
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0}};
        KruskalCase kruskalCase = new KruskalCase(vertex, matrix);
        kruskalCase.print();
        kruskalCase.kruskal();
    }

    public KruskalCase(char[] vertex, int[][] matrix) {//构造器用于初始化图
        //初始化顶点数 和 边的个数
        int len = vertex.length;
        this.vertex = new char[len];
        this.matrix = new int[len][len];
        //初始化顶点 拷贝复制  并不会因为参数数组的改变而直接影响成员数组
        for (int i = 0; i < len; i++) {
            this.vertex[i] = vertex[i];
        }
        //也可以这样简单的引用传递
        //初始化边 也是拷贝复制的形式
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        //获取到边数
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {//j从i+1开始算 重复的边和自己对自己的边不算
                if (this.matrix[i][j] != INF) {
                    edgesNum++;
                }
            }
        }
    }

    public void kruskal(){
        int[] ends = new int[edgesNum];//定义的终点集合 是一个动态变化的
        aEdge[] edges = getEdges();//获取到所有的边
        bubbleSort(edges);//按照权值大小进行排序(从小到大)
        aEdge[] res = new aEdge[edgesNum];//最小生成树的最优结果集
        //最后结果数组的索引
        int index = 0;
        for (int i = 0; i < edgesNum; i++) {
            if(index == vertex.length){//优化 如果下标等于顶点数目 说明最小生成树已经生成
                break;
            }
            int v1 = getIndexByValue(edges[i].start);//获取到该边第一个结点对应的索引
            int v2 = getIndexByValue(edges[i].end);//获取到该边第二个顶点在顶点集合中对应的索引
            int end1 = getEnd(ends,v1);//得到对应的终点索引
            int end2 = getEnd(ends,v2);
            if(end1 != end2){//终点不相同的话  才有资格加入到结果集中
                ends[end1] = end2;//更新终点数组
                res[index++] = edges[i];//已有一条边加入到res 数组当中
            }
        }
        System.out.println("最小生成树为:");
        for (int i = 0; i < index; i++) {
            System.out.println(res[i]);
        }
    }

    // 打印图
    public void print() {
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                System.out.printf("%13d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    //根据值获取到对应的下标 没有则返回-1
    private int getIndexByValue(char val) {
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] == val) {
                return i;
            }
        }
        return -1;
    }

    //按照权值对边的数组进行排序
    public void bubbleSort(aEdge[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    aEdge tmp = edges[j];//交换的是一个对象
                    edges[j] = edges[j + 1];
                    edges[j + 1] = tmp;
                }
            }
        }
    }

    //获取到图的所有的边 返回一个数组
    //通过matrix 领接矩阵 来获取
    public aEdge[] getEdges() {
        aEdge[] edges = new aEdge[this.edgesNum];
        int index = 0;
        for (int i = 0; i < vertex.length; i++) {
            for (int j = i + 1; j < vertex.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new aEdge(vertex[i], vertex[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 返回索引i的字符在已生成的最小生成树的终点索引
     * 如果两个顶点在最小生成树的终点相同  这两个顶点组成的边就不能加入到最小生成树中
     * @param ends 已生成的最小生成树的每个索引对应终点的数组
     * @param i    传入的索引i的顶点
     * @return 返回以该索引的终点的索引
     */
    public int getEnd(int[] ends, int i) {
        while(ends[i] != 0){
            i = ends[i];
        }
        return i;
    }
}

//一条边对应一个对象 为了便于把边存放到数组中  形式如【<A,B> 10】
class aEdge {
    char start;//边的起始顶点
    char end;//边的终点
    int weight;

    public aEdge(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "{aEdge<" +
                start +
                "," + end +
                "> weight=" + weight + '}';
    }
}

