package AVLTree;

public class AVLTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {4, 3, 6, 5, 7, 8};
//        int[] arr = {10, 12, 8, 9, 7, 6};
        int[] arr = {10, 11, 7, 6, 8, 9};
        //创建一个AVLTree
        AVLTree avlTree = new AVLTree();
        //添加节点
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        //遍历
        System.out.println("中序遍历");
        avlTree.infixOrder();

        System.out.println("做平衡处理之后");
        System.out.println("树的高度=" + avlTree.getRoot().height()); // 4
        System.out.println("左子树的高度=" + avlTree.getRoot().leftHight()); // 4
        System.out.println("右子树的高度=" + avlTree.getRoot().rightHight()); // 4

    }
}

//创建AVLTree
class AVLTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    //查找要删除的节点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找要删除节点的父节点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    /**
     * 返回并且删除以node为根节点的二叉排序的最小节点的值
     *
     * @param node 传入的节点（当作二叉排序树的根节点）
     * @return 返回的以node为根节点的二叉排序树额最小节点的值
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        //循环的查找左节点，就会找到最小值
        while (target.left != null) {
            target = target.left;
        }
        //删除最小节点
        delNode(target.value);
        return target.value;
    }

    //与上面相反，这是以tergetNode节点左子树的最大值的Node代替tergetNode
    public int delLetfTreeMin(Node node) {
        Node target = node;
        //循环的查找左节点，就会找到最小值
        while (target.right != null) {
            target = target.right;
        }
        //删除最大节点
        delNode(target.value);
        return target.value;
    }

    //删除节点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            //1. 首先定位要删除的节点，targetNode
            Node targetNode = search(value);
            //如果没有找到要删除的节点
            if (targetNode == null) {
                return;
            }
            //如果这个二叉树只有一个节点,并且顺利度过上一个步骤
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            //如果找到了targetNode并且还是大于等于2个节点的二叉树
            //2. 再找到targetNode的父节点parent
            Node parent = searchParent(value);
            //3.1 如果要删除的节点是叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                //3.1.1判断targetNode是parent的左子节点还是右子节点后置空删除
                if (parent.left != null && parent.left.value == value) {
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {
                //3.3 删除有两棵子树的节点
                //3.3.1 从targetNode的右子树找到最小的节点,删除，并且赋值
//                int minVal = delRightTreeMin(targetNode.right);
//                targetNode.value = minVal;
                //3.3.1 从targetNode的左子树找到最大的节点,删除，并且赋值
                int maxVal = delLetfTreeMin(targetNode.left);
                targetNode.value = maxVal;
            } else {
                //3.2 删除只有一颗子树的情况
                if (targetNode.left != null) {
                    if (parent != null) {
                        //3.2.1 如果targetNode有左节点
                        //3.2.1.1 如果targetNode是parent的左节点
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else {
                            //3.2.1.2 如果targetNode是parent的右节点
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {
                    if (parent != null) {
                        //3.2.2 如果targetNode有右节点
                        //3.2.2.1 如果targetNode是parent的左节点
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {
                            //3.2.1.2 如果targetNode是parent的右节点
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
            System.out.println("当前二叉排序树为空");
        }
    }
}


//创建节点
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        super();
        this.value = value;
    }

    //返回左子树的高度
    public int leftHight() {
        if (left == null) {
            return 0;
        } else {
            return left.height();
        }
    }

    //返回右子树的高度
    public int rightHight() {
        if (right == null) {
            return 0;
        } else {
            return right.height();
        }
    }

    //返回当前节点的高度,以该节点为根节点的树的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //左旋转的方法
    private void leftRotate() {
        //步骤如下
        //创建新的节点,以当前根节点的值
        Node newNode = new Node(value);
        //把新的节点的左子树设置成当前节点的左子树
        newNode.left = left;
        //把新的节点的右子树设置成当前节点的右子树的左子树
        newNode.right = right.left;
        //当前节点的值替换成右子节点的值
        value = right.value;
        //把当前节点的右子树设置成右子树的右子树
        right = right.right;
        //把当前节点的左子树设置成新的节点
        left = newNode;
    }

    //右旋转的方法
    private void rightRotate() {
        //步骤如下
        //创建新的节点,以当前根节点的值
        Node newNode = new Node(value);
        //把新的节点的右子树设置成当前节点的右子树
        newNode.right = right;
        //把新的节点的左子树设置成当前节点的左子树的右子树
        newNode.left = left.right;
        //当前节点的值替换成左子节点的值
        value = left.value;
        //把当前节点的左子树设置成左子树的左子树
        left = left.left;
        //把当前节点的右子树设置成新的节点
        right = newNode;
    }


    @Override
    public String toString() {
        return "Node{" + "value=" + value + '}';
    }

    //添加节点
    //递归的形式添加节点，需要满足二叉排序树的要求
    public void add(Node node) {
        if (node == null) {
            return;
        }
        //判断传入的节点的值，和当前子树的根节点的值的关系
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
        //当添加完一个节点后，如果 (右子树的高度-左子树的高度) > 1，左旋转
        if (rightHight() - leftHight() > 1) {
            //【双旋转】如果它的右子树的左子树的高度大于它的右子树的右子树的高度
            if (right != null && right.leftHight() > right.rightHight()) {
                //先对当前节点的右节点进行右旋转
                right.rightHight();
                leftRotate();
            } else {
                leftRotate();//左旋转
            }
            return;//!!!!很重要
        }

        //当添加完一个结点后，如果(左子树高度-右子树高度) > 1, 右旋转
        if (leftHight() - rightHight() > 1) {
            //【双旋转】如果它的左子树的右子树高度大于左子树的左子树的高度
            if (left != null && left.rightHight() > left.leftHight()) {
                //先对当前节点的左节点进行左旋转
                left.leftRotate();//左节点左旋转
                rightRotate();//当前结点右旋转
            } else {
                //直接进行右旋转即可
                rightRotate();
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


    //删除节点
    /*
     * 考虑点
     * 1. 删除叶子节点
     * 2. 删除只有一颗子树的节点
     * 3. 删除有两颗子树的节点
     */

    /**
     * 为删除节点做方法准备->查找要删除的节点
     *
     * @param value 希望删除的节点的值
     * @return 如果找到了返回该节点, 没有找到就返回null
     */
    public Node search(int value) {
        if (value == this.value) {
            //找到
            return this;
        } else if (value < this.value) {
            //向左查找
            //如果左子节点为空，就意味着没找到
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {//value>this.value
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 查找要删除节点的父节点
     *
     * @param value 要找到的节点(targetNode)的值
     * @return 返回的是要删除的节点父节点，如果没有接返回null
     */
    public Node searchParent(int value) {
        // 如果当前节点就是要删除的节点的父节点，就返回
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            // 如果查找的值小于当前节点的值，并且当前节点的左子节点不为空
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;//没有找都父节点
            }
        }
    }
}

