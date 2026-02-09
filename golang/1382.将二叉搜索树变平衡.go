/*
 * @lc app=leetcode.cn id=1382 lang=golang
 *
 * [1382] 将二叉搜索树变平衡
 *
 * https://leetcode.cn/problems/balance-a-binary-search-tree/description/
 *
 * algorithms
 * Medium (75.62%)
 * Likes:    240
 * Dislikes: 0
 * Total Accepted:    38.9K
 * Total Submissions: 51.2K
 * Testcase Example:  '[1,null,2,null,3,null,4]'
 *
 * 给你一棵二叉搜索树，请你返回一棵 平衡后 的二叉搜索树，新生成的树应该与原来的树有着相同的节点值。如果有多种构造方法，请你返回任意一种。
 *
 * 如果一棵二叉搜索树中，每个节点的两棵子树高度差不超过 1 ，我们就称这棵二叉搜索树是 平衡的 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 *
 * 输入：root = [1,null,2,null,3,null,4,null,null]
 * 输出：[2,1,3,null,null,null,4]
 * 解释：这不是唯一的正确答案，[3,1,4,null,2,null,null] 也是一个可行的构造方案。
 *
 *
 * 示例 2：
 *
 *
 *
 *
 * 输入: root = [2,1,3]
 * 输出: [2,1,3]
 *
 *
 *
 *
 * 提示：
 *
 *
 * 树节点的数目在 [1, 10^4] 范围内。
 * 1 <= Node.val <= 10^5
 *
 *
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
package main

func balanceBST(root *TreeNode) *TreeNode {
	var inorderSeqs []int

	var getInorder func(*TreeNode)
	getInorder = func(t *TreeNode) {
		if t.Left != nil {
			getInorder(t.Left)
		}

		inorderSeqs = append(inorderSeqs, t.Val)
		if t.Right != nil {
			getInorder(t.Right)
		}
	}

	var build func(int, int) *TreeNode
	build = func(l, r int) *TreeNode {
		mid := (l + r) >> 1
		t := &TreeNode{Val: inorderSeqs[mid]}

		if l <= mid-1 {
			t.Left = build(l, mid-1)
		}

		if mid+1 <= r {
			t.Right = build(mid+1, r)
		}

		return t
	}

	getInorder(root)
	return build(0, len(inorderSeqs)-1)
}

// @lc code=end
