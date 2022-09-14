import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.tree.TreeNode;

/*
 * @lc app=leetcode.cn id=652 lang=java
 *
 * [652] 寻找重复的子树
 *
 * https://leetcode.cn/problems/find-duplicate-subtrees/description/
 *
 * algorithms
 * Medium (61.18%)
 * Likes:    625
 * Dislikes: 0
 * Total Accepted:    84.8K
 * Total Submissions: 138.6K
 * Testcase Example:  '[1,2,3,4,null,2,4,null,null,4]'
 *
 * 给定一棵二叉树 root，返回所有重复的子树。
 * 
 * 对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
 * 
 * 如果两棵树具有相同的结构和相同的结点值，则它们是重复的。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 
 * 输入：root = [1,2,3,4,null,2,4,null,null,4]
 * 输出：[[2,4],[4]]
 * 
 * 示例 2：
 * 
 * 
 * 
 * 
 * 输入：root = [2,1,1]
 * 输出：[[1]]
 * 
 * 示例 3：
 * 
 * 
 * 
 * 
 * 输入：root = [2,2,2,3,null,3,null]
 * 输出：[[2,3],[3]]
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 树中的结点数在[1,10^4]范围内。
 * -200 <= Node.val <= 200
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
    private Map<String, Pair<TreeNode, Integer>> map = new HashMap<>();
    private Set<TreeNode> set = new HashSet<>();
    private int index = 0;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        dfs(root);
        return new ArrayList<>(set);
    }

    private int dfs(TreeNode root) {
        if (root == null)
            return 0;

        int[] triple = { root.val, dfs(root.left), dfs(root.right) };
        String key = Arrays.toString(triple);
        if (map.containsKey(key)) {
            Pair<TreeNode, Integer> pair = map.get(key);
            set.add(pair.getLeft());
            return pair.getRight();
        }

        map.put(key, new Pair<TreeNode, Integer>(root, ++index));
        return index;
    }
}

class Pair<K, V> {
    private K left;
    private V right;

    public Pair(K left, V right) {
        this.left = left;
        this.right = right;
    }

    /**
     * @return the left
     */
    public K getLeft() {
        return left;
    }

    /**
     * @return the right
     */
    public V getRight() {
        return right;
    }
}
// @lc code=end
