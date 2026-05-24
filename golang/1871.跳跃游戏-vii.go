/*
 * @lc app=leetcode.cn id=1871 lang=golang
 *
 * [1871] 跳跃游戏 VII
 *
 * https://leetcode.cn/problems/jump-game-vii/description/
 *
 * algorithms
 * Medium (30.53%)
 * Likes:    118
 * Dislikes: 0
 * Total Accepted:    15.4K
 * Total Submissions: 49.1K
 * Testcase Example:  '"011010"\n2\n3'
 *
 * 给你一个下标从 0 开始的二进制字符串 s 和两个整数 minJump 和 maxJump 。一开始，你在下标 0 处，且该位置的值一定为 '0'
 * 。当同时满足如下条件时，你可以从下标 i 移动到下标 j 处：
 *
 *
 * i + minJump  且
 * s[j] == '0'.
 *
 *
 * 如果你可以到达 s 的下标 s.length - 1 处，请你返回 true ，否则返回 false 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：s = "011010", minJump = 2, maxJump = 3
 * 输出：true
 * 解释：
 * 第一步，从下标 0 移动到下标 3 。
 * 第二步，从下标 3 移动到下标 5 。
 *
 *
 * 示例 2：
 *
 *
 * 输入：s = "01101110", minJump = 2, maxJump = 3
 * 输出：false
 *
 *
 *
 *
 * 提示：
 *
 *
 * 2
 * s[i] 要么是 '0' ，要么是 '1'
 * s[0] == '0'
 * 1
 *
 *
 */

// @lc code=start
package main

func canReach(s string, minJump, maxJump int) bool {
	n := len(s)
	prefixSums := make([]int, n+1)
	prefixSums[1] = 1

	for j := 1; j < n; j++ {
		prefixSums[j+1] = prefixSums[j]
		if j >= minJump && s[j] == '0' && prefixSums[j-minJump+1] > prefixSums[max(j-maxJump, 0)] {
			prefixSums[j+1]++
		}
	}

	return prefixSums[n] > prefixSums[n-1]
}

// @lc code=end
