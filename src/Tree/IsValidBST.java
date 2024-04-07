package Tree;
import java.util.*;

public class IsValidBST {

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
    }

    public boolean _isValidBST(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        long inorder = Long.MIN_VALUE;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();

            if (root.val <= inorder) {
                return false;
            }

            inorder = root.val;
            root = root.right;
        }
        return true;
    }

    public int kthSmallest(TreeNode root, int k) {
//        List<TreeNode> list = new ArrayList<>();
//        kthSmallest_helper(root, list);
//        if (k - 1 < list.size()) {
//            return list.get(k - 1).val;
//        }
//        return -1;
        Deque<TreeNode> stack = new LinkedList<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            --k;
            if (k == 0) {
                break;
            }
            root = root.right;
        }
        return root.val;
    }

    private void kthSmallest_helper(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }
        kthSmallest_helper(root.left, list);
        list.add(root);
        kthSmallest_helper(root.right, list);
    }

    public List<Integer> rightSideView(TreeNode root) {
        Map<Integer, Integer> rightmostValueAtDepth = new HashMap<Integer, Integer>();
        int max_depth = -1;

        Deque<TreeNode> nodeStack = new LinkedList<>();
        Deque<Integer> depthStack = new LinkedList<>();
        nodeStack.push(root);
        depthStack.push(0);

        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            int depth = depthStack.pop();

            if (node != null) {
                // 维护二叉树的最大深度
                max_depth = Math.max(max_depth, depth);

                // 如果不存在对应深度的节点我们才插入
                if (!rightmostValueAtDepth.containsKey(depth)) {
                    rightmostValueAtDepth.put(depth, node.val);
                }

                nodeStack.push(node.left);
                nodeStack.push(node.right);
                depthStack.push(depth + 1);
                depthStack.push(depth + 1);
            }
        }
        List<Integer> rightView = new ArrayList<>();
        for (int depth = 0; depth <= max_depth; depth++) {
            rightView.add(rightmostValueAtDepth.get(depth));
        }
        return rightView;
    }

    public void flatten(TreeNode root) {
        // 方法1：前序遍历存到数组中，
        List<TreeNode> list = new ArrayList<TreeNode>();
        preorderTraversal(root, list);
        int length = list.size();
        for (int i = 1; i < length; i++) {
            TreeNode prev = list.get(i - 1), curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }

    public void preorderTraversal(TreeNode root, List<TreeNode> list) {
        if (root != null) {
            list.add(root);
            preorderTraversal(root.left, list);
            preorderTraversal(root.right, list);
        }
    }
}
