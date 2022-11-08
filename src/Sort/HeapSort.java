package Sort;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9};
        /*adjustHeap(arr,1,arr.length);
        System.out.println("第一次"+ Arrays.toString(arr));*/
        //arr.length / 2-1 共有几个非叶子节点
        heapSort(arr);

    }

    public static void heapSort(int arr[]) {
        int temp = 0;
        System.out.println("堆排序");
        for (int i = arr.length / 2-1; i >=0 ; i--) {
            adjustHeap(arr,i,arr.length);
        }
        //反复执行做交换
        for (int j = arr.length-1; j >0 ; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] =temp;
            adjustHeap(arr,0,j);
        }
        System.out.println("堆排序后"+ Arrays.toString(arr));
    }

    //将一个数组（二叉树）调整成一个大顶堆
    //arr 待调整的数组 i 表示非叶子节点在数组中的索引 length 表示对多少个元素继续调整 length是逐渐减少
    public static void adjustHeap(int arr[], int i, int length) {
        int temp = arr[i];//先取出当前元素的值 保存在临时变量
        // k = i*2+1 k是i的左子节点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {//左子节点小于右子节点的值
                k++;
            }
            if (arr[k] > temp) {
                //如果子节点大于父节点
                arr[i] = arr[k];//把较大的值赋给当前节点
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;//将temp值放到调整后的位置


    }
}

