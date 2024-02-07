/*
 * @lc app=leetcode.cn id=2641 lang=java
 *
 * [2641] 二叉树的堂兄弟节点 II
 *
 * https://leetcode.cn/problems/cousins-in-binary-tree-ii/description/
 *
 * algorithms
 * Medium (72.08%)
 * Likes:    25
 * Dislikes: 0
 * Total Accepted:    6.3K
 * Total Submissions: 8.5K
 * Testcase Example:  '[5,4,9,1,10,null,7]'
 *
 * 给你一棵二叉树的根 root ，请你将每个节点的值替换成该节点的所有 堂兄弟节点值的和 。
 * 
 * 如果两个节点在树中有相同的深度且它们的父节点不同，那么它们互为 堂兄弟 。
 * 
 * 请你返回修改值之后，树的根 root 。
 * 
 * 注意，一个节点的深度指的是从树根节点到这个节点经过的边数。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 
 * 输入：root = [5,4,9,1,10,null,7]
 * 输出：[0,0,0,7,7,null,11]
 * 解释：上图展示了初始的二叉树和修改每个节点的值之后的二叉树。
 * - 值为 5 的节点没有堂兄弟，所以值修改为 0 。
 * - 值为 4 的节点没有堂兄弟，所以值修改为 0 。
 * - 值为 9 的节点没有堂兄弟，所以值修改为 0 。
 * - 值为 1 的节点有一个堂兄弟，值为 7 ，所以值修改为 7 。
 * - 值为 10 的节点有一个堂兄弟，值为 7 ，所以值修改为 7 。
 * - 值为 7 的节点有两个堂兄弟，值分别为 1 和 10 ，所以值修改为 11 。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 
 * 
 * 输入：root = [3,1,2]
 * 输出：[0,0,0]
 * 解释：上图展示了初始的二叉树和修改每个节点的值之后的二叉树。
 * - 值为 3 的节点没有堂兄弟，所以值修改为 0 。
 * - 值为 1 的节点没有堂兄弟，所以值修改为 0 。
 * - 值为 2 的节点没有堂兄弟，所以值修改为 0 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 树中节点数目的范围是 [1, 10^5] 。
 * 1 <= Node.val <= 10^4
 * 
 * 
 */

// @lc code=start

import java.util.ArrayDeque;
import java.util.Queue;

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
    public TreeNode replaceValueInTree(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        root.val = 0;

        while (!queue.isEmpty()) {
            Queue<TreeNode> nextQueue = new ArrayDeque<>();
            int total = 0;

            for (TreeNode node : queue) {
                if (node.left != null) {
                    nextQueue.offer(node.left);
                    total += node.left.val;
                }
                if (node.right != null) {
                    nextQueue.offer(node.right);
                    total += node.right.val;
                }
            }

            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                int sum = (node.left == null ? 0 : node.left.val) + (node.right == null ? 0 : node.right.val);

                if (node.left != null)
                    node.left.val = total - sum;

                if (node.right != null)
                    node.right.val = total - sum;
            }

            queue = nextQueue;
        }

        return root;
    }
}
// @lc code=end
