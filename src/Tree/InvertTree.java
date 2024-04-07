package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class InvertTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        invertTree(root.left);
        invertTree(root.right);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        return root;
    }

    public boolean isSymmetric(TreeNode root) {
        return check(root, root);
    }

    public boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }

    int maxDiameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        diameterOfBinaryTree_helper(root);
        return maxDiameter;
    }
    public int diameterOfBinaryTree_helper(TreeNode root) {
        if (root == null) return 0;
        int leftMax = diameterOfBinaryTree(root.left);
        int rightMax = diameterOfBinaryTree(root.right);
        maxDiameter = Math.max(maxDiameter, leftMax + rightMax);
        return 1 + Math.max(leftMax, rightMax);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (root == null) {
            return ret;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int currentSize = queue.size();
            for (int i = 0; i < currentSize; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            ret.add(level);
        }
        return ret;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST_helper(nums, 0, nums.length - 1);
    }

    public TreeNode sortedArrayToBST_helper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = left + ((right - left) >> 1);
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST_helper(nums, left, mid - 1);
        root.right = sortedArrayToBST_helper(nums, mid + 1, right);
        return root;
    }
}
