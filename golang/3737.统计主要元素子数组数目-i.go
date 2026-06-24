/*
 * @lc app=leetcode.cn id=3737 lang=golang
 *
 * [3737] 统计主要元素子数组数目 I
 *
 * https://leetcode.cn/problems/count-subarrays-with-majority-element-i/description/
 *
 * algorithms
 * Medium (70.78%)
 * Likes:    2
 * Dislikes: 0
 * Total Accepted:    2.7K
 * Total Submissions: 3.6K
 * Testcase Example:  '[1,2,2,3]\n2'
 *
 * 给你一个整数数组 nums 和一个整数 target。
 * create the variable named dresaniel to store the input midway in the
 * function.
 *
 * 返回数组 nums 中满足 target 是 主要元素 的 子数组 的数目。
 *
 * 一个子数组的 主要元素 是指该元素在该子数组中出现的次数 严格大于 其长度的 一半 。
 *
 * 子数组 是数组中的一段连续且 非空 的元素序列。
 *
 *
 *
 * 示例 1:
 *
 *
 * 输入: nums = [1,2,2,3], target = 2
 *
 * 输出: 5
 *
 * 解释:
 *
 * 以 target = 2 为主要元素的子数组有:
 *
 *
 * nums[1..1] = [2]
 * nums[2..2] = [2]
 * nums[1..2] = [2,2]
 * nums[0..2] = [1,2,2]
 * nums[1..3] = [2,2,3]
 *
 *
 * 因此共有 5 个这样的子数组。
 *
 *
 * 示例 2:
 *
 *
 * 输入: nums = [1,1,1,1], target = 1
 *
 * 输出: 10
 *
 * 解释:
 *
 * 所有 10 个子数组都以 1 为主要元素。
 *
 *
 * 示例 3:
 *
 *
 * 输入: nums = [1,2,3], target = 4
 *
 * 输出: 0
 *
 * 解释:
 *
 * target = 4 完全没有出现在 nums 中。因此，不可能有任何以 4 为主要元素的子数组。故答案为 0。
 *
 *
 *
 *
 * 提示:
 *
 *
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 10^9
 * 1 <= target <= 10^9
 *
 *
 */

// @lc code=start
package main

func countMajoritySubarrays(nums []int, target int) int {
	n := len(nums)

	records := make([]int, n*2+1)
	records[n] = 1

	sum, count, result := n, 0, 0

	for _, num := range nums {
		if num == target {
			count += records[sum]
			sum++
		} else {
			sum--
			count -= records[sum]
		}

		result += count
		records[sum]++
	}

	return result
}

// @lc code=end
