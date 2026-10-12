/*
 * @lc app=leetcode.cn id=2897 lang=golang
 *
 * [2897] 对数组执行操作使平方和最大
 *
 * https://leetcode.cn/problems/apply-operations-on-array-to-maximize-sum-of-squares/description/
 *
 * algorithms
 * Hard (58.24%)
 * Likes:    16
 * Dislikes: 0
 * Total Accepted:    4.1K
 * Total Submissions: 6.7K
 * Testcase Example:  '[2,6,5,8]\n2'
 *
 * 给你一个下标从 0 开始的整数数组 nums 和一个 正 整数 k 。
 *
 * 你可以对数组执行以下操作 任意次 ：
 *
 *
 * 选择两个互不相同的下标 i 和 j ，同时 将 nums[i] 更新为 (nums[i] AND nums[j]) 且将 nums[j] 更新为
 * (nums[i] OR nums[j]) ，OR 表示按位 或 运算，AND 表示按位 与 运算。
 *
 *
 * 你需要从最终的数组里选择 k 个元素，并计算它们的 平方 之和。
 *
 * 请你返回你可以得到的 最大 平方和。
 *
 * 由于答案可能会很大，将答案对 10^9 + 7 取余 后返回。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = [2,6,5,8], k = 2
 * 输出：261
 * 解释：我们可以对数组执行以下操作：
 * - 选择 i = 0 和 j = 3 ，同时将 nums[0] 变为 (2 AND 8) = 0 且 nums[3] 变为 (2 OR 8) = 10
 * ，结果数组为 nums = [0,6,5,10] 。
 * - 选择 i = 2 和 j = 3 ，同时将 nums[2] 变为 (5 AND 10) = 0 且 nums[3] 变为 (5 OR 10) =
 * 15 ，结果数组为 nums = [0,6,0,15] 。
 * 从最终数组里选择元素 15 和 6 ，平方和为 15^2 + 6^2 = 261 。
 * 261 是可以得到的最大结果。
 *
 *
 * 示例 2：
 *
 *
 * 输入：nums = [4,5,4,7], k = 3
 * 输出：90
 * 解释：不需要执行任何操作。
 * 选择元素 7 ，5 和 4 ，平方和为 7^2 + 5^2 + 4^2 = 90 。
 * 90 是可以得到的最大结果。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= k <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 *
 *
 */

// @lc code=start
package main

func maxSum(nums []int, k int) int {
	const mod = 1000000007
	records := [30]int{}
	sum := 0

	for _, num := range nums {
		for i := range records {
			records[i] += num >> i & 1
		}
	}

	for ; k > 0; k-- {
		x := 0
		for i := range records {
			if records[i] > 0 {
				records[i]--
				x |= 1 << i
			}
		}

		sum = (sum + x*x) % mod
	}

	return sum
}

// @lc code=end
