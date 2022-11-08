package Sort;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int arr[] = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort(arr);
        shellSort2(arr);
    }

    //1.交换法
    public static void shellSort(int arr[]) {
        int temp = 0;
        int count = 0;
        //第一轮排序 10组 分5组
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = 0; i < arr.length; i++) {
                //遍历各组中所有元素 步长5
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            System.out.println("希尔排序第" + (++count) + "轮" + Arrays.toString(arr));
        }

    }

    //2.移位法
    public static void shellSort2(int arr[]) {
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素 逐个对其所在组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    arr[j] = temp;
                }

            }
        }
        System.out.println("希尔排序第" + (++count) + "轮" + Arrays.toString(arr));
    }


}


