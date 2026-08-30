/*
 * @lc app=leetcode.cn id=2091 lang=golang
 *
 * [2091] 从数组中移除最大值和最小值
 *
 * https://leetcode.cn/problems/removing-minimum-and-maximum-from-array/description/
 *
 * algorithms
 * Medium (57.88%)
 * Likes:    23
 * Dislikes: 0
 * Total Accepted:    12.9K
 * Total Submissions: 21.5K
 * Testcase Example:  '[2,10,7,5,4,1,8,6]'
 *
 * 给你一个下标从 0 开始的数组 nums ，数组由若干 互不相同 的整数组成。
 *
 * nums 中有一个值最小的元素和一个值最大的元素。分别称为 最小值 和 最大值 。你的目标是从数组中移除这两个元素。
 *
 * 一次 删除 操作定义为从数组的 前面 移除一个元素或从数组的 后面 移除一个元素。
 *
 * 返回将数组中最小值和最大值 都 移除需要的最小删除次数。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = [2,10,7,5,4,1,8,6]
 * 输出：5
 * 解释：
 * 数组中的最小元素是 nums[5] ，值为 1 。
 * 数组中的最大元素是 nums[1] ，值为 10 。
 * 将最大值和最小值都移除需要从数组前面移除 2 个元素，从数组后面移除 3 个元素。
 * 结果是 2 + 3 = 5 ，这是所有可能情况中的最小删除次数。
 *
 *
 * 示例 2：
 *
 *
 * 输入：nums = [0,-4,19,1,8,-2,-3,5]
 * 输出：3
 * 解释：
 * 数组中的最小元素是 nums[1] ，值为 -4 。
 * 数组中的最大元素是 nums[2] ，值为 19 。
 * 将最大值和最小值都移除需要从数组前面移除 3 个元素。
 * 结果是 3 ，这是所有可能情况中的最小删除次数。
 *
 *
 * 示例 3：
 *
 *
 * 输入：nums = [101]
 * 输出：1
 * 解释：
 * 数组中只有这一个元素，那么它既是数组中的最小值又是数组中的最大值。
 * 移除它只需要 1 次删除操作。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= nums.length <= 10^5
 * -10^5 <= nums[i] <= 10^5
 * nums 中的整数 互不相同
 *
 *
 */

// @lc code=start
package main

func minimumDeletions(nums []int) int {
	n, p, q := len(nums), 0, 0

	for i, num := range nums {
		if num < nums[p] {
			p = i
		} else if num > nums[q] {
			q = i
		}
	}

	if p > q {
		p, q = q, p
	}

	return min(q+1, min(n-p, p+1+n-q))
}

func min(x, y int) int {
	if x < y {
		return x
	}

	return y
}

// @lc code=end
