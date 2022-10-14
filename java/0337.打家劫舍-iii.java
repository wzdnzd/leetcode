/*
 * @lc app=leetcode.cn id=337 lang=java
 *
 * [337] 打家劫舍 III
 *
 * https://leetcode.cn/problems/house-robber-iii/description/
 *
 * algorithms
 * Medium (60.93%)
 * Likes:    1450
 * Dislikes: 0
 * Total Accepted:    227.6K
 * Total Submissions: 373.4K
 * Testcase Example:  '[3,2,3,null,3,null,1]'
 *
 * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
 * 
 * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果
 * 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
 * 
 * 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
 * 
 * 
 * 
 * 示例 1:
 * 
 * 
 * 
 * 
 * 输入: root = [3,2,3,null,3,null,1]
 * 输出: 7 
 * 解释: 小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7
 * 
 * 示例 2:
 * 
 * 
 * 
 * 
 * 输入: root = [3,4,5,1,3,null,1]
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 4 + 5 = 9
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 
 * 
 * 树的节点数在 [1, 10^4] 范围内
 * 0 <= Node.val <= 10^4
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
    public int rob(TreeNode root) {
        // if (root == null)
        // return 0;

        // // m: 记录打劫当前层及左右孩子的孩子层时获得的最大收益，n: 记录打劫孩子层获得的最大收益
        // int m = root.val, n = 0;
        // if (root.left != null)
        // m += rob(root.left.left) + rob(root.left.right);
        // if (root.right != null)
        // m += rob(root.right.left) + rob(root.right.right);

        // n += rob(root.left) + rob(root.right);

        // return Math.max(m, n);

        int[] array = dfs(root);
        return Math.max(array[0], array[1]);
    }

    private int[] dfs(TreeNode root) {
        int[] result = new int[2];
        if (root == null)
            return result;

        int[] left = dfs(root.left), right = dfs(root.right);
        // 偷当前节点：当前节点能偷到的最大钱数 = 当前节点的钱数 + 左孩子选择自己不偷时能得到的钱 + 右孩子选择不偷时能得到的钱
        result[0] = root.val + left[1] + right[1];

        // 不偷当前节点：当前节点能偷到的最大钱数 = 左孩子能偷到的钱 + 右孩子能偷到的钱
        result[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return result;
    }
}
// @lc code=end
