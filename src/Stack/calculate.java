package Stack;
//计算 3+2*6-2
import java.util.Scanner;

public class calculate {
    public static void main(String[] args) {
        //输入表达式
        String expression = "7-2-2*5-1";
        //创建两个栈，数栈和符号栈
        ArrayStack01 numStack = new ArrayStack01(10);
        ArrayStack01 operStack = new ArrayStack01(10);
        //定义需要的相关变量
        int index = 0;//扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' '; //将每次扫描得到的char保存到ch中
        String keepNum = "";//用于拼接多位数
        //开始while循环的扫描expression
        while (true) {
            //依次得到expression的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch是什么，然后做相应的处理
            if (operStack.isOper(ch)) {
                //如果是运算符
                //判断符号栈是否为空
                if (!operStack.isEmpty()) {
                    //不为空进行处理
                    //1. 如果当前的操作符的优先级<=栈中的操作符：就需要从数栈中pop出两个数，
                    //   再从符号栈中pop出一个符号，进行运算，将结果入数栈，最后将当前的操作符入栈
                    //2. 如果当前的操作符优先级>栈中的操作符：直接将操作符入栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        //如果满足，就从数栈pop出两个数
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //把运算结果入数栈
                        numStack.push(res);
                        //把运算符入符号栈
                        operStack.push(ch);
                    } else {
                        //如果符号>符号栈中的符号,直接输入
                        operStack.push(ch);
                    }
                } else {
                    //为空直接入符号栈
                    operStack.push(ch);
                }
            } else {
                //如果是数，则直接入数栈
                //将char变成int
                //numStack.push(ch - 48);
                //当处理多位数的时。不能发现是一个数就立刻入栈，因为可能是多位置数字
                //所以需要想expresssion的表达式的index后再看一位，如果是数就继续扫描，是符号就入栈
                //所以我们要定义一个字符串变量，用于拼接

                //处理多位数
                keepNum += ch;
                //如果ch已经是expression的最后一位，就直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //判断下一位是不是数字，如果是数字，就继续扫描,如果是运算符，就入栈
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        //是操作符
                        numStack.push(Integer.parseInt(keepNum));
                        //还原keepNum
                        keepNum = "";
                    }
                }

            }
            //让index+1，并判断是否扫描到expression最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        //当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号并运算
        while (true) {
            //如果符号栈为空，则计算到了最后的结果，数栈中只有一个数字
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);//入栈
        }
        System.out.printf("表达式%s = %d", expression, numStack.pop());
    }
}

class ArrayStack01 {
    private int maxSize;
    //数组模拟栈，数据放在数组中
    private int stack[];
    //栈顶，初始化为-1
    private int top = -1;

    public ArrayStack01(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        //判断栈是否满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            //因为有返回值，所以我们可以抛出异常
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }


    //返回运算符的优先级，优先级时程序员来定，优先级使用数字表示
    //数字越大，则优先级越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            //假设目前的表达式只有+，-，*，/
            return -1;
        }
    }

    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        //res存放计算结果
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

    //返回当前栈顶的值
    public int peek() {
        return stack[top];
    }
}
