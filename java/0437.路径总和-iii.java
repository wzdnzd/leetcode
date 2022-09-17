import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=437 lang=java
 *
 * [437] 路径总和 III
 *
 * https://leetcode.cn/problems/path-sum-iii/description/
 *
 * algorithms
 * Medium (53.68%)
 * Likes:    1462
 * Dislikes: 0
 * Total Accepted:    193.3K
 * Total Submissions: 361.3K
 * Testcase Example:  '[10,5,-3,3,2,null,11,3,-2,null,1]\n8'
 *
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * 
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 
 * 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * 输出：3
 * 解释：和等于 8 的路径有 3 条，如图所示。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：3
 * 
 * 
 * 
 * 
 * 提示:
 * 
 * 
 * 二叉树的节点个数的范围是 [0,1000]
 * -10^9  
 * -1000  
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
    // public int pathSum(TreeNode root, int targetSum) {
    // if (root == null)
    // return 0;
    // int count = nodeSum(root, targetSum);

    // return count + pathSum(root.left, targetSum) + pathSum(root.right,
    // targetSum);
    // }

    // /**
    // *
    // * @param root
    // * @param targetSum 转换为 long 类型是为了防止减数为负数时两个数相减造成内存溢出
    // * @return
    // */
    // private int nodeSum(TreeNode root, long targetSum) {
    // if (root == null)
    // return 0;

    // int count = 0;
    // long val = (long) root.val;
    // if (root.val == targetSum)
    // count++;
    // count += nodeSum(root.left, targetSum - val) + nodeSum(root.right, targetSum
    // - val);

    // return count;
    // }

    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> prefixMap = new HashMap<>();
        // 防止当 root.val 等于 targetSum 时被忽略
        prefixMap.put(0L, 1);

        return dfs(root, targetSum, prefixMap, 0L);
    }

    private int dfs(TreeNode root, int targetSum, Map<Long, Integer> prefixMap, long current) {
        if (root == null)
            return 0;

        current += root.val;
        int count = prefixMap.getOrDefault(current - targetSum, 0);
        prefixMap.put(current, prefixMap.getOrDefault(current, 0) + 1);

        int nl = dfs(root.left, targetSum, prefixMap, current);
        int nr = dfs(root.right, targetSum, prefixMap, current);

        prefixMap.put(current, prefixMap.get(current) - 1);

        return count + nl + nr;
    }
}
// @lc code=end
