/*
 * @lc app=leetcode.cn id=826 lang=golang
 *
 * [826] 安排工作以达到最大收益
 *
 * https://leetcode.cn/problems/most-profit-assigning-work/description/
 *
 * algorithms
 * Medium (43.30%)
 * Likes:    138
 * Dislikes: 0
 * Total Accepted:    27.2K
 * Total Submissions: 56.3K
 * Testcase Example:  '[2,4,6,8,10]\n[10,20,30,40,50]\n[4,5,6,7]'
 *
 * 你有 n 个工作和 m 个工人。给定三个数组： difficulty, profit 和 worker ，其中:
 *
 *
 * difficulty[i] 表示第 i 个工作的难度，profit[i] 表示第 i 个工作的收益。
 * worker[i] 是第 i 个工人的能力，即该工人只能完成难度小于等于 worker[i] 的工作。
 *
 *
 * 每个工人 最多 只能安排 一个 工作，但是一个工作可以 完成多次 。
 *
 *
 * 举个例子，如果 3 个工人都尝试完成一份报酬为 $1 的同样工作，那么总收益为 $3 。如果一个工人不能完成任何工作，他的收益为 $0 。
 *
 *
 * 返回 在把工人分配到工作岗位后，我们所能获得的最大利润 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
 * 输出: 100
 * 解释: 工人被分配的工作难度是 [4,4,6,6] ，分别获得 [20,20,30,30] 的收益。
 *
 * 示例 2:
 *
 *
 * 输入: difficulty = [85,47,57], profit = [24,66,99], worker = [40,25,25]
 * 输出: 0
 *
 *
 *
 * 提示:
 *
 *
 * n == difficulty.length
 * n == profit.length
 * m == worker.length
 * 1 <= n, m <= 10^4
 * 1 <= difficulty[i], profit[i], worker[i] <= 10^5
 *
 *
 */

// @lc code=start
package main

import "slices"

func maxProfitAssignment(difficulty []int, profit []int, worker []int) int {
	n := len(difficulty)

	type job struct{ diff, profit int }
	jobs := make([]job, n)

	for i, d := range difficulty {
		jobs[i] = job{d, profit[i]}
	}

	slices.SortFunc(jobs, func(a, b job) int { return a.diff - b.diff })
	slices.Sort(worker)

	result, index, maxProfit := 0, 0, 0
	for _, w := range worker {
		for index < n && jobs[index].diff <= w {
			maxProfit = max(maxProfit, jobs[index].profit)
			index++
		}

		result += maxProfit
	}

	return result
}

// @lc code=end
