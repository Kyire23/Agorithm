package Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class MergeSort {
    static int count = 0;
    public static void main(String[] args) {
        int[] arr= {8,4,5,7,1,3,6,2};
        int[] temp = new int[arr.length];
        mergesort(arr,0, arr.length-1,temp);
        System.out.println(Arrays.toString(arr));
        System.out.println(count);
        /*int[] arr = new int[800000];
        int[] temp = new int[arr.length];
        for (int i = 0; i < 800000;i++){
            arr[i] = (int)(Math.random() * 800000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSSS");
        String s1 = simpleDateFormat.format(date1);
        System.out.println("排序前：" + s1);
        mergesort(arr,0, arr.length-1,temp);
        Date date2 = new Date();
        String s2 = simpleDateFormat.format(date2);
        System.out.println("排序后：" + s2);
    */}
    public static void mergesort(int[] arr,int left,int right,int[] temp) {
        if(left<right){
            int mid = (left + right) /2;
            //向左递归进行分解
            mergesort(arr,left,mid,temp);
            //向右递归分解
            mergesort(arr,mid+1,right,temp);
            //每分解一次就合并一次
            merge(arr,left,mid,right,temp);
        }

    }
    //合并的方法
    /**
     *
     * @param arr 原始数组
     * @param left 左边序列的初始索引
     * @param mid 中间索引
     * @param right 右边索引
     * @param temp 做中转的数组
     */
    public static void merge(int[] arr ,int left , int mid,int right,int[] temp){
        int i = left;//初始化i，左边有序序列的初始索引
        int j = mid +1;//初始化j，右边有序序列的初始索引
        int index  = 0; //中间数组的索引
        count ++;

        //先把左右两边的数据，按规则拷贝到temp中
        //直到左右两边有一侧处理完毕为止
        while( i <= mid && j <= right){
            if(arr[i] <=  arr[j]){
                temp[index] = arr[i];
                index ++;
                i ++;
            } else {
                temp[index] = arr[j];
                index ++;
                j ++;
            }
        }
        //把有剩余数据的一边，剩余的数据一次放入temp中
        while(i <= mid){
            temp[index] = arr[i];
            index ++;
            i ++;
        }
        while( j <= right){
            temp[index] = arr[j];
            index ++;
            j++;
        }

        //将temp数组中的原数放入temp中
        index = 0; //将索引置0
        int tempLeft = left;
        while(tempLeft <= right){
            arr[tempLeft] = temp[index];
            index ++;
            tempLeft ++;
        }
    }
}


