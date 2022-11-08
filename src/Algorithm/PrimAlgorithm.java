package Algorithm;

import java.util.Arrays;

public class PrimAlgorithm {
    public static void main(String[] args) {
        //测试
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verxs = data.length;
        //领接矩阵的关系使用二维数组表示 10000这个大数 表示两个点不连通
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000},};

        //创建一个MGraph 和 minTree对象
        MGraph mGraph = new MGraph(verxs);
        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph, verxs, data, weight);
        //输出
        minTree.showGraph(mGraph);
        //测试普利姆算法
        minTree.prim(mGraph, 0);//


    }

}

//创建最小生成树 村庄图
class MinTree {
    /**
     * @param graph  图对象
     * @param verxs  图对应顶点个数
     * @param data   图各个顶点的值
     * @param weight 图领接矩阵
     */
    //创建图的领接矩阵
    public void createGraph(MGraph graph, int verxs, char data[], int[][] weight) {
        int i, j;
        for (i = 0; i < verxs; i++) {
            graph.data[i] = data[i];
            for (j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    //显示图的领接矩阵方法
    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * @param graph 图
     * @param v     表示从图的第几个顶点开始生成'A'->0 'B'->1 ...
     */

    //编写prim 算法 得到最小生成树
    public void prim(MGraph graph, int v) {
        //visited[] 表示是否访问过 默认是0
        int visited[] = new int[graph.verxs];
        //把当前节点标记已经访问过
        visited[v] = 1;
        //h1 和 h2 记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        //将minWeight 初始化一个较大的数 后面遍历过程会被替换
        int minWeight = 10000;
        for (int k = 1; k < graph.verxs; k++) {//因为有graph.verxs个顶点 普利姆算法结束后 有graph.verxs-1 条边
            //确定每一次生成子图和哪个节点距离最近
            for (int i = 0; i < graph.verxs; i++) {//遍历已经访问过的
                for (int j = 0; j < graph.verxs; j++) {//遍历未访问过的
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        //替换minWeight(寻找已经访问过的结点和未访问过的结点间的权值最小的边)
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //找到一条边是最小
            //System.out.println(h1+"->"+h2   );
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值:" + minWeight);
            //将当前这个结点标记为已经访问
            visited[h2] = 1;
            //minWeight 重新设置为最大值 10000
            minWeight = 10000;

        }
    }

}


class MGraph {
    int verxs; //表示图节点个数
    char[] data;//存放结点数据;
    int[][] weight;//存放边,领接矩阵

    public MGraph(int verxs) {
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];

    }
}
