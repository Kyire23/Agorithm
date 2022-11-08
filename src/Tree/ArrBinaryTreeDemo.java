package Tree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        //创建一个ArrBinaryTree 对象
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.perOrder();
    }
}


class ArrBinaryTree {
    private int[] arr;//存储数据节点的数组
    public ArrBinaryTree(int[] arr){
        this.arr =arr;
    }

    //重载perOrder
    public void perOrder(){
        this.perOrder(0);
    }

    //顺序存储的前序遍历
    public void perOrder(int index){
        //如果数组为空 或者arr.length = 0
        if (arr == null || arr.length == 0){
            System.out.println("数组为空，不能遍历");
        }else {//输出当前元素
            System.out.println(arr[index]);
        }//向左递归遍历
        if ((index * 2 +1)<arr.length){
            perOrder(2*index+1);
        }//向右递归
        if ((index *2+2)<arr.length){
            perOrder(2*index+2);
        }
    }


}