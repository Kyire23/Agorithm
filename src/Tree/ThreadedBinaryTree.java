package Tree;



public class ThreadedBinaryTree {
    public static void main(String[] args) {
        //测试中序线索二叉树的功能
        HeroNode01 root = new HeroNode01(1, "Tom");
        HeroNode01 node2 = new HeroNode01(3, "jack");
        HeroNode01 node3 = new HeroNode01(6, "smith");
        HeroNode01 node4 = new HeroNode01(8, "mary");
        HeroNode01 node5 = new HeroNode01(10, "king");
        HeroNode01 node6 = new HeroNode01(14, "dim");
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //测试线索化
        BinaryTree01 binaryTree = new BinaryTree01();
        binaryTree.setRoot(root);
        binaryTree.threadedNodes();

        //以10号节点为例
        HeroNode01 leftNode = node5.getLeft();
        HeroNode01 rightNode = node5.getRight();
        System.out.println("10号节点的前驱节点是=" + leftNode);
        System.out.println("10号节点的后继节点是=" + rightNode);

        //线索化的方式遍历线索化二叉树
        System.out.println("线索化的方式遍历线索化二叉树");
        binaryTree.threadedList();//8->3->10->1->14->6
    }
}

//定义ThreadedBinaryTree实现了线索化功能的二叉树
class BinaryTree01 {
    private HeroNode01 root;
    //再递归进行线索化时，这个pre总是保留前一个节点
    private HeroNode01 pre = null;

    public void setRoot(HeroNode01 root) {
        this.root = root;
    }

    //重载
    public void threadedNodes() {
        this.threadedNodes(root);
    }

    //遍历线索化二叉树的方法
    public void threadedList() {
        //定义临时存储变量node
        HeroNode01 node = root;
        while (node != null) {
            //循环的找到leftType==1的节点，第一个找到的应该是8
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            System.out.println(node);
            //如果当前节点的右指针指向的是后继节点，就一直输出
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的节点
            node = node.getRight();
        }
    }

    //建立中序线索化二叉树方法
    /**
     * @param node 当前需要线索化的节点
     */
    public void threadedNodes(HeroNode01 node) {
        //如果当前节点为空
        if (node == null) {
            return;
        }

        //(1)先线索化左子树
        threadedNodes(node.getLeft());
        //(2)线索化当前节点[有难度]
        /*先处理当前节点的前驱节点*/
        /*以8节点为例:8.left = null;8.letType = 1*/
        if (node.getLeft() == null) {
            //让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //并且修改当前节点的左指针的类型,指向前驱节点
            node.setLeftType(1);
        }
        /*再处理后继节点*/
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        //每处理一个节点后，让当前节点是下一个节点的前驱节点
        pre = node;
        //(3)再线索化右子树
        threadedNodes(node.getRight());
    }
}

//创建HeroNode
class HeroNode01 {
    private int no;
    private String name;
    private HeroNode01 left;
    private HeroNode01 right;
    //New
    //1. if(leftType==0),表示指向左子树，if(leftType==1)责表示指向前驱节点
    //2. if(rightType==0),表示指向右子树，if(righType==1)则表示指向后继节点
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HeroNode01(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public HeroNode01 getLeft() {
        return left;
    }

    public void setLeft(HeroNode01 left) {
        this.left = left;
    }

    public HeroNode01 getRight() {
        return right;
    }

    public void setRight(HeroNode01 right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode01[no=" + no + ", name=" + name + "]";
    }
}
