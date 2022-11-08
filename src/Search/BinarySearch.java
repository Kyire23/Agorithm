package Search;
//二分查找前提 数组必须是有序的
public class BinarySearch {
    public static void main(String[] args) {
        int arr[] = {1,8,10,89,1000,1234};
//        int resIndex = binarySearch(arr,0,arr.length-1,1);
//        System.out.println("resIndex="+resIndex);
        int resIndex2 = binarySearch2(arr,1);
        System.out.println("resIndex2="+resIndex2);
    }

    public static int binarySearch(int[]arr,int left,int right,int findVal){
        //结束递归 防止死循环
        if (left>right){
            return -1;
        }
        int mid = (left+right)/2;
        int midVal = arr[mid];

        if (findVal>midVal){
            return binarySearch(arr,mid+1,right,findVal);
        }else if (findVal<midVal){
            return binarySearch(arr,left,mid-1,findVal);
        }else {
            return mid;
        }
    }
    //迭代法
    public static int binarySearch2(int []arr,int key){
        int low = 0;
        int high = arr.length-1;
        int mid = (low+high)/2;
        while (arr[mid]!=key){
            if (arr[mid]>key){
                high=mid-1;
            }else if (arr[mid]<key){
                low=mid+1;
            }
            if ((low>high)){
                return -1;
            }
            mid=(low+high)/2;
        }
        return mid;
    }
}
