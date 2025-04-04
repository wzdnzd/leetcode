/*
 * @lc app=leetcode.cn id=1863 lang=golang
 *
 * [1863] 找出所有子集的异或总和再求和
 *
 * https://leetcode.cn/problems/sum-of-all-subset-xor-totals/description/
 *
 * algorithms
 * Easy (83.49%)
 * Likes:    172
 * Dislikes: 0
 * Total Accepted:    30.6K
 * Total Submissions: 36.7K
 * Testcase Example:  '[1,3]'
 *
 * 一个数组的 异或总和 定义为数组中所有元素按位 XOR 的结果；如果数组为 空 ，则异或总和为 0 。
 *
 *
 * 例如，数组 [2,5,6] 的 异或总和 为 2 XOR 5 XOR 6 = 1 。
 *
 *
 * 给你一个数组 nums ，请你求出 nums 中每个 子集 的 异或总和 ，计算并返回这些值相加之 和 。
 *
 * 注意：在本题中，元素 相同 的不同子集应 多次 计数。
 *
 * 数组 a 是数组 b 的一个 子集 的前提条件是：从 b 删除几个（也可能不删除）元素能够得到 a 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,3]
 * 输出：6
 * 解释：[1,3] 共有 4 个子集：
 * - 空子集的异或总和是 0 。
 * - [1] 的异或总和为 1 。
 * - [3] 的异或总和为 3 。
 * - [1,3] 的异或总和为 1 XOR 3 = 2 。
 * 0 + 1 + 3 + 2 = 6
 *
 *
 * 示例 2：
 *
 * 输入：nums = [5,1,6]
 * 输出：28
 * 解释：[5,1,6] 共有 8 个子集：
 * - 空子集的异或总和是 0 。
 * - [5] 的异或总和为 5 。
 * - [1] 的异或总和为 1 。
 * - [6] 的异或总和为 6 。
 * - [5,1] 的异或总和为 5 XOR 1 = 4 。
 * - [5,6] 的异或总和为 5 XOR 6 = 3 。
 * - [1,6] 的异或总和为 1 XOR 6 = 7 。
 * - [5,1,6] 的异或总和为 5 XOR 1 XOR 6 = 2 。
 * 0 + 5 + 1 + 6 + 4 + 3 + 7 + 2 = 28
 *
 *
 * 示例 3：
 *
 * 输入：nums = [3,4,5,6,7,8]
 * 输出：480
 * 解释：每个子集的全部异或总和值之和为 480 。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= nums.length <= 12
 * 1 <= nums[i] <= 20
 *
 *
 */

// @lc code=start
package main

func subsetXORSum(nums []int) int {
	n, result := len(nums), 0

	for _, num := range nums {
		result |= num
	}

	return result << (n - 1)
}

// @lc code=end
