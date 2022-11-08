package Tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        //先创建一个二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        //手动创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        binaryTree.setRoot(root);

//        //测试
//        System.out.println("前序遍历");//中左右
//        binaryTree.setRoot(root);
//        binaryTree.perOder();
//
//        System.out.println("中序遍历");//左中右
//        binaryTree.infixOrder();
//
//        System.out.println("后续遍历");//左右中
//        binaryTree.postOrder();
//
//        //查找测试
//        System.out.println("前序遍历查找");
//        HeroNode resNode = binaryTree.perOrderSearch(3);
//        if (resNode != null){
//            System.out.printf("找到了，信息为no=%d name=%s",resNode.getNo(),resNode.getName());
//        }else {
//            System.out.printf("没有找到no = %d 的英雄",3);
//        }

        //删除测试
        System.out.println("删除前，前序遍历");

        binaryTree.perOder();
        binaryTree.delNode(4);
        System.out.println("=================");
        System.out.println("删除后~~~");
        binaryTree.perOder();


    }


}

//定义BinaryTree 二叉树
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //删除节点
    public void delNode(int no){
        if (root != null){
            if (root.getNo() == no){
                root = null;
            }else {
                //递归删除
                root.delNode(no);
            }
        }else {
            System.out.println("空树，不能删除");
        }
    }

    //前序遍历
    public void perOder() {
        if (this.root != null) {
            this.root.perOder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //前序遍历查找
    public HeroNode perOrderSearch(int no){
        if (root != null){
            return root.perOrderSearch(no);
        }else {
            return null;
        }
    }

    public HeroNode infixOrderSearch(int no){
        if (root != null){
            return root.infixOrderSearch(no);
        }else {
            return null;
        }
    }

    public HeroNode postOrderSearch(int no){
        if (root != null) {
            return this.root.postOrderSearch(no);
        }else {
            return null;
        }
    }
}


class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //递归删除节点 置空
    public void delNode(int no){
        if (this.left!= null && this.left.no == no){
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no){
            this.right = null;
            return;
        }
        if (this.left !=null){
            this.left.delNode(no);
        }
        if (this.right != null){
            this.right.delNode(no);
        }

    }
    //前序遍历
    public void perOder() {
        System.out.println(this);//先输出父节点
        //递归左子树前序遍历
        if (this.left != null) {
            this.left.perOder();
        }//递归右子树前序遍历
        if (this.right != null) {
            this.right.perOder();
        }
    }

    //中序遍历
    public void infixOrder() {
        //递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }//输出父节点
        System.out.println(this);
        //递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //前序遍历查找
    public HeroNode perOrderSearch(int no) {
        System.out.println("进行前序遍历");
        if (this.no == no) {
            return this;
        }
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.perOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.perOrderSearch(no);
        }
        return resNode;
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.no == no) {
            return this;
        }
        return resNode;
    }
}