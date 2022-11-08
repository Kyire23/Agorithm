package HashTab;

import java.util.Scanner;

public class HashTabDemo {
    public static void main(String[] args) {
        //创建一个哈希表
        HashTab hashTab = new HashTab(7);

        String key = "";

        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("del：删除雇员");
            System.out.println("exit: 退出系统");
            key = scanner.next();
            switch (key){
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    Emp emp = new Emp(id,name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "del":
                    System.out.println("请输入要删除的id");
                    id = scanner.nextInt();
                    hashTab.delEmp(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}


//创建哈希表 管理多条链表
class HashTab{
    private EmpLinkedList[] empLinkedListArray;
    private int size;//表示有多少条链表

    //构造器
    public HashTab(int size){
        this.size = size;
       empLinkedListArray=new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //添加员工
    public void add(Emp emp){
        //根据员工id 得到员工应该添加到哪条链表
        int empLinkedListNO = hashFun(emp.id);
        //将emp 添加到对应链表中
        empLinkedListArray[empLinkedListNO].add(emp);
    }

    //查找
    public void findEmpById(int id){
        //使用散列函数确定哪条链表查找
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNO].findEmpById(id);
        if (emp != null){
            //找到
            System.out.printf("在第%d条链表中找到 雇员 id = id = %d\n",(empLinkedListNO+1),id);
        }else {
            System.out.println("未找到");
        }
    }

    //遍历所有的链表 数组加链表==》哈希表
    public void list(){
        for (int i = 0;i<size;i++){
            empLinkedListArray[i].list(i);
        }
    }

    public void delEmp(int id){
        int empLinkedListNo = hashFun(id);
        empLinkedListArray[empLinkedListNo].del(id);
    }

    //编写散列函数 取模法
    public int hashFun(int id){
        return id % size;
    }
}


class Emp{
    public int id;
    public String name;
    public Emp next;//next 默认为空

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}

//创建EmpLinkedList,表示链表

class EmpLinkedList{
    //头指针执行第一个Emp  链表的head 指向第一个Emp
    private Emp head;//默认null

    //添加雇员到链表 假定添加雇员 到链表最后
    public void add(Emp emp){
        if (head == null){
            head = emp;
            return;
        }
        //如果不是第一个雇员 借助辅助指针
        Emp curEmp = head;
        while (true){
        if (curEmp.next == null){
            break;//链表到最后
        }
        curEmp = curEmp.next;//后移
        }
        //退出时直接将emp 加入链表
        curEmp.next=emp;
    }

    //遍历链表
    public void list(int no){
        if (head == null){
            System.out.println("第"+(no+1)+"链表为空");
            return;
        }
        System.out.print("第"+(no+1)+"链表信息为:");
        Emp curEmp = head;//辅助指针
        while (true){
            System.out.printf("=> id=%d name=%s\t",curEmp.id,curEmp.name);
            if (curEmp.next == null){
                break;
            }
            curEmp = curEmp.next;//后移遍历
        }
        System.out.println();
    }

    //根据id查找雇员 找到返回Emp 没有 返回null
    public Emp findEmpById(int id){
        //先判断链表是否为空
        if (head == null){
            System.out.println("链表为空");
            return null;
        }
        //辅助指针
        Emp curEmp = head;
        while (true){
            if (curEmp.id == id){
                break;//找到
            }
            if (curEmp.next == null){
                //说明遍历当前链表没有找到该员工
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;//后移
        }
        return curEmp;
    }

    //删除输入的id对应的雇员信息
    public void del(int id){
        if(head==null){
            System.out.println("链表为空，无法删除");
            return;
        }
        if (head.id==id){
            if(head.next==null){
                head=null;
            }else {
                head = head.next;
            }
            return;
        }
        Emp curEmp = head;
        boolean flag = false;
        while (true){
            if(curEmp.next==null){
                break;
            }
            if (curEmp.next.id==id){
                flag=true;
                break;
            }
            curEmp = curEmp.next;
        }
        if (flag){
            curEmp.next = curEmp.next.next;
        }else {
            System.out.println("要删除的节点不存在");
        }
    }
}