package Tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
        int arr[] = {13, 7, 8, 3, 29, 6, 1};
//        createHuffmanTree(arr);
        Node root = createHuffmanTree(arr);
        //测试
        perOder(root);
    }

    //调用前序遍历的方法
    public static void perOder(Node root){
        if (root != null){
            root.perOder();
        }else {
            System.out.println("空树不能遍历");
        }
    }


    //创建赫夫曼树的方法
    public static Node createHuffmanTree(int[] arr) {
        //1.遍历arr 数组
        //2.将arr的每个元素构建成一个Node
        //3.将Node 放入到ArrayList中
        List<Node> nodes = new ArrayList<Node>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        while (nodes.size() > 1) {
            //排序 从小到大
            Collections.sort(nodes);

            System.out.println("nodes = " + nodes);

            //取出根节点权值最小的两颗二叉树
            Node leftNode = nodes.get(0);
            //取出第二小的节点
            Node rightNode = nodes.get(1);

            //构建一个新的二叉树

            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            //从ArrayList 删除 处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            //再将parent加入到nodes
            nodes.add(parent);
        }
        //返回赫夫曼树root节点
        return nodes.get(0);
    }
}
//创建节点类
//为了 让Node 对象持续排序Collections集合排序
class Node implements Comparable<Node> {
    int value;
    Node left;
    Node right;

    //前序遍历
    public void perOder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.perOder();
        }
        if (this.right != null) {
            this.right.perOder();
        }
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;//从小到大排序
    }
}


