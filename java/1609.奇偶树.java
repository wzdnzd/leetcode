/*
 * @lc app=leetcode.cn id=1609 lang=java
 *
 * [1609] 奇偶树
 *
 * https://leetcode.cn/problems/even-odd-tree/description/
 *
 * algorithms
 * Medium (58.22%)
 * Likes:    101
 * Dislikes: 0
 * Total Accepted:    34.5K
 * Total Submissions: 59.3K
 * Testcase Example:  '[1,10,4,3,null,7,9,12,8,6,null,null,2]'
 *
 * 如果一棵二叉树满足下述几个条件，则可以称为 奇偶树 ：
 * 
 * 
 * 二叉树根节点所在层下标为 0 ，根的子节点所在层下标为 1 ，根的孙节点所在层下标为 2 ，依此类推。
 * 偶数下标 层上的所有节点的值都是 奇 整数，从左到右按顺序 严格递增
 * 奇数下标 层上的所有节点的值都是 偶 整数，从左到右按顺序 严格递减
 * 
 * 
 * 给你二叉树的根节点，如果二叉树为 奇偶树 ，则返回 true ，否则返回 false 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 
 * 输入：root = [1,10,4,3,null,7,9,12,8,6,null,null,2]
 * 输出：true
 * 解释：每一层的节点值分别是：
 * 0 层：[1]
 * 1 层：[10,4]
 * 2 层：[3,7,9]
 * 3 层：[12,8,6,2]
 * 由于 0 层和 2 层上的节点值都是奇数且严格递增，而 1 层和 3 层上的节点值都是偶数且严格递减，因此这是一棵奇偶树。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 
 * 
 * 输入：root = [5,4,2,3,3,7]
 * 输出：false
 * 解释：每一层的节点值分别是：
 * 0 层：[5]
 * 1 层：[4,2]
 * 2 层：[3,3,7]
 * 2 层上的节点值不满足严格递增的条件，所以这不是一棵奇偶树。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 
 * 
 * 输入：root = [5,9,1,3,5,7]
 * 输出：false
 * 解释：1 层上的节点值应为偶数。
 * 
 * 
 * 示例 4：
 * 
 * 
 * 输入：root = [1]
 * 输出：true
 * 
 * 
 * 示例 5：
 * 
 * 
 * 输入：root = [11,8,6,1,3,9,11,30,20,18,16,12,10,4,2,17]
 * 输出：true
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 树中节点数在范围 [1, 10^5] 内
 * 1 
 * 
 * 
 */

// @lc code=start

import java.util.LinkedList;
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
    public boolean isEvenOddTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean isOdd = level % 2 == 0;
            int preV = isOdd ? Integer.MIN_VALUE : Integer.MAX_VALUE;

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (isOdd) {
                    if (node.val % 2 == 0 || node.val <= preV)
                        return false;
                } else {
                    if (node.val % 2 == 1 || node.val >= preV)
                        return false;
                }

                preV = node.val;
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }

            level++;
        }

        return true;
    }
}
// @lc code=end
