package Search;

import java.util.Arrays;

public class InsertValueSearch {
    public static void main(String[] args) {
        int []arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i+1;
        }
        int index = insertValueSearch(arr,0,arr.length-1,100);
        System.out.println("index="+index);
        //System.out.println(Arrays.toString(arr));
    }

    //插值查找算法 归一化 拉格朗日查找 必须有序
    public static int insertValueSearch(int[]arr,int left,int right,int findVal){
        if (left>right || findVal<arr[0] || findVal>arr[arr.length-1]){
            return -1;
        }
        //求出mid 公式
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal){
            return insertValueSearch(arr,mid+1,right,findVal);
        }else if (findVal < midVal){
            return insertValueSearch(arr,left,mid-1,findVal);
        }else {
            return mid;
        }
    }

}
