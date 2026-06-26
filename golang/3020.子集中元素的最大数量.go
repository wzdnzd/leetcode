/*
 * @lc app=leetcode.cn id=3020 lang=golang
 *
 * [3020] 子集中元素的最大数量
 *
 * https://leetcode.cn/problems/find-the-maximum-number-of-elements-in-subset/description/
 *
 * algorithms
 * Medium (27.65%)
 * Likes:    15
 * Dislikes: 0
 * Total Accepted:    6.8K
 * Total Submissions: 23.1K
 * Testcase Example:  '[5,4,1,2,2]'
 *
 * 给你一个 正整数 数组 nums 。
 *
 * 你需要从数组中选出一个满足下述条件的子集：
 *
 *
 * 你可以将选中的元素放置在一个下标从 0 开始的数组中，并使其遵循以下模式：[x, x^2, x^4, ..., x^k/2, x^k, x^k/2,
 * ..., x^4, x^2, x]（注意，k 可以是任何 非负 的 2 的幂）。例如，[2, 4, 16, 4, 2] 和 [3, 9, 3]
 * 都符合这一模式，而 [2, 4, 8, 4, 2] 则不符合。
 *
 *
 * 返回满足这些条件的子集中，元素数量的 最大值 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = [5,4,1,2,2]
 * 输出：3
 * 解释：选择子集 {4,2,2} ，将其放在数组 [2,4,2] 中，它遵循该模式，且 2^2 == 4 。因此答案是 3 。
 *
 *
 * 示例 2：
 *
 *
 * 输入：nums = [1,3,2,4]
 * 输出：1
 * 解释：选择子集 {1}，将其放在数组 [1] 中，它遵循该模式。因此答案是 1 。注意我们也可以选择子集 {2} 、{4} 或 {3}
 * ，可能存在多个子集都能得到相同的答案。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 2 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 *
 *
 */

// @lc code=start
package main

func maximumLength(nums []int) int {
	records := map[int]int{}
	for _, num := range nums {
		records[num]++
	}

	result := records[1] - 1 | 1
	delete(records, 1)

	for x := range records {
		count := 0
		for records[x] >= 2 {
			count += 2
			x *= x
		}

		count += records[x]
		result = max(result, count-1|1)
	}

	return result
}

// @lc code=end
