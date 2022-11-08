package Algorithm;

public class dacHanoiTower {
    public static void main(String[] args) {
        hanoiTower(3,'A','B','C');

    }

    //汉诺塔移动方法 分治算法实现
    public static void hanoiTower(int num, char a,char b,char c){
        //只有一个盘时
        if (num == 1){
            System.out.println("第一个盘从"+a+"->"+c);
        }else {
            //n >=2 时 可以看成两个盘1 最下面盘2
            //1. 先把上面盘 A->B 移动过程会使用到 c
            hanoiTower(num-1,a,c,b);
            //2. A->C
            System.out.println("第"+num+"个盘从 "+a+"->"+c);
            //3. B->C 移动过程会使用到 a
            hanoiTower(num-1,b,a,c);
        }
    }
}
