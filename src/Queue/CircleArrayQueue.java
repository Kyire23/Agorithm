package Queue;

import java.util.Scanner;

public class CircleArrayQueue {
        //使用数组模拟队列
        public static void main(String[] args) {
            //测试 有效值其实为3
            CircleArray queue = new CircleArray(4);
            char key = ' ';
            Scanner scanner =new Scanner(System.in);
            boolean loop = true;
            //输出一个菜单
            while (loop){
                System.out.println("s(show):显示队列");
                System.out.println("e(exit):退出程序");
                System.out.println("a(add):添加数据到队列");
                System.out.println("g(get):从队列取出数据");
                System.out.println("h(head):查看队列头数据");
                key = scanner.next().charAt(0);//接受一个字符
                switch (key){
                    case 's':
                        queue.showQueue();
                        break;
                    case 'a':
                        System.out.println("输出一个数");
                        int value = scanner.nextInt();
                        queue.addQueue(value);
                        break;
                    case 'g':
                        try {
                            int res = queue.getQueue();
                            System.out.printf("取出数据是%d\n",res);
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 'h':
                        try {
                            int res = queue.headQueue();
                            System.out.printf("取出头数据是%d\n",res);

                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    case 'e':
                        scanner.close();
                        loop = false;
                        break;

                    default:
                        break;
                }
            }
            System.out.println("程序退出~");
        }
}

class CircleArray{
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    public CircleArray(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }
    //判断队列是否满
    public boolean isFull(){
        return (rear +1)%maxSize == front;
    }

    //判断队列为空
    public boolean isEmpty(){
        return rear == front;
    }


    //添加数据到队列
    public void addQueue(int n){
        //判断是否满
        if (isFull()){
            System.out.println("队列已满");
            return;
        }
        //直接将数据加入
        arr[rear]=n;
        rear=(rear+1)%maxSize;
    }

    //出队
    public int getQueue(){
        //判断是否为空
        if (isEmpty()){
            throw new RuntimeException("队列空，不能取数据");
        }
        // front 保存临时变量 然后后移 考虑取模 在将保存临时变量 返回
        int value = arr[front];
        front = (front+1)%maxSize;
        return value;
    }
    //显示队列所有数据
    public void showQueue(){
        //遍历
        if (isEmpty()){
            System.out.println("为空");
            return;
        }
        //先从front 遍历

        for (int i = front; i < front+size(); i++) {
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
        }
    }

    //求出当前队列有效数据个数
    public int size(){
        return (rear+maxSize-front)%maxSize;
    }
    //显示队列头数据不是取数据
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("没有数据哦");
        }
        return arr[front];
    }
}