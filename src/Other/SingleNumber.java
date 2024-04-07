package Other;

import java.util.Arrays;

public class SingleNumber {

    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num: nums) {
            res = (res ^ num);
        }
        return res;
    }
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        int middle = nums.length / 2;
        return nums[middle];
    }

    public void sortColors(int[] nums) {
//        int n = nums.length;
//        int ptr = 0;
//        for (int i = 0; i < n; i++) {
//            if (nums[i] == 0) {
//                swap(nums, i, ptr);
//                ptr++;
//            }
//        }
//
//        for (int i = ptr; i < n; i++) {
//            if (nums[i] == 1) {
//                swap(nums, i, ptr);
//                ptr++;
//            }
//        }
        int n = nums.length;
        int ptr_0 = 0, ptr_1 = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                swap(nums, i, ptr_1);
                ptr_1++;
            } else if (nums[i] == 0) {
                swap(nums, i, ptr_0);
                if (ptr_0 < ptr_1) {
                    swap(nums, i, ptr_1);
                }
                ptr_0++;
                ptr_1++;
            }
        }
    }


    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public static void main(String[] args) {
        SingleNumber singleNumber = new SingleNumber();
        System.out.println(singleNumber.singleNumber(new int[]{2, 2, 1}));
        System.out.println(singleNumber.singleNumber(new int[]{4,1,2,1,2}));
        int meter = 10;
        float x = 0;
        int day = 0;
        while (meter > x) {
            day += 1;
            x += 1;
            x = (x / meter) * (meter + 10);
            meter += 10;
            if (meter <= x) {
                System.out.println(day);
            }
        }
    }
}
