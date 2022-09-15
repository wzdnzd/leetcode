/*
 * @lc app=leetcode.cn id=1373 lang=java
 *
 * [1373] 二叉搜索子树的最大键值和
 *
 * https://leetcode.cn/problems/maximum-sum-bst-in-binary-tree/description/
 *
 * algorithms
 * Hard (42.57%)
 * Likes:    112
 * Dislikes: 0
 * Total Accepted:    16.5K
 * Total Submissions: 38.8K
 * Testcase Example:  '[1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]'
 *
 * 给你一棵以 root 为根的 二叉树 ，请你返回 任意 二叉搜索子树的最大键值和。
 * 
 * 二叉搜索树的定义如下：
 * 
 * 
 * 任意节点的左子树中的键值都 小于 此节点的键值。
 * 任意节点的右子树中的键值都 大于 此节点的键值。
 * 任意节点的左子树和右子树都是二叉搜索树。
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 
 * 输入：root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
 * 输出：20
 * 解释：键值为 3 的子树是和最大的二叉搜索树。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 
 * 
 * 输入：root = [4,3,null,1,2]
 * 输出：2
 * 解释：键值为 2 的单节点子树是和最大的二叉搜索树。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：root = [-4,-2,-5]
 * 输出：0
 * 解释：所有节点键值都为负数，和最大的二叉搜索树为空。
 * 
 * 
 * 示例 4：
 * 
 * 
 * 输入：root = [2,1,3]
 * 输出：6
 * 
 * 
 * 示例 5：
 * 
 * 
 * 输入：root = [5,4,8,3,null,6,3]
 * 输出：7
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 每棵树有 1 到 40000 个节点。
 * 每个节点的键值在 [-4 * 10^4 , 4 * 10^4] 之间。
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
    private int maxSum = 0;

    public int maxSumBST(TreeNode root) {
        maxSumBSTHelper(root);
        return maxSum;
    }

    private BSTData maxSumBSTHelper(TreeNode root) {
        if (root == null)
            return new BSTData(true, Integer.MAX_VALUE, Integer.MIN_VALUE);

        BSTData leftBstData = maxSumBSTHelper(root.left);
        BSTData rightBstData = maxSumBSTHelper(root.right);

        boolean flag = false;
        int maxVal = Integer.MIN_VALUE, minVal = Integer.MAX_VALUE, sum = 0;
        if (leftBstData.isBST() && rightBstData.isBST()
                && leftBstData.getMaxVal() < root.val
                && rightBstData.getMinVal() > root.val) {
            flag = true;
            minVal = Math.min(root.val, leftBstData.getMinVal());
            maxVal = Math.max(root.val, rightBstData.getMaxVal());
            sum = leftBstData.getMaxSumBST() + root.val + rightBstData.getMaxSumBST();

            maxSum = Math.max(maxSum, sum);
        }

        return new BSTData(flag, minVal, maxVal, sum);
    }
}

class BSTData {
    // 是否是二叉搜索树
    private boolean bstFalg;

    // 二叉搜索时最小值
    private int minVal;

    // 二叉搜索树最大值
    private int maxVal;

    // 最大键值和
    private int maxSumBST;

    public BSTData(boolean bstFalg, int minVal, int maxVal) {
        this(bstFalg, minVal, maxVal, 0);
    }

    public BSTData(boolean bstFalg, int minVal, int maxVal, int maxSumBST) {
        this.bstFalg = bstFalg;
        this.minVal = minVal;
        this.maxVal = maxVal;
        this.maxSumBST = maxSumBST;
    }

    /**
     * @return the isBST
     */
    public boolean isBST() {
        return bstFalg;
    }

    /**
     * @return the minVal
     */
    public int getMinVal() {
        return minVal;
    }

    /**
     * @return the maxVal
     */
    public int getMaxVal() {
        return maxVal;
    }

    /**
     * @return the maxSumBST
     */
    public int getMaxSumBST() {
        return maxSumBST;
    }
}
// @lc code=end
