/*
 * @Author: wzdnzd
 * @Date: 2022-03-18 15:05:20
 * @Description:
 * Copyright (c) 2022 by wzdnzd, All Rights Reserved.
 */
/*
 * @lc app=leetcode.cn id=1 lang=golang
 *
 * [1] 两数之和
 *
 * https://leetcode-cn.com/problems/two-sum/description/
 *
 * algorithms
 * Easy (52.20%)
 * Likes:    13768
 * Dislikes: 0
 * Total Accepted:    3.1M
 * Total Submissions: 6M
 * Testcase Example:  '[2,7,11,15]\n9'
 *
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 *
 *
 * 示例 2：
 *
 *
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 *
 *
 * 示例 3：
 *
 *
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 *
 *
 *
 *
 * 提示：
 *
 *
 * 2 <= nums.length <= 10^4
 * -10^9 <= nums[i] <= 10^9
 * -10^9 <= target <= 10^9
 * 只会存在一个有效答案
 *
 *
 * 进阶：你可以想出一个时间复杂度小于 O(n^2) 的算法吗？
 *
 */
package main

// @lc code=start
func twoSum(nums []int, target int) []int {
	if nums == nil || len(nums) < 2 {
		panic("无效的参数，数组长度必须大于1")
	}

	dict := make(map[int]int)
	result := make([]int, 2)
	for i := 0; i < len(nums); i++ {
		gap := target - nums[i]
		index, ok := dict[gap]
		if ok {
			result[0] = index
			result[1] = i
			break
		}

		dict[nums[i]] = i
	}

	return result
}

// @lc code=end
