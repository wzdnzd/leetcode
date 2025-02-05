/*
 * @lc app=leetcode.cn id=47 lang=golang
 *
 * [47] 全排列 II
 *
 * https://leetcode.cn/problems/permutations-ii/description/
 *
 * algorithms
 * Medium (66.11%)
 * Likes:    1645
 * Dislikes: 0
 * Total Accepted:    616.2K
 * Total Submissions: 930.8K
 * Testcase Example:  '[1,1,2]'
 *
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 * ⁠[1,2,1],
 * ⁠[2,1,1]]
 *
 *
 * 示例 2：
 *
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 *
 *
 */

// @lc code=start
package main

import "sort"

func permuteUnique(nums []int) [][]int {
	sort.Ints(nums)
	n := len(nums)
	permute := []int{}
	var result [][]int
	visited := make([]bool, n)

	var backtrack func(int)
	backtrack = func(index int) {
		if index == n {
			result = append(result, append([]int(nil), permute...))
			return
		}

		for i, v := range nums {
			if visited[i] || i > 0 && !visited[i-1] && v == nums[i-1] {
				continue
			}

			permute = append(permute, v)
			visited[i] = true
			backtrack(index + 1)
			visited[i] = false
			permute = permute[:len(permute)-1]
		}
	}

	backtrack(0)
	return result
}

// @lc code=end
