/*
 * @lc app=leetcode.cn id=2196 lang=golang
 *
 * [2196] 根据描述创建二叉树
 *
 * https://leetcode.cn/problems/create-binary-tree-from-descriptions/description/
 *
 * algorithms
 * Medium (73.77%)
 * Likes:    66
 * Dislikes: 0
 * Total Accepted:    15K
 * Total Submissions: 20K
 * Testcase Example:  '[[20,15,1],[20,17,0],[50,20,1],[50,80,0],[80,19,1]]'
 *
 * 给你一个二维整数数组 descriptions ，其中 descriptions[i] = [parenti, childi, isLefti] 表示
 * parenti 是 childi 在 二叉树 中的 父节点，二叉树中各节点的值 互不相同 。此外：
 *
 *
 * 如果 isLefti == 1 ，那么 childi 就是 parenti 的左子节点。
 * 如果 isLefti == 0 ，那么 childi 就是 parenti 的右子节点。
 *
 *
 * 请你根据 descriptions 的描述来构造二叉树并返回其 根节点 。
 *
 * 测试用例会保证可以构造出 有效 的二叉树。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 *
 * 输入：descriptions = [[20,15,1],[20,17,0],[50,20,1],[50,80,0],[80,19,1]]
 * 输出：[50,20,80,15,17,19]
 * 解释：根节点是值为 50 的节点，因为它没有父节点。
 * 结果二叉树如上图所示。
 *
 *
 * 示例 2：
 *
 *
 *
 *
 * 输入：descriptions = [[1,2,1],[2,3,0],[3,4,1]]
 * 输出：[1,2,null,null,3,4]
 * 解释：根节点是值为 1 的节点，因为它没有父节点。
 * 结果二叉树如上图所示。
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= descriptions.length <= 10^4
 * descriptions[i].length == 3
 * 1 <= parenti, childi <= 10^5
 * 0 <= isLefti <= 1
 * descriptions 所描述的二叉树是一棵有效二叉树
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

func createBinaryTree(descriptions [][]int) *TreeNode {
	n := len(descriptions)
	nodes := make(map[int]*TreeNode, n+1)
	children := make(map[int]bool, n)

	for _, d := range descriptions {
		x, y := d[0], d[1]
		if nodes[x] == nil {
			nodes[x] = &TreeNode{Val: x}
		}

		if nodes[y] == nil {
			nodes[y] = &TreeNode{Val: y}
		}

		if d[2] == 1 {
			nodes[x].Left = nodes[y]
		} else {
			nodes[x].Right = nodes[y]
		}

		children[y] = true
	}

	for x, node := range nodes {
		if !children[x] {
			return node
		}
	}

	panic("descriptions is not a valid binary tree")
}

// @lc code=end
