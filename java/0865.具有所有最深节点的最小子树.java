/*
 * @lc app=leetcode.cn id=865 lang=java
 *
 * [865] 具有所有最深节点的最小子树
 *
 * https://leetcode.cn/problems/smallest-subtree-with-all-the-deepest-nodes/description/
 *
 * algorithms
 * Medium (68.83%)
 * Likes:    203
 * Dislikes: 0
 * Total Accepted:    16.4K
 * Total Submissions: 23.7K
 * Testcase Example:  '[3,5,1,6,2,0,8,null,null,7,4]'
 *
 * 给定一个根为 root 的二叉树，每个节点的深度是 该节点到根的最短距离 。
 * 
 * 返回包含原始树中所有 最深节点 的 最小子树 。
 * 
 * 如果一个节点在 整个树 的任意节点之间具有最大的深度，则该节点是 最深的 。
 * 
 * 一个节点的 子树 是该节点加上它的所有后代的集合。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4]
 * 输出：[2,7,4]
 * 解释：
 * 我们返回值为 2 的节点，在图中用黄色标记。
 * 在图中用蓝色标记的是树的最深的节点。
 * 注意，节点 5、3 和 2 包含树中最深的节点，但节点 2 的子树最小，因此我们返回它。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：root = [1]
 * 输出：[1]
 * 解释：根节点是树中最深的节点。
 * 
 * 示例 3：
 * 
 * 
 * 输入：root = [0,1,3,null,2]
 * 输出：[2]
 * 解释：树中最深的节点为 2 ，有效子树为节点 2、1 和 0 的子树，但节点 2 的子树最小。
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 树中节点的数量在 [1, 500] 范围内。
 * 0 <= Node.val <= 500
 * 每个节点的值都是 独一无二 的。
 * 
 * 
 * 
 * 
 * 注意：本题与力扣 1123
 * 重复：https://leetcode-cn.com/problems/lowest-common-ancestor-of-deepest-leaves
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
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return dfs(root).lca;
    }

    private Tuple dfs(TreeNode node) {
        if (node == null)
            return new Tuple(0, null);

        Tuple left = dfs(node.left);
        Tuple right = dfs(node.right);

        if (left.depth > right.depth)
            return new Tuple(left.depth + 1, left.lca);
        else if (left.depth < right.depth)
            return new Tuple(right.depth + 1, right.lca);
        return new Tuple(left.depth + 1, node);
    }

    static class Tuple {
        private int depth;
        private TreeNode lca;

        Tuple(int depth, TreeNode lca) {
            this.depth = depth;
            this.lca = lca;
        }
    }
}
// @lc code=end
