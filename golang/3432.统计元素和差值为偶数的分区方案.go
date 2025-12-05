/*
 * @lc app=leetcode.cn id=3432 lang=golang
 *
 * [3432] 统计元素和差值为偶数的分区方案
 *
 * https://leetcode.cn/problems/count-partitions-with-even-sum-difference/description/
 *
 * algorithms
 * Easy (86.06%)
 * Likes:    4
 * Dislikes: 0
 * Total Accepted:    6.7K
 * Total Submissions: 7.7K
 * Testcase Example:  '[10,10,3,7,6]'
 *
 * 给你一个长度为 n 的整数数组 nums 。
 *
 * 分区 是指将数组按照下标 i （0 <= i < n - 1）划分成两个 非空 子数组，其中：
 *
 *
 * 左子数组包含区间 [0, i] 内的所有下标。
 * 右子数组包含区间 [i + 1, n - 1] 内的所有下标。
 *
 *
 * 对左子数组和右子数组先求元素 和 再做 差 ，统计并返回差值为 偶数 的 分区 方案数。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = [10,10,3,7,6]
 *
 * 输出：4
 *
 * 解释：
 *
 * 共有 4 个满足题意的分区方案：
 *
 *
 * [10]、[10, 3, 7, 6] 元素和的差值为 10 - 26 = -16 ，是偶数。
 * [10, 10]、[3, 7, 6] 元素和的差值为 20 - 16 = 4，是偶数。
 * [10, 10, 3]、[7, 6] 元素和的差值为 23 - 13 = 10，是偶数。
 * [10, 10, 3, 7]、[6] 元素和的差值为 30 - 6 = 24，是偶数。
 *
 *
 *
 * 示例 2：
 *
 *
 * 输入：nums = [1,2,2]
 *
 * 输出：0
 *
 * 解释：
 *
 * 不存在元素和的差值为偶数的分区方案。
 *
 *
 * 示例 3：
 *
 *
 * 输入：nums = [2,4,6,8]
 *
 * 输出：3
 *
 * 解释：
 *
 * 所有分区方案都满足元素和的差值为偶数。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 2 <= n == nums.length <= 100
 * 1 <= nums[i] <= 100
 *
 *
 */

// @lc code=start
package main

func countPartitions(nums []int) int {
	total := 0
	for _, num := range nums {
		total += num
	}

	count, sum := 0, 0
	for i := 0; i < len(nums)-1; i++ {
		sum += nums[i]
		if (total-sum*2)%2 == 0 {
			count++
		}
	}

	return count
}

// @lc code=end
