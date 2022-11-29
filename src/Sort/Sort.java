package Sort;

import java.util.Arrays;
import java.util.Random;

public class Sort {

    /**
    * 冒泡排序
    * 原地排序，稳定排序算法，最好是O(n) 最坏是O(n^2)
    * */
    public int[] bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            /// 提前退出冒泡循环标志
            boolean flag = false;
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {/// 交换
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                    flag = true;// 表示有数据交换
                }
            }
            if (!flag) break; // 没有数据交换直接退出
        }
        return nums;
    }

    /**
     * 插入排序
     * 原地排序，稳定排序算法，最好是O(n) 最坏是O(n^2)
     * */
    public int[] insertSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            int j = i - 1;
            // 查找插入位置
            for (; j >= 0; j--) {
                if (nums[j] > value) {
                    nums[j+1] = nums[j]; // 数据移动
                } else {
                    break;
                }
            }
            nums[j+1] = value;// 插入数据
        }
        return nums;
    }

    public int[] selectionSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = nums[minIndex];
            nums[minIndex] = nums[i];
            nums[i] = temp;
        }
        return nums;
    }

    public int[] shellSort(int[] nums) {
        int len = nums.length;
        int gap = len / 2;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                int temp = nums[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && nums[preIndex] > temp) {
                    nums[preIndex + gap] = nums[preIndex];
                    preIndex -= gap;
                }
                nums[preIndex + gap] = temp;
            }
            gap /= 2;
        }
        return nums;
    }

    public int[] mergeSort(int[] nums) {
        if (nums.length < 2) return nums;
        int mid = nums.length / 2;
        int[] left = Arrays.copyOfRange(nums, 0, mid);
        int[] right = Arrays.copyOfRange(nums, mid, nums.length);
        return _merge(mergeSort(left), mergeSort(right));
    }
    public int[] _merge(int[] left, int[] right) {
        int[] res = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < res.length; index++) {
            if (i >= left.length) {
                res[index] = right[j++];
            } else if (j >= right.length) {
                res[index] = left[i++];
            } else if (left[i] > right[j]) {
                res[index] = right[j++];
            } else {
                res[index] = left[i++];
            }
        }
        return res;
    }

    public int[] quickSort(int[] nums) {
        if (nums.length <= 1) return nums;
        return quickSortHelper(nums, 0, nums.length - 1);
    }

    public int[] quickSortHelper(int[] nums, int left, int right) {
        if (left > right) {
            return nums;
        }
        int smallIndex = partition(nums, left, right);
        if (smallIndex > left) {
            quickSortHelper(nums, left, smallIndex - 1);
        }

        if (smallIndex < right) {
            quickSortHelper(nums, smallIndex + 1, smallIndex);
        }
        return nums;
    }
    public int partition(int[] array, int start, int end) {
        int pivot = (int) (start + Math.random() * (end - start + 1));
        int smallIndex = start - 1;
        swap(array, pivot, end);
        for (int i = start; i <= end; i++)
            if (array[i] <= array[end]) {
                smallIndex++;
                if (i > smallIndex)
                    swap(array, i, smallIndex);
            }
        return smallIndex;
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    void printArray(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
            System.out.print(", ");
        }
        System.out.println();
    }
    static public void main(String[] args) {
        Sort s = new Sort();
        int[] nums = new int[]{3, 6, 12, 5, 4, 1};
        s.printArray(s.bubbleSort(nums));
        s.printArray(s.insertSort(nums));
        s.printArray(s.selectionSort(nums));
        s.printArray(s.mergeSort(nums));
        s.printArray(s.quickSort(nums));
    }
}
