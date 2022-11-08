package BinarySortTree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int j : arr) {
            binarySortTree.add(new Node(j));
        }

        //中序遍历测试
        System.out.println("中序遍历测试");
        binarySortTree.infixOrder();

        //测试删除叶子节点
        System.out.println("删除后");
        binarySortTree.delNode(2);
        binarySortTree.delNode(5);

        binarySortTree.delNode(10);
        binarySortTree.delNode(7);
        binarySortTree.delNode(12);


        binarySortTree.infixOrder();


    }
}

//创建二叉排序树
class BinarySortTree {
    private Node root;

    //查找要删除的节点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找父节点删除
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    /**
     * @param node 传入的节点(当做二叉排序树的根节点)
     * @return 返回以 node 为根节点的二叉排序树的最小节点的值
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        //循环查找左子节点 就会找到最小值
        while (target.left != null) {
            target = target.left;
        }
        //找到后 删除
        delNode(target.value);
        return target.value;
    }

    //删除节点
    public void delNode(int value) {
        if (root == null) {
        } else {
            //需要先去找到要删除的节点 targetNode
            Node targetNode = search(value);
            if (targetNode == null) {
                return;
            }
            //如果当前二叉排序树只有一个节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }

            //去找到targetNode的父节点
            Node parent = searchParent(value);
            //如果要删除的节点为叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                if (parent.left != null && parent.left.value == value) {//左子节点
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    //右子节点
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {
                //删除有两颗子树的节点
                //从右边找最小
                targetNode.value = delRightTreeMin(targetNode.right);
                //也可以从左边找最大

            } else {
                //删除只有一颗子树的节点
                //如果要删除的节点有左子节点
                if (targetNode.left != null) {
                    if (parent != null) {
                        //如果targetNode 是 parent 的左子节点
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else {
                            //targetNode 是 parent 的右子节点
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {
                    //如果要删除的节点有右子节点
                    //如果targetNode 是 parent 的左子节点
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {
                            //targetNode 是 parent 的右子节点
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }

                }
            }


        }
    }

    //添加节点的方法
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空，不能遍历");
        }
    }

}

class Node {
    int value;
    Node left;
    Node right;

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public Node(int value) {
        this.value = value;
    }

    // value 希望删除节点的值 如果找到就返回该节点 否则返回null
    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {
            //向左递归查找
            //如果左子节点为空
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            //不小于则向右递归查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    //查找要删除节点的父节点
    public Node searchParent(int value) {
        //如果当前节点就是要删除的父节点 就返回
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //如果查找的值小于当前节点的值 并且当前节点的左子节点不为空
            if (value < this.value && this.left != null) {
                return left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null; // 未找到父节点
            }
        }
    }

    //添加节点的方法 递归 需要满足二叉排序树的要求
    public void add(Node node) {
        if (node == null) {
            return;
        }//判断传入节点的值和当前子树跟节点值的关系
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {//递归向左子树添加
                this.left.add(node);
            }
        } else {//添加的节点的值大于当前节点的值
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }


}
