public class Demo {
    int binarySearch(int[] array, int l, int r, int target) {
       if (l > r)
           return -1;
       int mid = l + (r - l) / 2 ;
       int num = array[mid];
       if (num == target)
           return mid;
       else if (num < target)
           return binarySearch(array, mid + 1, r, target);
       else
           return binarySearch(array, l, r - 1, target);
    }
    public static void main(String args[]) {

    }
}
