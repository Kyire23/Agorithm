package DataStructures;

public class JosePfu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(50);
        circleSingleLinkedList.showBoy();
    }
}

//创建一个环形的单向链表
class CircleSingleLinkedList{
    private Boy first = new Boy(-1);
    public void addBoy(int num){
        if (num<1){
            System.out.println("num 不正确");
            return;
        }
        Boy curBoy = null; //辅助指针
        for (int i=1;i<=num;i++){
            //根据编号创建小孩节点
            Boy boy= new Boy(i);
            //第一个小孩特别处理
            if (i==1){
                first = boy;
                first.setNext(first);//构成环
            }else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy =boy;
            }

        }
    }
    //遍历当前环形列表
    public void showBoy(){
        //链表是否为空
        if (first==null){
            System.out.println("无内容");
            return;
        }
        Boy curBoy = first;
        while (true){
            System.out.printf("小孩的编号%d\n",curBoy.getNo());
            if (curBoy.getNext()==first){
                break;//遍历完毕
            }
            curBoy = curBoy.getNext();//curBoy后移
        }
    }

}
class Boy{
    private int no;//编号
    private Boy next;//指向下一个节点 默认为null
    public Boy(int no){
        this.no=no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}