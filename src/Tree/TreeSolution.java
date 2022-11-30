package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeSolution {
    int res;
    int maxDepth(TreeNode root) {
        travese(root, 0);
        return res;
    }
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        int min_depth = Integer.MAX_VALUE;
        if (root.left != null)
            min_depth = Math.min(minDepth(root.left),  min_depth);

        if (root.right != null)
            min_depth = Math.min(minDepth(root.right), min_depth);

        return min_depth + 1;
    }

    int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        // 利用定义，计算左右子树的最大深度
        int leftMax = maxDepth2(root.left);
        int rightMax = maxDepth2(root.right);
        // 整棵树的最大深度等于左右子树的最大深度取最大值，
        // 然后再加上根节点自己
        int res = Math.max(leftMax, rightMax) + 1;
        return res;
    }
    void travese(TreeNode root, int depth) {
        if (root == null) return;
        // 前序位置
        depth++;
        if (root.left == null && root.right == null) {
            // 到达叶子节点，更新最大深度
            res = Math.max(depth, res);
        }
        travese(root.left, depth);
        travese(root.right, depth);
    }

    int maxDiameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
         _helper(root);
         return maxDiameter;
    }

    public int _helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftMax = _helper(root.left);
        int rightMax = _helper(root.right);
        // 后序位置，顺便计算最大直径
        int myDiameter = leftMax + rightMax;
        maxDiameter = Math.max(maxDiameter, myDiameter);

        return 1 + Math.max(leftMax, rightMax);
    }


    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (root == null) {
            return ret;
        }
        /// 用于存储节点信息
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<Integer>();
            // 一层的节点数
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

    List<List<Integer>> list = new ArrayList<>();
    public List<List<Integer>> levelOrder2(TreeNode root) {
        if (root == null) return list;
        levelOrder_helper(root, 0);
        return list;
    }

    void levelOrder_helper(TreeNode root, int depth) {
        if (root == null) return;
        // 前序位置，看看是否已经存储 depth 层的节点了
        if (list.size() <= depth) {
            // 第一次进入 depth 层
            list.add(new LinkedList<Integer>());
        }
        // 前序位置，在depth位置存储 depth 层节点
        list.get(depth).add(root.val);
        levelOrder_helper(root.left, depth + 1);
        levelOrder_helper(root.right, depth + 1);
    }
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (root == null) {
            return ret;
        }
        /// 用于存储节点信息
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<Integer>();
            // 一层的节点数
            int currentSize = queue.size();
            for (int i = 0; i < currentSize; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            ret.add(0, level);
        }
        return ret;
    }
}
