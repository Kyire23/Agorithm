package LinkList;
//链表是以单结点存储 各个节点不一定连续存储 分头结点和没有头结点
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");
        HeroNode hero4 = new HeroNode(4,"林冲","豹子头");
        //创建链表
        SingleLinkedList ls = new SingleLinkedList();
//        ls.add(hero1);
//        ls.add(hero2);
//        ls.add(hero3);
//        ls.add(hero4);
        ls.addByOrder(hero1);
        ls.addByOrder(hero4);
        ls.addByOrder(hero2);
        ls.addByOrder(hero3);

        ls.list();
    }


}

//创建单链表 管理英雄
class SingleLinkedList{
    //先初始化一个头结点 不能动 不存放具体数据 需要辅助变量
    private HeroNode head =new HeroNode(0,"","");

    //添加节点到单项列表 找到最后节点将最后节点的next指向新节点

    public void add(HeroNode heroNode){
        HeroNode temp = head;
        //遍历列表到最后
        while (true){
            if (temp.next==null){
                break;
            }
            temp = temp.next;
        }
        //当退出循环时 temp 指向链表的最后
        temp.next = heroNode;
    }

    public void addByOrder(HeroNode heroNode){
        HeroNode temp = head;
        boolean flag = false;
        while (true){
            if (temp.next==null){
                break;
            }
            if (temp.next.no >heroNode.no){
                break;
            } else if (temp.next.no==heroNode.no) {
                flag = true;
                break;
            }
            temp =temp.next;

            }if (false){
            System.out.println("不能加入");
        }else {
            heroNode.next=temp.next;
            temp.next=heroNode;
        }

    }

    //根据编号修改
    public void update(HeroNode newHeroNode){
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        boolean flag =false;
        while (true){
            if (temp==null){
                break;
            }
            if (temp.no== newHeroNode.no){
                flag=true;
                break;
            }temp=temp.next;
        }
        if (flag){
            temp.name= newHeroNode.name;
            temp.nickName=newHeroNode.nickName;
        }else {
            System.out.println("编号不存在");
        }
    }


    //删除
    public void del(int no) {
        HeroNode temp = head;
        boolean flag =false;
        while (true){
            if (temp.next==null){
                break;
            }if (temp.next.no==no){
                flag=true;
                break;
            }temp=temp.next;
        }
        if (flag){
            temp.next=temp.next.next;
        }else {
            System.out.println("删除节点不存在");
        }
    }
    //显示链表 遍历
    public void list(){
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        while (true){
            if (temp==null){
                break;
            }
            //输出节点信息
            System.out.println(temp);
            temp = temp.next;
        }
    }

}

class HeroNode{
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;//指向下个节点
    //构造器
    public HeroNode(int no, String name,String nickname){
        this.no=no;
        this.name=name;
        this.nickName=nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\''  +
                '}';
    }

}
