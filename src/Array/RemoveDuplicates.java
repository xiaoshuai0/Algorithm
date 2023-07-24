package Array;

public class RemoveDuplicates {

    public int removeDuplicates(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int start = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[start] != nums[i]) {
                start++;
                nums[start] = nums[i];
            }
        }
        return start + 1;
    }

    public static void main(String[] args) {
        RemoveDuplicates r = new RemoveDuplicates();
        System.out.println(r.removeDuplicates(new int[]{1,1,2}));
    }
}
