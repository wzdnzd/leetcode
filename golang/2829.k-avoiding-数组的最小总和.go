/*
 * @lc app=leetcode.cn id=2829 lang=golang
 *
 * [2829] k-avoiding 数组的最小总和
 *
 * https://leetcode.cn/problems/determine-the-minimum-sum-of-a-k-avoiding-array/description/
 *
 * algorithms
 * Medium (60.36%)
 * Likes:    19
 * Dislikes: 0
 * Total Accepted:    9.1K
 * Total Submissions: 14.6K
 * Testcase Example:  '5\n4'
 *
 * 给你两个整数 n 和 k 。
 *
 * 对于一个由 不同 正整数组成的数组，如果其中不存在任何求和等于 k 的不同元素对，则称其为 k-avoiding 数组。
 *
 * 返回长度为 n 的 k-avoiding 数组的可能的最小总和。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：n = 5, k = 4
 * 输出：18
 * 解释：设若 k-avoiding 数组为 [1,2,4,5,6] ，其元素总和为 18 。
 * 可以证明不存在总和小于 18 的 k-avoiding 数组。
 *
 *
 * 示例 2：
 *
 *
 * 输入：n = 2, k = 6
 * 输出：3
 * 解释：可以构造数组 [1,2] ，其元素总和为 3 。
 * 可以证明不存在总和小于 3 的 k-avoiding 数组。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= n, k <= 50
 *
 *
 */

// @lc code=start
package main

func minimumSum(n int, k int) int {
	m := min(k/2, n)
	return (m*(m+1) + (k*2+n-m-1)*(n-m)) / 2
}

// @lc code=end
