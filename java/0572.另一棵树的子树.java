/*
 * @lc app=leetcode.cn id=572 lang=java
 *
 * [572] 另一棵树的子树
 *
 * https://leetcode.cn/problems/subtree-of-another-tree/description/
 *
 * algorithms
 * Easy (47.73%)
 * Likes:    1046
 * Dislikes: 0
 * Total Accepted:    213.4K
 * Total Submissions: 445.3K
 * Testcase Example:  '[3,4,5,1,2]\n[4,1,2]'
 *
 * 
 * 
 * 给你两棵二叉树 root 和 subRoot 。检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树。如果存在，返回 true
 * ；否则，返回 false 。
 * 
 * 二叉树 tree 的一棵子树包括 tree 的某个节点和这个节点的所有后代节点。tree 也可以看做它自身的一棵子树。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：root = [3,4,5,1,2], subRoot = [4,1,2]
 * 输出：true
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
 * 输出：false
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * root 树上的节点数量范围是 [1, 2000]
 * subRoot 树上的节点数量范围是 [1, 1000]
 * -10^4 
 * -10^4 
 * 
 * 
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
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null)
            return false;

        return isSameTree(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private boolean isSameTree(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null)
            return true;
        else if (t1 == null || t2 == null || t1.val != t2.val)
            return false;

        return isSameTree(t1.left, t2.left) && isSameTree(t1.right, t2.right);
    }
}
// @lc code=end
