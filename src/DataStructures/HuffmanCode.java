package DataStructures;
import java.util.*;
//该部分内容为复制粘贴后的内容
public class HuffmanCode {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte contentBytes[] = content.getBytes();
        System.out.println(contentBytes.length);//40

        //测试
        List<HuffmanNode> nodes = getHuffmanNodes(contentBytes);
        System.out.println("nodes" + nodes);

        //测试创建二叉树
        System.out.println("赫夫曼树");
        HuffmanNode HuffmanTreeRoot = creatHuffmanTree(nodes);
        System.out.println("前序遍历");
        HuffmanTreeRoot.preOreder();

        //测试一把是否赫夫曼编码
        //getCodes(HuffmanTreeRoot,"",stringBuilder);
        Map<Byte,String> huffmanCodes = getCodes(HuffmanTreeRoot);
        System.out.println("~生成的赫夫曼编码表\n"+huffmanCodes);

    }

    //生成赫夫曼树对应的赫夫曼编码里
    //思路
    //(1)将赫夫曼编码表放在Map<Byte,String>
    //  这个Map就跟Python的字典一样，{[key:value] [key:value]……}
    static Map<Byte,String> huffmanCodes = new HashMap<Byte, String>();
    //(2)在生成赫夫曼编码表时，需要去拼接路径，所以定义一个StringBuilder 存储某个叶子节点的路径
    static StringBuilder stringBuilder = new StringBuilder();
    //Ps:为了调用方便，我们重载一下getCodes
    private static Map<Byte,String> getCodes(HuffmanNode root){
        if (root==null){
             return null;
        }
        //处理root的左子树
        getCodes(root.left,"0",stringBuilder);
        //处理root的右子树
        getCodes(root.right,"1",stringBuilder);
        return huffmanCodes;
    }
    /**
     * //(3)方法
     * 功能：得到传入的node节点的所有赫夫曼编码，并存放到huffmanCodes集合中
     * @param node 传入的节点（默认从root）
     * @param code 该节点的路径（左节点0和右节点1）
     * @param stringBuilder 拼接路径
     */
    private static void getCodes(HuffmanNode node,String code,StringBuilder stringBuilder){
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将传入的code加入到StringBuilder2中
        stringBuilder2.append(code);
        if (node!=null){
            //如果node==null不处理
            //判断当前node时叶子节点还是非叶子节点
            if (node.data==null){
                //说明是非叶子节点
                //递归处理
                //向左
                getCodes(node.left,"0",stringBuilder2);
                //向右
                getCodes(node.right,"1",stringBuilder2);
            }else {
                //说明是一个叶子节点
                huffmanCodes.put(node.data,stringBuilder2.toString());
            }
        }
    }
    /**
     * @param bytes 接受的数组
     * @return 返回的就是List形式：[Node[date=97,weight =5],Node[date=32,weight=9]]……
     */
    private static List<HuffmanNode> getHuffmanNodes(byte bytes[]) {
        //1.创建ArrayList
        ArrayList<HuffmanNode> nodes = new ArrayList<HuffmanNode>();
        //2.遍历bytes，统计每个byte出现的次数->map
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                //Map还没有这个字符数据
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }
        //把每个键值对转成HuffmanNode对象，并加入nodes中
        //遍历Map
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }
    //通过上面的List，常见对应的赫夫曼树
    private static HuffmanNode creatHuffmanTree(List<HuffmanNode> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            HuffmanNode leftNode = nodes.get(0);
            HuffmanNode rightNode = nodes.get(1);
            //没有data只有权值weight
            HuffmanNode parent = new HuffmanNode(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }
    //前序遍历
    private static void preOrder(HuffmanNode root) {
        if (root != null) {
            root.preOreder();
        } else {
            System.out.println("空树");
        }
    }
}



//创建Node
class HuffmanNode implements Comparable<HuffmanNode> {
    Byte data;//存放数据本身'a'->97 ' '->32
    int weight;//权值,字符出现的次数
    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override//表示从小到达排序
    public int compareTo(HuffmanNode huffmanNode) {
        return this.weight - huffmanNode.weight;
    }

    @Override
    public String toString() {
        return "HuffmanNode{" + "data=" + data + ", weight=" + weight + '}';
    }

    public void preOreder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOreder();
        }
        if (this.right != null) {
            this.right.preOreder();
        }
    }
}
