/*
 * @lc app=leetcode.cn id=993 lang=java
 *
 * [993] 二叉树的堂兄弟节点
 *
 * https://leetcode.cn/problems/cousins-in-binary-tree/description/
 *
 * algorithms
 * Easy (55.81%)
 * Likes:    303
 * Dislikes: 0
 * Total Accepted:    65.7K
 * Total Submissions: 117.5K
 * Testcase Example:  '[1,2,3,4]\n4\n3'
 *
 * 在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
 * 
 * 如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。
 * 
 * 我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。
 * 
 * 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 输入：root = [1,2,3,4], x = 4, y = 3
 * 输出：false
 * 
 * 
 * 示例 2：
 * 
 * 
 * 
 * 输入：root = [1,2,3,null,4,null,5], x = 5, y = 4
 * 输出：true
 * 
 * 
 * 示例 3：
 * 
 * 
 * 
 * 
 * 输入：root = [1,2,3,null,4], x = 2, y = 3
 * 输出：false
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 二叉树的节点数介于 2 到 100 之间。
 * 每个节点的值都是唯一的、范围为 1 到 100 的整数。
 * 
 * 
 * 
 * 
 */

// @lc code=start

import java.util.ArrayDeque;
import java.util.Deque;

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
    public boolean isCousins(TreeNode root, int x, int y) {
        int[] xi = bfs(root, x);
        int[] yi = bfs(root, y);

        return xi[1] == yi[1] && xi[0] != yi[0];
    }

    private int[] bfs(TreeNode root, int target) {
        Deque<Object[]> d = new ArrayDeque<>();
        d.addLast(new Object[] { root, null, 0 });

        while (!d.isEmpty()) {
            int size = d.size();
            while (size-- > 0) {
                Object[] record = d.pollFirst();
                TreeNode current = (TreeNode) record[0];
                TreeNode father = (TreeNode) record[1];
                int depth = (Integer) record[2];

                if (current.val == target)
                    return new int[] { father != null ? father.val : 0, depth };
                if (current.left != null)
                    d.addLast(new Object[] { current.left, current, depth + 1 });
                if (current.right != null)
                    d.addLast(new Object[] { current.right, current, depth + 1 });
            }
        }

        return new int[] { -1, -1 };
    }
}
// @lc code=end
