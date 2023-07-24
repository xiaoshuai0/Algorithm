package Array;

public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        if (nums.length < 2) return;
        int left = 0, right = 0, length = nums.length;
        while (right < length) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left ++;
            }
            right ++;
        }
        System.out.println(nums);
    }
    public void swap(int[] nums, int left, int right) {
        if (left == right) return;
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    public static void main(String[] args) {
        MoveZeroes m = new MoveZeroes();
        m.moveZeroes(new int[]{0,1,0,3,12});
        System.out.println();
    }
}
