import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=530 lang=java
 *
 * [530] 二叉搜索树的最小绝对差
 *
 * https://leetcode.cn/problems/minimum-absolute-difference-in-bst/description/
 *
 * algorithms
 * Easy (63.39%)
 * Likes:    409
 * Dislikes: 0
 * Total Accepted:    150.2K
 * Total Submissions: 236.9K
 * Testcase Example:  '[4,2,6,1,3]'
 *
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 * 
 * 差值是一个正数，其数值等于两值之差的绝对值。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：root = [4,2,6,1,3]
 * 输出：1
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：root = [1,0,48,null,null,12,49]
 * 输出：1
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 树中节点的数目范围是 [2, 10^4]
 * 0 <= Node.val <= 10^5
 * 
 * 
 * 
 * 
 * 注意：本题与 783
 * https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/ 相同
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
    public int getMinimumDifference(TreeNode root) {
        List<Integer> nums = new ArrayList<>();
        inorder(root, nums);

        if (nums.size() < 2)
            throw new IllegalArgumentException("node size cannot less than 2");
        int minGap = Integer.MAX_VALUE;
        for (int i = 1; i < nums.size(); i++) {
            minGap = Math.min(minGap, nums.get(i) - nums.get(i - 1));
        }

        return minGap;
    }

    private void inorder(TreeNode root, List<Integer> nums) {
        if (root == null)
            return;

        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }
}
// @lc code=end
