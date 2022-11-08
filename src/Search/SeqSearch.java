package Search;

public class SeqSearch {
    public static void main(String[] args) {
        int arr[] = {-1,34,9,89,1,45};
        int index = seqSearch(arr,1);
        if (index == -1){
            System.out.println("没有找到");
        }else {
            System.out.println("找到，下标为="+index);
        }
    }

    public static int seqSearch(int[] arr,int value){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value){
                return i;
            }
        }
        return -1;
    }
}
