package Queue;

import java.util.Scanner;
//改进-》环形队列 队满: (rear+1)%maxSize=front;  为空: rear==front front,rear初始值为0
public class ArrayQueue {
    //使用数组模拟队列
    public static void main(String[] args) {
        //测试
        ArrayQue queue = new ArrayQue(3);
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

class ArrayQue{
    private int maxSize;//数组最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;//存放数据 模拟队列

    //创建队列的构造器
    public ArrayQue(int arrMAxSize){
        maxSize = arrMAxSize;
        arr = new int[maxSize];
        front = -1;//指向队列头部的前一个位置
        rear = -1;//指向队列尾的具体位置
    }

    //判断队列是否满
    public boolean isFull(){
        return rear == maxSize-1;
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
        rear++;//让rear 后移
        arr[rear]=n;
    }

    //出队
    public int getQueue(){
        //判断是否为空
        if (isEmpty()){
          throw new RuntimeException("队列空，不能取数据");
        }
        front++;
        return arr[front];
    }
    //显示队列所有数据
    public void showQueue(){
        //遍历
        if (isEmpty()){
            System.out.println("为空");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }
    //显示队列头数据不是取数据
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("没有数据哦");
        }
        return arr[front+1];
    }
}