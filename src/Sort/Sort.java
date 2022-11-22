package Sort;

public class Sort {

    /**
    * 冒泡排序
    * 原地排序，稳定排序算法，最好是O(n) 最坏是O(n^2)
    * */
    public void bubbleSort(int[] nums) {
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
    }

    /**
     * 插入排序
     * 原地排序，稳定排序算法，最好是O(n) 最坏是O(n^2)
     * */
    public void insertSort(int[] nums) {
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
    }

    public void selectionSort(int[] nums) {
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
    }


}
