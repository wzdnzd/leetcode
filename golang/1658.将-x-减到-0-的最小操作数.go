/*
 * @lc app=leetcode.cn id=1658 lang=golang
 *
 * [1658] 将 x 减到 0 的最小操作数
 *
 * https://leetcode.cn/problems/minimum-operations-to-reduce-x-to-zero/description/
 *
 * algorithms
 * Medium (40.44%)
 * Likes:    495
 * Dislikes: 0
 * Total Accepted:    99.9K
 * Total Submissions: 246.4K
 * Testcase Example:  '[1,1,4,2,3]\n5'
 *
 * 给你一个整数数组 nums 和一个整数 x 。每一次操作时，你应当移除数组 nums 最左边或最右边的元素，然后从 x 中减去该元素的值。请注意，需要
 * 修改 数组以供接下来的操作使用。
 *
 * 如果可以将 x 恰好 减到 0 ，返回 最小操作数 ；否则，返回 -1 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = [1,1,4,2,3], x = 5
 * 输出：2
 * 解释：最佳解决方案是移除后两个元素，将 x 减到 0 。
 *
 *
 * 示例 2：
 *
 *
 * 输入：nums = [5,6,7,8,9], x = 4
 * 输出：-1
 *
 *
 * 示例 3：
 *
 *
 * 输入：nums = [3,2,20,1,1,3], x = 10
 * 输出：5
 * 解释：最佳解决方案是移除后三个元素和前两个元素（总共 5 次操作），将 x 减到 0 。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1
 * 1
 * 1
 *
 *
 */

// @lc code=start
package main

func minOperations(nums []int, x int) int {
	sum, n := 0, len(nums)

	right := n
	for right > 0 && sum+nums[right-1] <= x {
		right--
		sum += nums[right]
	}

	if right == 0 && sum < x {
		return -1
	}

	count := n + 1
	if sum == x {
		count = n - right
	}

	for left, num := range nums {
		sum += num
		for right < n && sum > x {
			sum -= nums[right]
			right++
		}

		if sum > x {
			break
		}

		if sum == x {
			count = min(count, left+1+n-right)
		}
	}

	if count > n {
		return -1
	}

	return count
}

// @lc code=end
