import java.util.Scanner;

public class St01 {

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int a = sc.nextInt();
//        if (a<0||(a!=0&&a%10==0)) {
//            System.out.println("这个不是回文数");
//        }
//        int res = 0;
//        while (a>res) {
//            res = res*10+a%10;
//            a=a/10;
//        }
//
//        if (a==res||a==res/10) {
//            System.out.println("这是一个回文数");
//        }else {
//            System.out.println("这个不是回文数");
//        }
        int [] arr = {1,4,3};
        System.out.println(arr[arr.length-1]++);
        Solution solution =new Solution();
        solution.plusOne(arr);
    }
}

class Solution {
    public int[] plusOne(int[] digits) {
        for(int i = digits.length-1;i>=0;--i){//2
            digits[i]++;//0
            digits[i] %= 10;//3
            if(digits[i]!=0){
                return digits;
            }
        }
        //所有元素都为9的情况
        digits = new int[digits.length+1];
        digits[0]=1;
        return digits;
    }
}