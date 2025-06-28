/*
 * @lc app=leetcode.cn id=1498 lang=golang
 *
 * [1498] 满足条件的子序列数目
 *
 * https://leetcode.cn/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/description/
 *
 * algorithms
 * Medium (39.68%)
 * Likes:    162
 * Dislikes: 0
 * Total Accepted:    14.8K
 * Total Submissions: 37.2K
 * Testcase Example:  '[3,5,6,7]\n9'
 *
 * 给你一个整数数组 nums 和一个整数 target 。
 *
 * 请你统计并返回 nums 中能满足其最小元素与最大元素的 和 小于或等于 target 的 非空 子序列的数目。
 *
 * 由于答案可能很大，请将结果对 10^9 + 7 取余后返回。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = [3,5,6,7], target = 9
 * 输出：4
 * 解释：有 4 个子序列满足该条件。
 * [3] -> 最小元素 + 最大元素 <= target (3 + 3 <= 9)
 * [3,5] -> (3 + 5 <= 9)
 * [3,5,6] -> (3 + 6 <= 9)
 * [3,6] -> (3 + 6 <= 9)
 *
 *
 * 示例 2：
 *
 *
 * 输入：nums = [3,3,6,8], target = 10
 * 输出：6
 * 解释：有 6 个子序列满足该条件。（nums 中可以有重复数字）
 * [3] , [3] , [3,3], [3,6] , [3,6] , [3,3,6]
 *
 * 示例 3：
 *
 *
 * 输入：nums = [2,3,3,4,6,7], target = 12
 * 输出：61
 * 解释：共有 63 个非空子序列，其中 2 个不满足条件（[6,7], [7]）
 * 有效序列总数为（63 - 2 = 61）
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^6
 * 1 <= target <= 10^6
 *
 *
 */

// @lc code=start
package main

import "sort"

const MOD = int(1e9 + 7)

func numSubseq(nums []int, target int) int {
	n := len(nums)
	records := make([]int, n)
	records[0] = 1
	for i := 1; i < n; i++ {
		records[i] = (records[i-1] * 2) % MOD
	}

	sort.Ints(nums)
	count := 0

	for left, num := range nums {
		if num*2 > target {
			break
		}

		maxValue := target - num
		right := sort.SearchInts(nums, maxValue+1) - 1

		contribute := 0
		if right >= left {
			contribute = records[right-left]
		}

		count = (count + contribute) % MOD
	}

	return count
}

// @lc code=end
