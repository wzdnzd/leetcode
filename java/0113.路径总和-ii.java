import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=113 lang=java
 *
 * [113] 路径总和 II
 *
 * https://leetcode.cn/problems/path-sum-ii/description/
 *
 * algorithms
 * Medium (63.25%)
 * Likes:    841
 * Dislikes: 0
 * Total Accepted:    294.8K
 * Total Submissions: 466K
 * Testcase Example:  '[5,4,8,11,null,13,4,7,2,null,null,5,1]\n22'
 *
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * 
 * 叶子节点 是指没有子节点的节点。
 * 
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：[[5,4,11,2],[5,8,4,5]]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：[]
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：root = [1,2], targetSum = 0
 * 输出：[]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 树中节点总数在范围 [0, 5000] 内
 * -1000 
 * -1000 
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
    private int currentSum = 0;

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(root, targetSum, ans, new ArrayList<>());
        return ans;
    }

    private void dfs(TreeNode root, int targetSum, List<List<Integer>> ans, List<Integer> path) {
        // 树中节点存在负值，所以不能通过判断 currentSum + root.val > targetSum 及时终止
        // if (root == null || currentSum + root.val > targetSum)
        if (root == null)
            return;

        path.add(root.val);
        currentSum += root.val;
        if (currentSum == targetSum && root.left == null && root.right == null) {
            ans.add(new ArrayList<>(path));
        }

        dfs(root.left, targetSum, ans, path);
        dfs(root.right, targetSum, ans, path);

        int val = path.remove(path.size() - 1);
        currentSum -= val;
    }
}
// @lc code=end
