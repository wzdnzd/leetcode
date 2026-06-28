/*
 * @lc app=leetcode.cn id=1846 lang=golang
 *
 * [1846] 减小和重新排列数组后的最大元素
 *
 * https://leetcode.cn/problems/maximum-element-after-decreasing-and-rearranging/description/
 *
 * algorithms
 * Medium (63.23%)
 * Likes:    102
 * Dislikes: 0
 * Total Accepted:    37K
 * Total Submissions: 58.2K
 * Testcase Example:  '[2,2,1,2,1]'
 *
 * 给你一个正整数数组 arr 。请你对 arr 执行一些操作（也可以不进行任何操作），使得数组满足以下条件：
 *
 *
 * arr 中 第一个 元素必须为 1 。
 * 任意相邻两个元素的差的绝对值 小于等于 1 ，也就是说，对于任意的 1  （数组下标从 0 开始），都满足 abs(arr[i] - arr[i -
 * 1])  。abs(x) 为 x 的绝对值。
 *
 *
 * 你可以执行以下 2 种操作任意次：
 *
 *
 * 减小 arr 中任意元素的值，使其变为一个 更小的正整数 。
 * 重新排列 arr 中的元素，你可以以任意顺序重新排列。
 *
 *
 * 请你返回执行以上操作后，在满足前文所述的条件下，arr 中可能的 最大值 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：arr = [2,2,1,2,1]
 * 输出：2
 * 解释：
 * 我们可以重新排列 arr 得到 [1,2,2,2,1] ，该数组满足所有条件。
 * arr 中最大元素为 2 。
 *
 *
 * 示例 2：
 *
 *
 * 输入：arr = [100,1,1000]
 * 输出：3
 * 解释：
 * 一个可行的方案如下：
 * 1. 重新排列 arr 得到 [1,100,1000] 。
 * 2. 将第二个元素减小为 2 。
 * 3. 将第三个元素减小为 3 。
 * 现在 arr = [1,2,3] ，满足所有条件。
 * arr 中最大元素为 3 。
 *
 *
 * 示例 3：
 *
 *
 * 输入：arr = [1,2,3,4,5]
 * 输出：5
 * 解释：数组已经满足所有条件，最大元素为 5 。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1
 * 1
 *
 *
 */

// @lc code=start
package main

func maximumElementAfterDecrementingAndRearranging(arr []int) int {
	n := len(arr)
	records := make([]int, n+1)
	for _, x := range arr {
		records[min(x, n)]++
	}

	num := 0
	for x := 1; x <= n; x++ {
		num = min(num+records[x], x)
	}

	return num
}

func min(x, y int) int {
	if x < y {
		return x
	}

	return y
}

// @lc code=end
