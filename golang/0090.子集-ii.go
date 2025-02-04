/*
 * @lc app=leetcode.cn id=90 lang=golang
 *
 * [90] 子集 II
 *
 * https://leetcode.cn/problems/subsets-ii/description/
 *
 * algorithms
 * Medium (63.57%)
 * Likes:    1260
 * Dislikes: 0
 * Total Accepted:    415.7K
 * Total Submissions: 653.3K
 * Testcase Example:  '[1,2,2]'
 *
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的 子集（幂集）。
 *
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 *
 *
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 *
 *
 * 示例 2：
 *
 *
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 *
 *
 *
 *
 */

// @lc code=start
package main

import "sort"

func subsetsWithDup(nums []int) [][]int {
	sort.Ints(nums)
	paths := []int{}

	var result [][]int

	var dfs func(bool, int)
	dfs = func(choosePre bool, current int) {
		if current == len(nums) {
			result = append(result, append([]int(nil), paths...))
			return
		}

		dfs(false, current+1)
		if !choosePre && current > 0 && nums[current-1] == nums[current] {
			return
		}

		paths = append(paths, nums[current])
		dfs(true, current+1)
		paths = paths[:len(paths)-1]
	}

	dfs(false, 0)
	return result
}

// @lc code=end
