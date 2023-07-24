package BinarySeaerch;

public class BinarySearch {

    static public void main(String[] args) {
        BinarySearch s = new BinarySearch();
        int[] nums = new int[] {5,7,7,8,8,10};
        System.out.println(s.binarySearch(nums, 9));
        System.out.println(s.searchRange(nums, 9));
    }


    int binarySearch(int[] nums, int target) {
        // 一左一右两个指针相向而行
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public int[] searchRange(int[] nums, int target) {
        return new int[] {left_bound(nums, target), right_bound(nums, target)};
    }

    int left_bound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (left == nums.length) return -1;
        return nums[left] == target ? left : -1;
    }
    int right_bound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (left - 1 < 0) return -1;
        return nums[left - 1] == target ? left - 1 : -1;
    }
}
