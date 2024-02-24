/*
 * @lc app=leetcode.cn id=2476 lang=java
 *
 * [2476] 二叉搜索树最近节点查询
 *
 * https://leetcode.cn/problems/closest-nodes-queries-in-a-binary-search-tree/description/
 *
 * algorithms
 * Medium (42.16%)
 * Likes:    40
 * Dislikes: 0
 * Total Accepted:    10.2K
 * Total Submissions: 22.1K
 * Testcase Example:  '[6,2,13,1,4,9,15,null,null,null,null,null,null,14]\n[2,5,16]'
 *
 * 给你一个 二叉搜索树 的根节点 root ，和一个由正整数组成、长度为 n 的数组 queries 。
 * 
 * 请你找出一个长度为 n 的 二维 答案数组 answer ，其中 answer[i] = [mini, maxi] ：
 * 
 * 
 * mini 是树中小于等于 queries[i] 的 最大值 。如果不存在这样的值，则使用 -1 代替。
 * maxi 是树中大于等于 queries[i] 的 最小值 。如果不存在这样的值，则使用 -1 代替。
 * 
 * 
 * 返回数组 answer 。
 * 
 * 
 * 
 * 示例 1 ：
 * 
 * 
 * 
 * 
 * 输入：root = [6,2,13,1,4,9,15,null,null,null,null,null,null,14], queries =
 * [2,5,16]
 * 输出：[[2,2],[4,6],[15,-1]]
 * 解释：按下面的描述找出并返回查询的答案：
 * - 树中小于等于 2 的最大值是 2 ，且大于等于 2 的最小值也是 2 。所以第一个查询的答案是 [2,2] 。
 * - 树中小于等于 5 的最大值是 4 ，且大于等于 5 的最小值是 6 。所以第二个查询的答案是 [4,6] 。
 * - 树中小于等于 16 的最大值是 15 ，且大于等于 16 的最小值不存在。所以第三个查询的答案是 [15,-1] 。
 * 
 * 
 * 示例 2 ：
 * 
 * 
 * 
 * 
 * 输入：root = [4,null,9], queries = [3]
 * 输出：[[-1,4]]
 * 解释：树中不存在小于等于 3 的最大值，且大于等于 3 的最小值是 4 。所以查询的答案是 [-1,4] 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 树中节点的数目在范围 [2, 10^5] 内
 * 1 <= Node.val <= 10^6
 * n == queries.length
 * 1 <= n <= 10^5
 * 1 <= queries[i] <= 10^6
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

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
    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        List<Integer> arrays = new ArrayList<>();
        dfs(root, arrays);

        List<List<Integer>> result = new ArrayList<>();
        for (int query : queries) {
            int minVal = -1, maxVal = -1;
            int index = binarySearch(arrays, query);

            if (index > 0)
                minVal = arrays.get(index - 1);
            if (index < arrays.size()) {
                maxVal = arrays.get(index);
                if (maxVal == query)
                    minVal = maxVal;
            }

            result.add(List.of(minVal, maxVal));

        }

        return result;
    }

    private void dfs(TreeNode root, List<Integer> arrays) {
        if (root == null)
            return;

        dfs(root.left, arrays);
        arrays.add(root.val);
        dfs(root.right, arrays);
    }

    private int binarySearch(List<Integer> arrays, int target) {
        int left = 0, right = arrays.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arrays.get(mid) == target)
                return mid;
            else if (arrays.get(mid) < target)
                left = mid + 1;
            else
                right = mid - 1;
        }

        return left;
    }
}
// @lc code=end
