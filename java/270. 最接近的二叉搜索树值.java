/*
 * @lc app=leetcode.cn id=279 lang=java
 *
 * [270] 最接近的二叉搜索树值
 *
 * https://www.lintcode.com/problem/900/solution
 *
 * algorithms
 * Medium (65.10%)
 * Likes:    1418
 * Dislikes: 0
 * Total Accepted:    303.8K
 * Total Submissions: 465.9K
 * Testcase Example:  '12'
 *
 * 给一棵非空二叉搜索树以及一个target值，找到在BST中最接近给定值的节点值。
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入: root = {5,4,9,2,#,8,10} and target = 6.124780
 * 输出: 5
 * 解释：
 * 二叉树 {5,4,9,2,#,8,10}，表示如下的树结构：
 *       5
 *      / \
 *    4    9
 *   /    / \
 *  2    8  10
 * 
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入: root = {3,2,4,1} and target = 4.142857
 * 输出: 4
 * 解释：
 * 二叉树 {3,2,4,1}，表示如下的树结构：
 *     3
 *    / \
 *   2   4
 *  /
 * 1
 * 
 * 
 * 提示：
 * 
 * 给出的目标值为浮点数
 * 可以保证只有唯一一个最接近给定值的节点
 * 
 * 
 */

// @lc code=start
/**
 * Definition of TreeNode:
 * public class TreeNode {
 * public int val;
 * public TreeNode left, right;
 * public TreeNode(int val) {
 * this.val = val;
 * this.left = this.right = null;
 * }
 * }
 */

class Solution {
    public int closestValue(TreeNode root, double target) {
        TreeNode upper = root, lower = root;
        while (root != null) {
            if (root.val < target) {
                lower = root;
                root = root.right;
            } else if (root.val > target) {
                upper = root;
                root = root.left;
            } else
                return root.val;
        }

        boolean flag = Math.abs(upper.val - target) > Math.abs(lower.val - target);
        return flag ? lower.val : upper.val;
    }
}
// @lc code=end
