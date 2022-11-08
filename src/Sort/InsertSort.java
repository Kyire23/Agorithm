package Sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};
        insertSort(arr);
    }

    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];
            int insertIndex = i - 1;
            //insertIndex >=0 防止越界
            //insertVal<arr[insertIndex 带插入数还未找到
            //需要将arr[insertIndex]后移
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //当退出while时 插入位置找到 insertIndex+1
            arr[insertIndex + 1] = insertVal;
            System.out.println("第" + i + "次排序结果");
            System.out.println(Arrays.toString(arr));
        }
    }
}
