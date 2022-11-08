package Sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -256, 70};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("arr=" + Arrays.toString(arr));
    }

    public static void quickSort(int arr[], int left, int right) {
        int l = left;//左下标
        int r = right;//右下标
        //中轴下标
        int pivot = arr[(left + right) / 2];
        int temp = 0;
        while (l < r) {
            while (arr[l] < pivot) {
                l += 1;
            }
            while (arr[r] > pivot) {
                r -= 1;
            }
            //pivot 左右两边值 已经找到
            if (l >= r) {
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完后 发现arr[l] == pivot值相等 -- 后移
            if (arr[l] == pivot) {
                r -= 1;
            }
            //如果交换完后 发现arr[r] == pivot值相等 -- 后移
            if (arr[r] == pivot) {
                l += 1;
            }
        }
            //如果 l==r 必须l++,r-- 否则栈溢出
        if (l==r){
            l+=1;
            r-=1;
        }
        //向左递归
        if (left<r){
            quickSort(arr,left,r);
        }
        if (right>l){
            quickSort(arr,l,right);
        }

    }
}
