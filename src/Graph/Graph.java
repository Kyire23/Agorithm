package Graph;

import java.util.*;

public class Graph {
    private ArrayList<String> vertexList;//存储图的结点  用String表示结点
    private int[][] edges;//定义一个二维数组  表示顶点之间的联系 1表示直接相连 0表示没有直达路径
    private int edgeCounts;//表示边的个数
    private boolean[] isVisited;//标记结点是否被访问
    private Queue<Integer> queue;//用来存储刚被标记的结点

    public static void main(String[] args) {
        int n = 5;//定义结点的个数
        String[] vertexList = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph(n);
        for (String vertex : vertexList) {
            graph.addVertex(vertex);//添加顶点到集合
        }
        //添加边到集合
        graph.addEdge(0, 1, 1);//A--B
        graph.addEdge(0, 2, 1);//A--C
        graph.addEdge(1, 2, 1);//B--C
        graph.addEdge(1, 3, 1);//B--D
        graph.addEdge(1, 4, 1);//B--E
        graph.showGraph();
        System.out.print("图的深度优先遍历 ");
        graph.dfs();
        System.out.print("\n图的广度优先遍历 ");
        graph.bfs();
    }

    public Graph(int n) {//初始化二维数组和集合
        vertexList = new ArrayList<>(n);
        edges = new int[n][n];
        isVisited = new boolean[n];
        queue = new LinkedList<>();
    }

    public void bfs() {//遍历所有结点 进行广度优先遍历
        isVisited = new boolean[getNumOfVertex()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    //对一个结点进行广度优先遍历 类似于层次遍历
    public void bfs(boolean[] isVisited, int i) {
        //首先访问该节点
        System.out.print(getVertexByIndex(i) + "-->");
        isVisited[i] = true;//标记为已访问
        queue.offer(i);//入队
        int u;//表示队列头节点的下标
        int w;//表示u的邻接点下标
        while (!queue.isEmpty()) {
            u = queue.poll();//出队
            w = getFirstLinkedVertex(u);
            while (w != -1) {//只要还有邻接点 就进行while循环
                if (!isVisited[w]) {
                    System.out.print(getVertexByIndex(w) + "-->");
                    isVisited[w] = true;
                    queue.offer(w);//再入队
                }
                w = getNextVertex(u, w);//找遍以u为结点所有的未被访问的邻接点输出并入队
                //这句话充分体现广度优先遍历
            }
        }
    }

    public void dfs() {//遍历所有结点 进行图的深度优先遍历  考虑到有的结点之间并没有相互联系 所以需要遍历每一个结点进行dfs算法
        isVisited = new boolean[getNumOfVertex()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    //以一个结点开始的的图的深度优先遍历  DFS
    private void dfs(boolean[] isVisited, int i) {
        System.out.print(getVertexByIndex(i) + "-->");//输出
        isVisited[i] = true;//标记为已访问
        //查找节点i 的第一个邻接节点
        int w = getFirstLinkedVertex(i);
        while (w != -1) {//只要以当前结点的索引的第一个邻接点存在 就进行while循环
            if (!isVisited[w]) {//当前结点（递归过程中这个结点在变化）第一个邻接点只要没有被访问 就进行递归访问
                dfs(isVisited, w);
            }
            //如果当前结点的第一个邻接点已经被访问过  就进行以i为索引的结点 获取他的第二个邻接点  即以第一个邻接点的下一个邻接点
            w = getNextVertex(i, w);//直到w=-1 跳出while循环 方法结束
        }
    }

    //获取到当前索引的结点的第一个邻接点索引 如果没有 则返回-1
    public int getFirstLinkedVertex(int index) {
        for (int i = 0; i < edges.length; i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    //获取到当前索引结点v的邻接结点w的下一个邻接结点的索引  如果没有则返回-1
    public int getNextVertex(int v, int w) {
        for (int i = w + 1; i < edges.length; i++) {
            if (edges[v][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    //添加顶点到集合的方法
    public void addVertex(String value) {
        vertexList.add(value);
    }

    /**
     * @param v1     添加的第一个顶点在集合中的下标
     * @param v2     添加的第二个顶点在集合中的下标
     * @param weight 代表是否直接相连
     */
    //添加边到集合的方法
    public void addEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;//注意是无向图
        edges[v2][v1] = weight;
        edgeCounts++;
    }

    //查看图中顶点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //查看边的个数
    public int getEdgeCount() {
        return edgeCounts;
    }

    //根据索引获取到顶点
    public String getVertexByIndex(int index) {
        return vertexList.get(index);
    }

    //返回v1 和 v2 的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //查看图
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }
}


