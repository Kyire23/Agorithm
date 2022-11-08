package Recursion;

/**
 * 解决八皇后案例
 */
public class Queen8 {
    int max = 8;
    int[] arr = new int[max];
    static int count = 0;
    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.printf("一共有%d种解法",count);
    }

    /**
     * 检查第n个皇后和前面的位置的皇后有没有冲突，进行回溯判断
     * 特别注意： check 是 每一次递归时，进入到check中都有  for(int i = 0; i < max; i++)，因此会有回溯
     * @param n 第n个皇后
     */
    private void check(int n){
        if (n == max){//说明已经遍历到最后的一个皇后的最后一个位置了
            print();
            return;
        }

        for (int i = 0; i < max; i++) {
            arr[n] = i;//先把这个皇后放在第第一列的位置上
            if (judge(n)){//没冲突
                check(n+1);//就检查n+1个皇后的情况
            }
            //如果冲突，就继续执行 array[n] = i; 即将第n个皇后，放置在本行得 后移的一个位置
        }
    }

    /**
     *
     * @param n 第n个皇后
     * @return
     */
    private boolean judge(int n){
        for (int i = 0; i < n; i++) {
            // 说明
            //1. array[i] == array[n]  表示判断 第n个皇后是否和前面的n-1个皇后在同一列
            //2. Math.abs(n-i) == Math.abs(array[n] - array[i]) 表示判断第n个皇后是否和第i皇后是否在同一斜线
            // n = 1  放置第 2列 1 n = 1 array[1] = 1
            // Math.abs(1-0) == 1  Math.abs(array[n] - array[i]) = Math.abs(1-0) = 1
            //3. 判断是否在同一行, 没有必要，n 每次都在递增
            if (arr[i] == arr[n] || Math.abs(n-i) == Math.abs(arr[n] - arr[i])){
                return false;
            }
        }
        return true;
    }

    //写一个方法，可以将皇后摆放的位置输出
    private void print(){
        count++;
        for (int i = 0; i < arr.length; i++) {
            System.out.printf(arr[i]+" ");
        }
        System.out.println();
    }
}