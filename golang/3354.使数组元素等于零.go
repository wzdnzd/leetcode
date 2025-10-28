/*
 * @lc app=leetcode.cn id=3354 lang=golang
 *
 * [3354] 使数组元素等于零
 *
 * https://leetcode.cn/problems/make-array-elements-equal-to-zero/description/
 *
 * algorithms
 * Easy (56.14%)
 * Likes:    16
 * Dislikes: 0
 * Total Accepted:    6.6K
 * Total Submissions: 11.2K
 * Testcase Example:  '[1,0,2,0,3]'
 *
 * 给你一个整数数组 nums 。
 *
 * 开始时，选择一个满足 nums[curr] == 0 的起始位置 curr ，并选择一个移动 方向 ：向左或者向右。
 *
 * 此后，你需要重复下面的过程：
 *
 *
 * 如果 curr 超过范围 [0, n - 1] ，过程结束。
 * 如果 nums[curr] == 0 ，沿当前方向继续移动：如果向右移，则 递增 curr ；如果向左移，则 递减 curr 。
 * 如果 nums[curr] > 0:
 *
 * 将 nums[curr] 减 1 。
 * 反转 移动方向（向左变向右，反之亦然）。
 * 沿新方向移动一步。
 *
 *
 *
 *
 * 如果在结束整个过程后，nums 中的所有元素都变为 0 ，则认为选出的初始位置和移动方向 有效 。
 *
 * 返回可能的有效选择方案数目。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = [1,0,2,0,3]
 *
 * 输出：2
 *
 * 解释：
 *
 * 可能的有效选择方案如下：
 *
 *
 * 选择 curr = 3 并向左移动。
 *
 *
 * [1,0,2,0,3] -> [1,0,2,0,3] -> [1,0,1,0,3] -> [1,0,1,0,3] -> [1,0,1,0,2] ->
 * [1,0,1,0,2] -> [1,0,0,0,2] -> [1,0,0,0,2] -> [1,0,0,0,1] -> [1,0,0,0,1] ->
 * [1,0,0,0,1] -> [1,0,0,0,1] -> [0,0,0,0,1] -> [0,0,0,0,1] -> [0,0,0,0,1] ->
 * [0,0,0,0,1] -> [0,0,0,0,0].
 *
 *
 * 选择 curr = 3 并向右移动。
 *
 * [1,0,2,0,3] -> [1,0,2,0,3] -> [1,0,2,0,2] -> [1,0,2,0,2] -> [1,0,1,0,2] ->
 * [1,0,1,0,2] -> [1,0,1,0,1] -> [1,0,1,0,1] -> [1,0,0,0,1] -> [1,0,0,0,1] ->
 * [1,0,0,0,0] -> [1,0,0,0,0] -> [1,0,0,0,0] -> [1,0,0,0,0] ->
 * [0,0,0,0,0].
 *
 *
 *
 *
 *
 * 示例 2：
 *
 *
 * 输入：nums = [2,3,4,0,4,1,0]
 *
 * 输出：0
 *
 * 解释：
 *
 * 不存在有效的选择方案。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 * 至少存在一个元素 i 满足 nums[i] == 0 。
 *
 *
 */

// @lc code=start
package main

func countValidSelections(nums []int) int {
	total := 0
	for _, num := range nums {
		total += num
	}

	count, leftSum, rightSum := 0, 0, total
	for _, v := range nums {
		if v == 0 {
			if leftSum == rightSum {
				count += 2
			} else if leftSum == rightSum+1 || leftSum+1 == rightSum {
				count++
			}
		} else {
			leftSum += v
			rightSum -= v
		}
	}

	return count
}

// @lc code=end
