package Graph;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * 定义无向图
 */
public class DFSGraph {
    // 顶点数
    private int vector;
    // 图的邻接表
    private LinkedList<Integer>[] adj;

    // 初始化无向图
    public DFSGraph(int v) {
        // 有v个顶点
        this.vector = v;
        // 一个长为v的数组，负责给每个顶点存放邻边
        adj = new LinkedList[v];
        // 邻边用LinkedList类型存放:易增删，进行初始化
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new LinkedList();
        }
    }

    // 向图中某个顶点v添加邻边w
    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

    // DFS算法设计
    public void DFS(int v) {
        // 用于记录每个节点是否被访问过
        boolean[] visited = new boolean[this.vector];
        // 回溯
        DFSUtil(v, visited);
    }

    // 回溯算法
    private void DFSUtil(int v, boolean[] visited) {
        // 访问节点v，标记当前节点被访问，输出该节点
        visited[v] = true;
        System.out.print(v + " ");

        // 遍历节点v的邻接矩阵，访问节点v的所有邻接节点
        Iterator<Integer> iterator = adj[v].listIterator();
        while (iterator.hasNext()) {
            int n = iterator.next();
            if(!visited[n]) {
                DFSUtil(n, visited);
            }
        }
    }

    public static void main(String[] args) {
        DFSGraph g = new DFSGraph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("下面是DFS搜索结果 " + "(从2号结点开始)");

        g.DFS(2);

    }
}


