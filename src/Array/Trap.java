package Array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Trap {

    public int trap(int[] height) {
        int ans = 0;
        int max = getMax(height);
        for (int i = 1; i <= max; i++) {
            boolean isStart = false;
            int temp = 0;
            for (int j = 0; j < height.length; j++) {
                if (isStart && height[j] < i) {
                    temp++;
                }
                if (height[j] >= i) {
                    ans += temp;
                    temp = 0;
                    isStart = true;
                }
            }
        }
        return ans;
    }

    public int trap2(int[] height) {
        int ans = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int max_left = 0;
            for (int j = i - 1; j >= 0 ; j--) {
                if (height[j] > max_left) {
                    max_left = height[j];
                }
            }
            int max_right = 0;
            for (int j = i + 1; j < height.length; j++) {
                if (height[j] > max_right) {
                    max_right = height[j];
                }
            }
            int min = Math.min(max_left, max_right);
            if (min > height[i]) {
                ans = ans + (min - height[i]);
            }
        }
        return ans;
    }
    public int trap3(int[] height) {
        int sum = 0;
        int[] max_left = new int[height.length];
        int[] max_right = new int[height.length];
        for (int i = 1; i < height.length - 1; i++) {
            max_left[i] = Math.max(max_left[i - 1], height[i - 1]);
        }
        for (int i = height.length - 2; i >= 0; i--) {
            max_right[i] = Math.max(max_right[i + 1], height[i + 1]);
        }
        for (int i = 1; i < height.length - 1; i++) {
            int min = Math.min(max_left[i], max_right[i]);
            if (min > height[i]) {
                sum = sum + (min - height[i]);
            }
        }
        return sum;
    }

    public int trap4(int[] height) {
        int sum = 0;
        int max_left = 0;
        int[] max_right = new int[height.length];

        for (int i = height.length - 2; i >= 0; i--) {
            max_right[i] = Math.max(max_right[i + 1], height[i + 1]);
        }
        for (int i = 1; i < height.length - 1; i++) {
            max_left = Math.max(max_left, height[i - 1]);
            int min = Math.min(max_left, max_right[i]);
            if (min > height[i]) {
                sum = sum + (min - height[i]);
            }
        }
        return sum;
    }
    private int getMax(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            max = max < height[i] ? height[i] : max;
        }
        return max;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new LinkedList();
        int row = matrix.length;
        int cloumn = matrix[0].length;
        int up_bounds = 0, bottom_bounds = row - 1;
        int left_bounds = 0, right_bounds = cloumn - 1;
        while (ans.size() < row * cloumn) {
            if (up_bounds <= bottom_bounds) {
                for (int i = left_bounds; i <= right_bounds; i++) {
                    ans.add(matrix[up_bounds][i]);
                }
                up_bounds++;
            }
            if (left_bounds <= right_bounds) {
                for (int i = up_bounds; i <= bottom_bounds; i++) {
                    ans.add(matrix[i][right_bounds]);
                }
                right_bounds--;
            }
            if (up_bounds <= bottom_bounds) {
                for (int i = right_bounds; i >= left_bounds; i--) {
                    ans.add(matrix[bottom_bounds][i]);
                }
                bottom_bounds--;
            }
            if (left_bounds <= right_bounds) {
                for (int i = bottom_bounds; i >= up_bounds; i--) {
                    ans.add(matrix[i][left_bounds]);
                }
                left_bounds++;
            }
        }
        return ans;
    }

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 先沿对角线镜像对称矩阵
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        // 翻转每一行
        for (int[] arr: matrix) {
            reserse(arr);
        }
    }

    public void reserse(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = col[j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int row = 0, cloumn = n - 1;

        while (row < m && cloumn >= 0) {
            int num = matrix[row][cloumn];
            if (num == target) {
                return true;
            } else if (num > target) {
                cloumn--;
            } else {
                row++;
            }
        }
        return false;
    }
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 0; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new LinkedList<>();
            for (int j = 0; j <= i ; j++) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(ans.get(i - 1).get(j - 1) + ans.get(i - 1).get(j));
                }
            }
            ans.add(row);
        }
        return ans;
    }
    public static void main(String[] args) {
        Trap t = new Trap();
        System.out.println(t.trap2(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        for (Integer num:
                t.spiralOrder(new int[][]{{1,2,3}, {4,5,6}, {7,8,9}})) {
            System.out.println(num);
        };
        System.out.println(t.searchMatrix(new int[][]{{1,4,7,11,15}, {2,5,8,12,19},{3,6,9,16,22}, {10,13,14,17,24}, {18,21,23,26,30}}, 5));
    }
}
