import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import javax.swing.tree.TreeNode;

/*
 * @lc app=leetcode.cn id=897 lang=java
 *
 * [897] 递增顺序搜索树
 *
 * https://leetcode.cn/problems/increasing-order-search-tree/description/
 *
 * algorithms
 * Easy (74.19%)
 * Likes:    305
 * Dislikes: 0
 * Total Accepted:    73.7K
 * Total Submissions: 99.5K
 * Testcase Example:  '[5,3,6,2,4,null,8,1,null,null,null,7,9]'
 *
 * 给你一棵二叉搜索树的 root ，请你 按中序遍历
 * 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：root = [5,1,7]
 * 输出：[1,null,5,null,7]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 树中节点数的取值范围是 [1, 100]
 * 0 <= Node.val <= 1000
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
    public TreeNode increasingBST(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        List<TreeNode> list = new ArrayList<>();
        while (root != null || !queue.isEmpty()) {
            while (root != null) {
                queue.add(root);
                root = root.left;
            }

            root = queue.pollLast();
            list.add(root);
            root = root.right;
        }

        TreeNode dummy = new TreeNode(), current = dummy;
        for (TreeNode tn : list) {
            current.right = tn;
            tn.left = null;
            current = current.right;
        }

        return dummy.right;
    }
}
// @lc code=end
