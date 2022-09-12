import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=889 lang=java
 *
 * [889] 根据前序和后序遍历构造二叉树
 *
 * https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-postorder-traversal/description/
 *
 * algorithms
 * Medium (67.90%)
 * Likes:    275
 * Dislikes: 0
 * Total Accepted:    30.4K
 * Total Submissions: 44.7K
 * Testcase Example:  '[1,2,4,5,3,6,7]\n[4,5,2,6,7,3,1]'
 *
 * 给定两个整数数组，preorder 和 postorder ，其中 preorder 是一个具有 无重复 值的二叉树的前序遍历，postorder
 * 是同一棵树的后序遍历，重构并返回二叉树。
 * 
 * 如果存在多个答案，您可以返回其中 任何 一个。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 
 * 输入：preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
 * 输出：[1,2,3,4,5,6,7]
 * 
 * 
 * 示例 2:
 * 
 * 
 * 输入: preorder = [1], postorder = [1]
 * 输出: [1]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= preorder.length <= 30
 * 1 <= preorder[i] <= preorder.length
 * preorder 中所有值都 不同
 * postorder.length == preorder.length
 * 1 <= postorder[i] <= postorder.length
 * postorder 中所有值都 不同
 * 保证 preorder 和 postorder 是同一棵二叉树的前序遍历和后序遍历
 * 
 * 
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < postorder.length; i++) {
            map.put(postorder[i], i);
        }

        return buildTreeHelper(preorder, 0, preorder.length - 1, map, 0);
    }

    private TreeNode buildTreeHelper(int[] preorder, int preStart, int preEnd, Map<Integer, Integer> map,
            int postStart) {
        if (preStart > preEnd)
            return null;
        if (preStart == preEnd)
            return new TreeNode(preorder[preStart]);

        TreeNode root = new TreeNode(preorder[preStart]);
        int index = map.get(preorder[preStart + 1]);
        int length = index - postStart + 1;
        root.left = buildTreeHelper(preorder, preStart + 1, preStart + length, map, postStart);
        root.right = buildTreeHelper(preorder, preStart + length + 1, preEnd, map, index + 1);

        return root;
    }
}
// @lc code=end
