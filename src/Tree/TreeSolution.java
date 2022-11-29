package Tree;

public class TreeSolution {
    int res;
    int maxDepth(TreeNode root) {
        travese(root, 0);
        return res;
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
}
