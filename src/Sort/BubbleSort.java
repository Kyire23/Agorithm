package Sort;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int arr[] = {3,9,-1,10,20};
        System.out.println("排序前");
        System.out.println(Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println("排序后");
        System.out.println(Arrays.toString(arr));

        /*int temp = 0;
        boolean flag = false;//标识变量 表示是否交换
        for (int j = 0; j < arr.length-1; j++) {
            //如果前面数比后面数大 则交换
            for (int i = 0; i < arr.length-1-j; i++) {
                if (arr[i]>arr[i+1]){
                    flag = true;
                    temp = arr[i];
                    arr[i]=arr[i+1];
                    arr[i+1]=temp;
                    }
                }
            System.out.println("第"+(j+1)+"次排序结果");
            System.out.println(Arrays.toString(arr));
            if (!flag){
                //在排序中 一次都没发生
                break;
            }else {
                flag = false;//重置 进行下次判断
            }
        }*/

        //进一步优化
    }
    public static void bubbleSort(int arr[]){
        int temp = 0;
        boolean flag = false;//标识变量 表示是否交换
        for (int j = 0; j < arr.length-1; j++) {
            //如果前面数比后面数大 则交换
            for (int i = 0; i < arr.length-1-j; i++) {
                if (arr[i]>arr[i+1]){
                    flag = true;
                    temp = arr[i];
                    arr[i]=arr[i+1];
                    arr[i+1]=temp;
                }
            }
            //System.out.println("第"+(j+1)+"次排序结果");
            //System.out.println(Arrays.toString(arr));
            if (!flag){
                //在排序中 一次都没发生
                break;
            }else {
                flag = false;//重置 进行下次判断
            }
        }
    }
}
